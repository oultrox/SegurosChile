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
@Table(name = "supervisor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Supervisor.findAll", query = "SELECT s FROM Supervisor s"),
    @NamedQuery(name = "Supervisor.findByIdSupervisor", query = "SELECT s FROM Supervisor s WHERE s.idSupervisor = :idSupervisor"),
    @NamedQuery(name = "Supervisor.findByRutSupervisor", query = "SELECT s FROM Supervisor s WHERE s.rutSupervisor = :rutSupervisor"),
    @NamedQuery(name = "Supervisor.findByClaveSupervisor", query = "SELECT s FROM Supervisor s WHERE s.claveSupervisor = :claveSupervisor")})
public class Supervisor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_supervisor")
    private Integer idSupervisor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "rut_supervisor")
    private String rutSupervisor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "clave_supervisor")
    private String claveSupervisor;

    public Supervisor() {
    }

    public Supervisor(Integer idSupervisor) {
        this.idSupervisor = idSupervisor;
    }

    public Supervisor(Integer idSupervisor, String rutSupervisor, String claveSupervisor) {
        this.idSupervisor = idSupervisor;
        this.rutSupervisor = rutSupervisor;
        this.claveSupervisor = claveSupervisor;
    }

    public Integer getIdSupervisor() {
        return idSupervisor;
    }

    public void setIdSupervisor(Integer idSupervisor) {
        this.idSupervisor = idSupervisor;
    }

    public String getRutSupervisor() {
        return rutSupervisor;
    }

    public void setRutSupervisor(String rutSupervisor) {
        this.rutSupervisor = rutSupervisor;
    }

    public String getClaveSupervisor() {
        return claveSupervisor;
    }

    public void setClaveSupervisor(String claveSupervisor) {
        this.claveSupervisor = claveSupervisor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSupervisor != null ? idSupervisor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Supervisor)) {
            return false;
        }
        Supervisor other = (Supervisor) object;
        if ((this.idSupervisor == null && other.idSupervisor != null) || (this.idSupervisor != null && !this.idSupervisor.equals(other.idSupervisor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.Supervisor[ idSupervisor=" + idSupervisor + " ]";
    }
    
}
