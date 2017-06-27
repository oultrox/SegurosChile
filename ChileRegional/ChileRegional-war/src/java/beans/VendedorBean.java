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

    public List<Vendedor> getVendedores(){
        return vendedorFacade.findAll();
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
        Vendedor v = vendedorFacade.find(vendedor.getRutVendedor());
        vendedorFacade.remove(v);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vendedor Eliminado!!!"));
        return "index";
    }

    public String actualizarDatos() {
        Vendedor v = vendedorFacade.find(vendedor.getRutVendedor());
        v.setCorreoVendedor(vendedor.getCorreoVendedor());
        vendedorFacade.edit(v);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vendedor actualizado!!!"));
        return "index";
    }
    
   
    
    
}
