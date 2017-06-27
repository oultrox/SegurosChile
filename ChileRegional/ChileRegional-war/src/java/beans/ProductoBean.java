/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import pojos.Cliente;
import pojos.Producto;
import services.ClienteFacadeLocal;
import services.NombreproductoFacadeLocal;
import services.PrecioFacadeLocal;
import services.ProductoFacadeLocal;

/**
 *
 * @author Pelao
 */
@Named(value = "productoBean")
@SessionScoped
public class ProductoBean implements Serializable {

    @EJB
    private ClienteFacadeLocal clienteFacade;

    @EJB
    private ProductoFacadeLocal productoFacade;

    @EJB
    private NombreproductoFacadeLocal nomProdFacade;

    @EJB
    private PrecioFacadeLocal precioFacade;

    private Producto producto;
    private Cliente cliente;

    public ProductoBean() {
        producto = new Producto();
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Producto> getProductos() {
        return productoFacade.findAll();
    }

    public String crearProducto() {
        try {
            Cliente c = (Cliente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cliente");
            Producto p = new Producto();
            p.setNombreProducto(nomProdFacade.find(producto.getNombreProducto()));
            p.setEstadoProducto("Pendiente");
            p.setRutCliente(c);
            p.setIdPrecio(precioFacade.find(producto.getIdPrecio()));
            this.productoFacade.create(p);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto en espera de ser aprobado por un Supervisor"));
            return "seleccionProductos";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error, intente nuevamente", ""));
            return "seleccionProductos";
        }
    }

    public String eliminarProducto(Producto producto) {
        try {
            Producto p = productoFacade.find(producto);
            productoFacade.remove(p);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto Eliminado!!!"));
            return "gestionProductos";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error, intente nuevamente", ""));
            return "gestionProductos";
        }
    }

    public String actualizarDatos() {
        Producto p = productoFacade.find(producto.getIdProducto());
        p.setNombreProducto(producto.getNombreProducto());
        productoFacade.edit(p);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto actualizado!!!"));
        return "gestionProductos";
    }

    public String aprobarCompra() {
        Producto p = productoFacade.find(producto.getIdProducto());
        p.setEstadoProducto("Aprobado");
        productoFacade.edit(p);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto/s Aprobados!!!"));

        final String username = "soportechileregional@gmail.com";
        final String password = "12345678.";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(""));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(clienteFacade.find(cliente).getCorreoCliente()));
            message.setSubject("Su seguro de vida ha sido aprobado");
            message.setText("Estimado " + clienteFacade.find(cliente).getNombresCliente() + " " + clienteFacade.find(cliente).getApellidoPatCliente() + "\n"
                    + "Se le comunica que su transacción para solicitar un seguro de vida en nuestra compañia ha sido aceptada" + "\n" + "\n"
                    + "Atte" + "\n"
                    + "Chile Regional");

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return "aprobarProductos";
    }

}
