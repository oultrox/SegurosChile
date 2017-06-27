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
import pojos.Supervisor;
import services.SupervisorFacadeLocal;

/**
 *
 * @author Pelao
 */
@Named(value = "supervisorBean")
@SessionScoped
public class SupervisorBean implements Serializable {

    @EJB
    private SupervisorFacadeLocal supervisorFacade;
    private Supervisor supervisor;
    boolean loggedIn = false;
    
    public SupervisorBean() {
        supervisor = new Supervisor();
    }

    public SupervisorFacadeLocal getSupervisorFacade() {
        return supervisorFacade;
    }

    public void setSupervisorFacade(SupervisorFacadeLocal supervisorFacade) {
        this.supervisorFacade = supervisorFacade;
    }


    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }
    
    public List<Supervisor> getSupervisores(){
        return supervisorFacade.findAll();
    }
    

    public void login(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        Supervisor s = supervisorFacade.find(supervisor);

        if (s != null && supervisor.getClaveSupervisor().equals(s.getClaveSupervisor())) {
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido Supervisor del Sistema", "");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("supervisor", s);
            FacesContext.getCurrentInstance().addMessage(null, message);
            context.addCallbackParam("loggedIn", loggedIn);
            context.addCallbackParam("view", "faces/indexSupervisor.xhtml");
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Rut o clave no válida");
            FacesContext.getCurrentInstance().addMessage(null, message);
            context.addCallbackParam("view", "faces/loginSupervisor.xhtml");
            
        }
    }
    
    public boolean verificarSesionMenu() {
        FacesContext context = FacesContext.getCurrentInstance();
        Supervisor s1 = (Supervisor) context.getExternalContext().getSessionMap().get("supervisor");
        if (s1 == null) {
            return false;
        } else {
            return true;
        }
    }

   
    public void verificarSesion() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Supervisor s = (Supervisor) context.getExternalContext().getSessionMap().get("supervisor");
            if (s == null) {
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
    
    public String crearSupervisor() {
        try {
            Supervisor s = new Supervisor();
            s.setRutSupervisor(supervisor.getRutSupervisor());
            s.setClaveSupervisor(supervisor.getClaveSupervisor());
            this.supervisorFacade.create(s);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Supervisor ingresado al sistema correctamente"));
            return "index";            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al registrar Supervisor", ""));
            return "index";
        }

    }
    
    public String eliminarSupervisor(Supervisor supervisor) {
        Supervisor s = supervisorFacade.find(supervisor.getRutSupervisor());
        supervisorFacade.remove(s);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Supervisor Eliminado!!!"));
        return "index";
    }

    
    public String actualizarContrasena() {
        Supervisor s = (Supervisor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("supervisor");
        s = supervisorFacade.find(s);
        if (s.getClaveSupervisor()== supervisor.getClaveSupervisor()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No debe usar la contraseña antigua", ""));
            return "cambioContrasenaSupervisor";
        } else {
            s.setClaveSupervisor(supervisor.getClaveSupervisor());
            supervisorFacade.edit(s);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Su contraseña ha sido modificada"));
            cerrarSesion();
            return "loginSupervisor";
        }
    }
    
    //Generar menu
    
    private MenuModel menu;
    
    public MenuModel generarMenu() {
        menu = new DefaultMenuModel();
        Supervisor pSupervisor = (Supervisor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("supervisor");
        if (pSupervisor != null) {
            this.supervisor = pSupervisor;
            DefaultMenuItem item = new DefaultMenuItem("INICIO");
            item.setOutcome("index");
            menu.addElement(item);

            item = new DefaultMenuItem("VALIDAR SEGUROS");
            item.setOutcome("validarSeguros");
            menu.addElement(item);
            
            item = new DefaultMenuItem("CAMBIAR CONTRASEÑA");
            item.setOutcome("cambioContrasenaSupervisor");
            menu.addElement(item);

        }

        return menu;
    }
    
}
