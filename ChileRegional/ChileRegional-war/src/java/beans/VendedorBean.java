/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import services.VendedorFacadeLocal;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import pojos.Vendedor;

/**
 *
 * @author Pelao
 */
@Named(value = "vendedorBean")
@SessionScoped
public class VendedorBean implements Serializable {

    @EJB
    private VendedorFacadeLocal vendedorFacade;

    private Vendedor vendedor;
    private String nombres_vendedor;
    private String rut_vendedor;
    private String clave_vendedor;
    private String correo_vendedor;
    
    public VendedorBean() {
        vendedor = new Vendedor();
    }

    public VendedorFacadeLocal getVendedorFacade() {
        return vendedorFacade;
    }

    public void setVendedorFacade(VendedorFacadeLocal vendedorFacade) {
        this.vendedorFacade = vendedorFacade;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public String getNombres_vendedor() {
        return nombres_vendedor;
    }

    public void setNombres_vendedor(String nombres_vendedor) {
        this.nombres_vendedor = nombres_vendedor;
    }

    public String getRut_vendedor() {
        return rut_vendedor;
    }

    public void setRut_vendedor(String rut_vendedor) {
        this.rut_vendedor = rut_vendedor;
    }

    public String getClave_vendedor() {
        return clave_vendedor;
    }

    public void setClave_vendedor(String clave_vendedor) {
        this.clave_vendedor = clave_vendedor;
    }

    public String getCorreo_vendedor() {
        return correo_vendedor;
    }

    public void setCorreo_vendedor(String correo_vendedor) {
        this.correo_vendedor = correo_vendedor;
    }
    
    public List<Vendedor> getVendedores(){
        return vendedorFacade.findAll();
    }
    
    public Vendedor getEsteVendedor(){
        return vendedorFacade.find(rut_vendedor);
    }
    
    public String crearVendedor() {
        try {
            Vendedor v = new Vendedor();
            v.setRutVendedor(vendedor.getRutVendedor());
            v.setClaveVendedor(vendedor.getClaveVendedor());
            v.setNombreVendedor(vendedor.getNombreVendedor());
            v.setCorreoVendedor(vendedor.getCorreoVendedor());
            this.vendedorFacade.create(v);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vendedor ingresado al sistema correctamente"));
            return "index";            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al registrar Vendedor", ""));
            return "index";
        }

    }
    
    public String eliminarVendedor(Vendedor vendedor) {
        Vendedor v = vendedorFacade.find(vendedor.getIdVendedor());
        vendedorFacade.remove(v);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vendedor Eliminado!!!"));
        return "index";
    }

    public String actualizarDatos() {
        Vendedor v = vendedorFacade.find(vendedor.getIdVendedor());
        v.setCorreoVendedor(vendedor.getCorreoVendedor());
        vendedorFacade.edit(v);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vendedor actualizado!!!"));
        return "index";
    }
    
    public String actualizarContrasena() {
        Vendedor v = vendedorFacade.find(vendedor.getIdVendedor());
        v.setClaveVendedor(vendedor.getClaveVendedor());
        vendedorFacade.edit(v);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contraseña actualizada!!!"));
        return "index";
    }
    
    
}
