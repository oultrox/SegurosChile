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
import pojos.Solicitud;
import services.ClienteFacadeLocal;
import services.ProductoFacadeLocal;
import services.SolicitudFacadeLocal;

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
    private SolicitudFacadeLocal solicitudFacade;

    @EJB
    private ProductoFacadeLocal productoFacade;

    private Solicitud solicitud;
    private Producto producto;
    private Cliente cliente;
    private int id_solicitud;
    private int id_producto;
    private String nombre_producto;
    private String descripcion_producto;
    private String estado;

    public ProductoBean() {
        solicitud = new Solicitud();
        producto = new Producto();
    }

    public SolicitudFacadeLocal getSolicitudFacade() {
        return solicitudFacade;
    }

    public void setSolicitudFacade(SolicitudFacadeLocal solicitudFacade) {
        this.solicitudFacade = solicitudFacade;
    }

    public ProductoFacadeLocal getProductoFacade() {
        return productoFacade;
    }

    public void setProductoFacade(ProductoFacadeLocal productoFacade) {
        this.productoFacade = productoFacade;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getDescripcion_producto() {
        return descripcion_producto;
    }

    public void setDescripcion_producto(String descripcion_producto) {
        this.descripcion_producto = descripcion_producto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Producto> getProductos() {
        return productoFacade.findAll();
    }

    public Producto getEsteProducto() {
        return productoFacade.find(id_producto);
    }

    public Solicitud buscarEstaSolicitud() {
        return solicitudFacade.find(id_solicitud);
    }

    public String crearProducto() {
        try {
            Producto p = new Producto();
            p.setNombreProducto(producto.getNombreProducto());
            p.setDescripcionProducto(producto.getDescripcionProducto());
            p.setEstadoProducto("Pendiente");
            p.setSolicitudIdSolicitud(solicitudFacade.find(solicitud.getIdSolicitud()));
            this.productoFacade.create(p);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto en espera de ser aprobado por un Supervisor"));
            return "seleccionProductos";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error, intente nuevamente", ""));
            return "seleccionProductos";
        }

    }

    public String eliminarProducto(Producto producto) {
        Producto p = productoFacade.find(producto.getIdProducto());
        productoFacade.remove(p);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto Eliminado!!!"));
        return "gestionProductos";
    }

    public String actualizarDatos() {
        Producto p = productoFacade.find(producto.getIdProducto());
        p.setNombreProducto(producto.getNombreProducto());
        p.setDescripcionProducto(producto.getDescripcionProducto());
        productoFacade.edit(p);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto actualizado!!!"));
        return "gestionProductos";
    }

    public String cambiarEstado() {
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
                    InternetAddress.parse(clienteFacade.find(cliente.getIdCliente()).getCorreoCliente()));
            message.setSubject("Su seguro de vida ha sido aprobado");
            message.setText("Estimado " + clienteFacade.find(cliente.getIdCliente()).getNombresCliente() + " " + clienteFacade.find(cliente.getIdCliente()).getApellidoPatCliente() + "\n"
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
