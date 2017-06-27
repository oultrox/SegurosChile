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
@Table(name = "vendedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vendedor.findAll", query = "SELECT v FROM Vendedor v"),
    @NamedQuery(name = "Vendedor.findByRutVendedor", query = "SELECT v FROM Vendedor v WHERE v.rutVendedor = :rutVendedor"),
    @NamedQuery(name = "Vendedor.findByNombreVendedor", query = "SELECT v FROM Vendedor v WHERE v.nombreVendedor = :nombreVendedor"),
    @NamedQuery(name = "Vendedor.findByClaveVendedor", query = "SELECT v FROM Vendedor v WHERE v.claveVendedor = :claveVendedor"),
    @NamedQuery(name = "Vendedor.findByCorreoVendedor", query = "SELECT v FROM Vendedor v WHERE v.correoVendedor = :correoVendedor")})
public class Vendedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "rut_vendedor")
    private Integer rutVendedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "nombre_vendedor")
    private String nombreVendedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "clave_vendedor")
    private String claveVendedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "correo_vendedor")
    private String correoVendedor;

    public Vendedor() {
    }

    public Vendedor(Integer rutVendedor) {
        this.rutVendedor = rutVendedor;
    }

    public Vendedor(Integer rutVendedor, String nombreVendedor, String claveVendedor, String correoVendedor) {
        this.rutVendedor = rutVendedor;
        this.nombreVendedor = nombreVendedor;
        this.claveVendedor = claveVendedor;
        this.correoVendedor = correoVendedor;
    }

    public Integer getRutVendedor() {
        return rutVendedor;
    }

    public void setRutVendedor(Integer rutVendedor) {
        this.rutVendedor = rutVendedor;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public String getClaveVendedor() {
        return claveVendedor;
    }

    public void setClaveVendedor(String claveVendedor) {
        this.claveVendedor = claveVendedor;
    }

    public String getCorreoVendedor() {
        return correoVendedor;
    }

    public void setCorreoVendedor(String correoVendedor) {
        this.correoVendedor = correoVendedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rutVendedor != null ? rutVendedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vendedor)) {
            return false;
        }
        Vendedor other = (Vendedor) object;
        if ((this.rutVendedor == null && other.rutVendedor != null) || (this.rutVendedor != null && !this.rutVendedor.equals(other.rutVendedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.Vendedor[ rutVendedor=" + rutVendedor + " ]";
    }
    
}
