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
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import pojos.Cliente;
import pojos.Solicitud;
import services.ClienteFacadeLocal;
import services.SolicitudFacadeLocal;

/**
 *
 * @author Pelao
 */
@Named(value = "solicitudBean")
@SessionScoped
public class SolicitudBean implements Serializable {

    @EJB
    private ClienteFacadeLocal clienteFacade;

    @EJB
    private SolicitudFacadeLocal solicitudFacade;

    private Solicitud solicitud;
    private Cliente cliente;
    
    private int id_solicitud;
    private String descripcion_solicitud;
    private int id_cliente;
    
    public SolicitudBean() {
        solicitud = new Solicitud();
        cliente = new Cliente();
    }

    public ClienteFacadeLocal getClienteFacade() {
        return clienteFacade;
    }

    public void setClienteFacade(ClienteFacadeLocal clienteFacade) {
        this.clienteFacade = clienteFacade;
    }

    public SolicitudFacadeLocal getSolicitudFacade() {
        return solicitudFacade;
    }

    public void setSolicitudFacade(SolicitudFacadeLocal solicitudFacade) {
        this.solicitudFacade = solicitudFacade;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getId_solicitud() {
        return id_solicitud;
    }

    public void setId_solicitud(int id_solicitud) {
        this.id_solicitud = id_solicitud;
    }

    public String getDescripcion_solicitud() {
        return descripcion_solicitud;
    }

    public void setDescripcion_solicitud(String descripcion_solicitud) {
        this.descripcion_solicitud = descripcion_solicitud;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
    
    public List<Solicitud> getSolicitudes(){
        return solicitudFacade.findAll();
    }
    
    public Solicitud getEstaSolicitud(){
        return solicitudFacade.find(id_solicitud);
    }
    
    public Cliente buscarEsteCliente(){
        return clienteFacade.find(id_cliente);
    }
    
    public String crearSolicitud() {
        try {
            Solicitud s = new Solicitud();
            s.setDescripcionSolicitud(solicitud.getDescripcionSolicitud());
            s.setClienteIdCliente(clienteFacade.find(cliente.getIdCliente()));
            this.solicitudFacade.create(s);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Solicitud creada correctamente!!!"));
            return "index";            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al crear la solicitud", ""));
            return "index";
        }

    }
    
    public String eliminarSolicitud(Solicitud solicitud) {
        Solicitud s = solicitudFacade.find(solicitud.getIdSolicitud());
        solicitudFacade.remove(s);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Solicitud Eliminada!!!"));
        return "index";
    }

    public String actualizarDatos() {
        Solicitud s = solicitudFacade.find(solicitud.getIdSolicitud());
        s.setDescripcionSolicitud(solicitud.getDescripcionSolicitud());
        solicitudFacade.edit(s);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Solicitud actualizada!!!"));
        return "index";
    }
    
}
