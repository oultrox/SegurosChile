/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pelao
 */
@Entity
@Table(name = "super_administrador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SuperAdministrador.findAll", query = "SELECT s FROM SuperAdministrador s"),
    @NamedQuery(name = "SuperAdministrador.findByIdAdmin", query = "SELECT s FROM SuperAdministrador s WHERE s.idAdmin = :idAdmin"),
    @NamedQuery(name = "SuperAdministrador.findByRutAdmin", query = "SELECT s FROM SuperAdministrador s WHERE s.rutAdmin = :rutAdmin"),
    @NamedQuery(name = "SuperAdministrador.findByClaveAdmin", query = "SELECT s FROM SuperAdministrador s WHERE s.claveAdmin = :claveAdmin")})
public class SuperAdministrador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_admin")
    private Integer idAdmin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "rut_admin")
    private String rutAdmin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "clave_admin")
    private String claveAdmin;

    public SuperAdministrador() {
    }

    public SuperAdministrador(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }

    public SuperAdministrador(Integer idAdmin, String rutAdmin, String claveAdmin) {
        this.idAdmin = idAdmin;
        this.rutAdmin = rutAdmin;
        this.claveAdmin = claveAdmin;
    }

    public Integer getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getRutAdmin() {
        return rutAdmin;
    }

    public void setRutAdmin(String rutAdmin) {
        this.rutAdmin = rutAdmin;
    }

    public String getClaveAdmin() {
        return claveAdmin;
    }

    public void setClaveAdmin(String claveAdmin) {
        this.claveAdmin = claveAdmin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAdmin != null ? idAdmin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SuperAdministrador)) {
            return false;
        }
        SuperAdministrador other = (SuperAdministrador) object;
        if ((this.idAdmin == null && other.idAdmin != null) || (this.idAdmin != null && !this.idAdmin.equals(other.idAdmin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.SuperAdministrador[ idAdmin=" + idAdmin + " ]";
    }
    
}
