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
    private String rut_cliente;
    private String dv_cliente;
    private int clave_cliente;
    private String nombres_cliente;
    private String apellido_pat_cliente;
    private String apellido_mat_cliente;
    private String direccion_cliente;
    private int telefono_cliente;
    private String correo_cliente;
    private String actividad_cliente;
    private String beneficiario1_nombre;
    private String beneficiario2_nombre;
    private Vendedor vendedor;
    private String rut_vendedor;
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

    public String getRut_cliente() {
        return rut_cliente;
    }

    public void setRut_cliente(String rut_cliente) {
        this.rut_cliente = rut_cliente;
    }

    public String getDv_cliente() {
        return dv_cliente;
    }

    public void setDv_cliente(String dv_cliente) {
        this.dv_cliente = dv_cliente;
    }

    public int getClave_cliente() {
        return clave_cliente;
    }

    public void setClave_cliente(int clave_cliente) {
        this.clave_cliente = clave_cliente;
    }

    public String getNombres_cliente() {
        return nombres_cliente;
    }

    public void setNombres_cliente(String nombres_cliente) {
        this.nombres_cliente = nombres_cliente;
    }

    public String getApellido_pat_cliente() {
        return apellido_pat_cliente;
    }

    public void setApellido_pat_cliente(String apellido_pat_cliente) {
        this.apellido_pat_cliente = apellido_pat_cliente;
    }

    public String getApellido_mat_cliente() {
        return apellido_mat_cliente;
    }

    public void setApellido_mat_cliente(String apellido_mat_cliente) {
        this.apellido_mat_cliente = apellido_mat_cliente;
    }

    public String getDireccion_cliente() {
        return direccion_cliente;
    }

    public void setDireccion_cliente(String direccion_cliente) {
        this.direccion_cliente = direccion_cliente;
    }

    public int getTelefono_cliente() {
        return telefono_cliente;
    }

    public void setTelefono_cliente(int telefono_cliente) {
        this.telefono_cliente = telefono_cliente;
    }

    public String getCorreo_cliente() {
        return correo_cliente;
    }

    public void setCorreo_cliente(String correo_cliente) {
        this.correo_cliente = correo_cliente;
    }

    public String getActividad_cliente() {
        return actividad_cliente;
    }

    public void setActividad_cliente(String actividad_cliente) {
        this.actividad_cliente = actividad_cliente;
    }

    public String getBeneficiario1_nombre() {
        return beneficiario1_nombre;
    }

    public void setBeneficiario1_nombre(String beneficiario1_nombre) {
        this.beneficiario1_nombre = beneficiario1_nombre;
    }

    public String getBeneficiario2_nombre() {
        return beneficiario2_nombre;
    }

    public String getRut_vendedor() {
        return rut_vendedor;
    }

    public void setRut_vendedor(String rut_vendedor) {
        this.rut_vendedor = rut_vendedor;
    }

    
    public void setBeneficiario2_nombre(String beneficiario2_nombre) {
        this.beneficiario2_nombre = beneficiario2_nombre;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    
    public List<Cliente> getClientes(){
        return clienteFacade.findAll();
    }
    
    public Cliente getEsteCliente(){
        return clienteFacade.find(rut_cliente);
    }
    
    public Vendedor buscarEsteFuncionario(){
        return vendedorFacade.find(rut_vendedor);
    }
    
    public void login(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        Cliente c = clienteFacade.find(rut_cliente);

        if (c != null && clave_cliente == c.getClaveCliente()) {
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", c.getNombresCliente() + " " + c.getApellidoPatCliente());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cliente", c);
            FacesContext.getCurrentInstance().addMessage(null, message);
            context.addCallbackParam("loggedIn", loggedIn);
            context.addCallbackParam("view", "faces/index.xhtml");
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Rut o clave no válida");
            FacesContext.getCurrentInstance().addMessage(null, message);
            context.addCallbackParam("view", "faces/index.xhtml");
            
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
            c.setClaveCliente(cliente.getClaveCliente());
            c.setNombresCliente(cliente.getNombresCliente());
            c.setApellidoPatCliente(cliente.getApellidoPatCliente());
            c.setApellidoMatCliente(cliente.getApellidoMatCliente());
            c.setDireccionCliente(cliente.getDireccionCliente());
            c.setTelefonoCliente(cliente.getTelefonoCliente());
            c.setCorreoCliente(cliente.getCorreoCliente());
            c.setActividad(cliente.getActividad());
            c.setBeneficiario1Nombre(cliente.getBeneficiario1Nombre());
            c.setBeneficiario2Nombre(cliente.getBeneficiario2Nombre());
            c.setVendedorIdVendedor(vendedorFacade.find(vendedor.getIdVendedor()));
            this.clienteFacade.create(c);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente ingresado al sistema correctamente"));
            return "index";            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al registrar Cliente", ""));
            return "index";
        }

    }
    
    public String eliminarCliente(Cliente cliente) {
        Cliente c = clienteFacade.find(cliente.getIdCliente());
        clienteFacade.remove(c);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente Eliminado!!!"));
        return "index";
    }

    public String actualizarDatos() {
        Cliente c = clienteFacade.find(cliente.getIdCliente());
        c.setDireccionCliente(cliente.getDireccionCliente());
        c.setTelefonoCliente(cliente.getTelefonoCliente());
        c.setCorreoCliente(cliente.getCorreoCliente());
        c.setActividad(cliente.getActividad());
        clienteFacade.edit(c);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente actualizado!!!"));
        return "index";
    }
    
    public String actualizarContrasena() {
        Cliente c = clienteFacade.find(cliente.getIdCliente());
        c.setClaveCliente(cliente.getClaveCliente());
        clienteFacade.edit(c);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contraseña actualizada!!!"));
        return "index";
    }
    
    //Generar menu
    
    private MenuModel menu;
    
    public MenuModel generarMenu() {
        menu = new DefaultMenuModel();
        Cliente pCliente = (Cliente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cliente");
        if (pCliente != null) {
            this.cliente = pCliente;
            DefaultMenuItem item = new DefaultMenuItem("INICIO");
            item.setOutcome("index");
            menu.addElement(item);

            item = new DefaultMenuItem("PEDIR SEGURO");
            item.setOutcome("seguro");
            menu.addElement(item);

        }

        return menu;
    }
    
}
