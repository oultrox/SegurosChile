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
import pojos.SuperAdministrador;
import services.SuperAdministradorFacadeLocal;

/**
 *
 * @author Pelao
 */
@Named(value = "superAdministradorBean")
@SessionScoped
public class SuperAdministradorBean implements Serializable {

    @EJB
    private SuperAdministradorFacadeLocal superAdministradorFacade;

    private String rut_admin;
    private String clave_admin;
    private SuperAdministrador admin;
    boolean loggedIn = false;
    
    public SuperAdministradorBean() {
        admin = new SuperAdministrador();
    }

    public SuperAdministradorFacadeLocal getSuperAdministradorFacade() {
        return superAdministradorFacade;
    }

    public void setSuperAdministradorFacade(SuperAdministradorFacadeLocal superAdministradorFacade) {
        this.superAdministradorFacade = superAdministradorFacade;
    }

    public String getRut_admin() {
        return rut_admin;
    }

    public void setRut_admin(String rut_admin) {
        this.rut_admin = rut_admin;
    }

    public String getClave_admin() {
        return clave_admin;
    }

    public void setClave_adminr(String clave_admin) {
        this.clave_admin = clave_admin;
    }

    public SuperAdministrador getAdmin() {
        return admin;
    }

    public void setAdmin(SuperAdministrador admin) {
        this.admin = admin;
    }
    
    public List<SuperAdministrador> getAdministradores(){
        return superAdministradorFacade.findAll();
    }
    
    public SuperAdministrador getEsteCliente(){
        return superAdministradorFacade.find(rut_admin);
    }
    
    public void login(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        SuperAdministrador a = superAdministradorFacade.find(rut_admin);

        if (a != null && clave_admin.equals(a.getClaveAdmin())) {
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido Administrador del Sistema", "");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("admin", a);
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
        SuperAdministrador a1 = (SuperAdministrador) context.getExternalContext().getSessionMap().get("admin");
        if (a1 == null) {
            return false;
        } else {
            return true;
        }
    }

   
    public void verificarSesion() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            SuperAdministrador a = (SuperAdministrador) context.getExternalContext().getSessionMap().get("admin");
            if (a == null) {
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
    
    
    
    //Generar menu
    
    private MenuModel menu;
    
    public MenuModel generarMenu() {
        menu = new DefaultMenuModel();
        SuperAdministrador pAdmin = (SuperAdministrador) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("admin");
        if (pAdmin != null) {
            this.admin = pAdmin;
            DefaultMenuItem item = new DefaultMenuItem("INICIO");
            item.setOutcome("index");
            menu.addElement(item);

            item = new DefaultMenuItem("NUEVOS PRODUCTOS");
            item.setOutcome("productos");
            menu.addElement(item);
            
            item = new DefaultMenuItem("GESTIONAR CLIENTES");
            item.setOutcome("gestionClientes");
            menu.addElement(item);
            
            item = new DefaultMenuItem("GESTIONAR SUPERVISORES");
            item.setOutcome("gestionSupervisores");
            menu.addElement(item);

        }

        return menu;
    }
    
}
