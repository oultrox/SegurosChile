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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sebastian
 */
@Entity
@Table(name = "supervisor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Supervisor.findAll", query = "SELECT s FROM Supervisor s"),
    @NamedQuery(name = "Supervisor.findByRutSupervisor", query = "SELECT s FROM Supervisor s WHERE s.rutSupervisor = :rutSupervisor"),
    @NamedQuery(name = "Supervisor.findByClaveSupervisor", query = "SELECT s FROM Supervisor s WHERE s.claveSupervisor = :claveSupervisor")})
public class Supervisor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "rut_supervisor")
    private Integer rutSupervisor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "clave_supervisor")
    private String claveSupervisor;

    public Supervisor() {
    }

    public Supervisor(Integer rutSupervisor) {
        this.rutSupervisor = rutSupervisor;
    }

    public Supervisor(Integer rutSupervisor, String claveSupervisor) {
        this.rutSupervisor = rutSupervisor;
        this.claveSupervisor = claveSupervisor;
    }

    public Integer getRutSupervisor() {
        return rutSupervisor;
    }

    public void setRutSupervisor(Integer rutSupervisor) {
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
        hash += (rutSupervisor != null ? rutSupervisor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Supervisor)) {
            return false;
        }
        Supervisor other = (Supervisor) object;
        if ((this.rutSupervisor == null && other.rutSupervisor != null) || (this.rutSupervisor != null && !this.rutSupervisor.equals(other.rutSupervisor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.Supervisor[ rutSupervisor=" + rutSupervisor + " ]";
    }
    
}
