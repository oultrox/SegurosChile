/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import pojos.Cliente;
import pojos.Vendedor;
import services.ClienteFacadeLocal;
import services.VendedorFacadeLocal;

/**
 *
 * @author Pelao
 */
@Named(value = "clienteBean")
@SessionScoped
public class ClienteBean implements Serializable {

    @EJB
    private VendedorFacadeLocal vendedorFacade;

    @EJB
    private ClienteFacadeLocal clienteFacade;

    private Cliente cliente;
    private Vendedor vendedor;
    boolean loggedIn = false;

    public ClienteBean() {
        cliente = new Cliente();
        vendedor = new Vendedor();
    }

    public VendedorFacadeLocal getVendedorFacade() {
        return vendedorFacade;
    }

    public void setVendedorFacade(VendedorFacadeLocal vendedorFacade) {
        this.vendedorFacade = vendedorFacade;
    }

    public ClienteFacadeLocal getClienteFacade() {
        return clienteFacade;
    }

    public void setClienteFacade(ClienteFacadeLocal clienteFacade) {
        this.clienteFacade = clienteFacade;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public List<Cliente> getClientes() {
        return clienteFacade.findAll();
    }

    public void login(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        Cliente c = clienteFacade.find(cliente);

        if (c != null && cliente.getClaveCliente() == c.getClaveCliente()) {
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", c.getNombresCliente() + " " + c.getApellidoPatCliente());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cliente", c);
            FacesContext.getCurrentInstance().addMessage(null, message);
            context.addCallbackParam("loggedIn", loggedIn);
            context.addCallbackParam("view", "faces/indexCliente.xhtml");
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Rut o clave no válida");
            FacesContext.getCurrentInstance().addMessage(null, message);
            context.addCallbackParam("view", "faces/loginCliente.xhtml");

        }
    }

    public boolean verificarSesionMenu() {
        FacesContext context = FacesContext.getCurrentInstance();
        Cliente c1 = (Cliente) context.getExternalContext().getSessionMap().get("cliente");
        if (c1 == null) {
            return false;
        } else {
            return true;
        }
    }

    public void verificarSesion() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Cliente c = (Cliente) context.getExternalContext().getSessionMap().get("cliente");
            if (c == null) {
                context.getExternalContext().redirect("faces/index.xhtml");
            }
        } catch (Exception e) {
            //log
        }
    }

    public void cerrarSesion() {
        ExternalContext ctx
                = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath
                = ((ServletContext) ctx.getContext()).getContextPath();
        try {
            ((HttpSession) ctx.getSession(false)).invalidate();
            ctx.redirect(ctxPath + "/faces/index.xhtml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String crearCliente() {
        try {
            Cliente c = new Cliente();
            c.setRutCliente(cliente.getRutCliente());
            c.setDvCliente(cliente.getDvCliente());
            c.setClaveCliente(Integer.parseInt(cliente.getRutCliente().toString() + "123"));
            c.setNombresCliente(cliente.getNombresCliente());
            c.setApellidoPatCliente(cliente.getApellidoPatCliente());
            c.setApellidoMatCliente(cliente.getApellidoMatCliente());
            c.setDireccionCliente(cliente.getDireccionCliente());
            c.setTelefonoCliente(cliente.getTelefonoCliente());
            c.setCorreoCliente(cliente.getCorreoCliente());
            c.setActividad(cliente.getActividad());
            c.setBeneficiario1Nombre(cliente.getBeneficiario1Nombre());
            c.setBeneficiario2Nombre(cliente.getBeneficiario2Nombre());
            this.clienteFacade.create(c);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se le ha enviado un correo electrónico con su clave para entrar al sistema"));
            return "loginCliente";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error, intente nuevamente", ""));
            return "registrarCliente";
        }

    }

    public String eliminarCliente(Cliente cliente) {
        try {
            Cliente c = clienteFacade.find(cliente);
            clienteFacade.remove(c);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente Eliminado!!!"));
            return "gestionarClientes";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error, intente nuevamente", ""));
            return "gestionarClientes";
        }

    }

    public String actualizarDatos() {
        Cliente c = clienteFacade.find(cliente);
        c.setDireccionCliente(cliente.getDireccionCliente());
        c.setTelefonoCliente(cliente.getTelefonoCliente());
        c.setCorreoCliente(cliente.getCorreoCliente());
        c.setActividad(cliente.getActividad());
        clienteFacade.edit(c);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente actualizado!!!"));
        return "gestionarClientes";
    }

    public String actualizarContrasena() {
        Cliente c = (Cliente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cliente");
        c = clienteFacade.find(c);
        if (c.getClaveCliente() == cliente.getClaveCliente()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No debe usar la contraseña antigua", ""));
            return "cambioContrasenaCliente";
        } else {
            c.setClaveCliente(cliente.getClaveCliente());
            clienteFacade.edit(c);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Su contraseña ha sido modificada"));
            cerrarSesion();
            return "loginCliente";
        }
    }

    //Generar menu
    private MenuModel menu;

    public MenuModel generarMenu() {
        menu = new DefaultMenuModel();
        Cliente pCliente = (Cliente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cliente");
        if (pCliente != null) {
            this.cliente = pCliente;
            DefaultMenuItem item = new DefaultMenuItem("INICIO");
            item.setOutcome("indexCliente");
            menu.addElement(item);

            item = new DefaultMenuItem("SOLICITAR SEGURO");
            item.setOutcome("seleccionProductos");
            menu.addElement(item);
            
            item = new DefaultMenuItem("CAMBIAR CONTRASEÑA");
            item.setOutcome("cambioContrasenaCliente");
            menu.addElement(item);

        }

        return menu;
    }

}
