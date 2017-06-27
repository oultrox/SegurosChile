/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sebastian
 */
@Entity
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByRutCliente", query = "SELECT c FROM Cliente c WHERE c.rutCliente = :rutCliente"),
    @NamedQuery(name = "Cliente.findByDvCliente", query = "SELECT c FROM Cliente c WHERE c.dvCliente = :dvCliente"),
    @NamedQuery(name = "Cliente.findByClaveCliente", query = "SELECT c FROM Cliente c WHERE c.claveCliente = :claveCliente"),
    @NamedQuery(name = "Cliente.findByNombresCliente", query = "SELECT c FROM Cliente c WHERE c.nombresCliente = :nombresCliente"),
    @NamedQuery(name = "Cliente.findByApellidoPatCliente", query = "SELECT c FROM Cliente c WHERE c.apellidoPatCliente = :apellidoPatCliente"),
    @NamedQuery(name = "Cliente.findByApellidoMatCliente", query = "SELECT c FROM Cliente c WHERE c.apellidoMatCliente = :apellidoMatCliente"),
    @NamedQuery(name = "Cliente.findByDireccionCliente", query = "SELECT c FROM Cliente c WHERE c.direccionCliente = :direccionCliente"),
    @NamedQuery(name = "Cliente.findByTelefonoCliente", query = "SELECT c FROM Cliente c WHERE c.telefonoCliente = :telefonoCliente"),
    @NamedQuery(name = "Cliente.findByCorreoCliente", query = "SELECT c FROM Cliente c WHERE c.correoCliente = :correoCliente"),
    @NamedQuery(name = "Cliente.findByActividad", query = "SELECT c FROM Cliente c WHERE c.actividad = :actividad"),
    @NamedQuery(name = "Cliente.findByBeneficiario1Nombre", query = "SELECT c FROM Cliente c WHERE c.beneficiario1Nombre = :beneficiario1Nombre"),
    @NamedQuery(name = "Cliente.findByBeneficiario2Nombre", query = "SELECT c FROM Cliente c WHERE c.beneficiario2Nombre = :beneficiario2Nombre"),
    @NamedQuery(name = "Cliente.findByRutVendedor", query = "SELECT c FROM Cliente c WHERE c.rutVendedor = :rutVendedor")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "rut_cliente")
    private Integer rutCliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "dv_cliente")
    private String dvCliente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "clave_cliente")
    private int claveCliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombres_cliente")
    private String nombresCliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "apellido_pat_cliente")
    private String apellidoPatCliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "apellido_mat_cliente")
    private String apellidoMatCliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "direccion_cliente")
    private String direccionCliente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "telefono_cliente")
    private int telefonoCliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "correo_cliente")
    private String correoCliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "actividad")
    private String actividad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "beneficiario1_nombre")
    private String beneficiario1Nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "beneficiario2_nombre")
    private String beneficiario2Nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rut_vendedor")
    private int rutVendedor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rutCliente")
    private List<Producto> productoList;

    public Cliente() {
    }

    public Cliente(Integer rutCliente) {
        this.rutCliente = rutCliente;
    }

    public Cliente(Integer rutCliente, String dvCliente, int claveCliente, String nombresCliente, String apellidoPatCliente, String apellidoMatCliente, String direccionCliente, int telefonoCliente, String correoCliente, String actividad, String beneficiario1Nombre, String beneficiario2Nombre, int rutVendedor) {
        this.rutCliente = rutCliente;
        this.dvCliente = dvCliente;
        this.claveCliente = claveCliente;
        this.nombresCliente = nombresCliente;
        this.apellidoPatCliente = apellidoPatCliente;
        this.apellidoMatCliente = apellidoMatCliente;
        this.direccionCliente = direccionCliente;
        this.telefonoCliente = telefonoCliente;
        this.correoCliente = correoCliente;
        this.actividad = actividad;
        this.beneficiario1Nombre = beneficiario1Nombre;
        this.beneficiario2Nombre = beneficiario2Nombre;
        this.rutVendedor = rutVendedor;
    }

    public Integer getRutCliente() {
        return rutCliente;
    }

    public void setRutCliente(Integer rutCliente) {
        this.rutCliente = rutCliente;
    }

    public String getDvCliente() {
        return dvCliente;
    }

    public void setDvCliente(String dvCliente) {
        this.dvCliente = dvCliente;
    }

    public int getClaveCliente() {
        return claveCliente;
    }

    public void setClaveCliente(int claveCliente) {
        this.claveCliente = claveCliente;
    }

    public String getNombresCliente() {
        return nombresCliente;
    }

    public void setNombresCliente(String nombresCliente) {
        this.nombresCliente = nombresCliente;
    }

    public String getApellidoPatCliente() {
        return apellidoPatCliente;
    }

    public void setApellidoPatCliente(String apellidoPatCliente) {
        this.apellidoPatCliente = apellidoPatCliente;
    }

    public String getApellidoMatCliente() {
        return apellidoMatCliente;
    }

    public void setApellidoMatCliente(String apellidoMatCliente) {
        this.apellidoMatCliente = apellidoMatCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public int getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(int telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getBeneficiario1Nombre() {
        return beneficiario1Nombre;
    }

    public void setBeneficiario1Nombre(String beneficiario1Nombre) {
        this.beneficiario1Nombre = beneficiario1Nombre;
    }

    public String getBeneficiario2Nombre() {
        return beneficiario2Nombre;
    }

    public void setBeneficiario2Nombre(String beneficiario2Nombre) {
        this.beneficiario2Nombre = beneficiario2Nombre;
    }

    public int getRutVendedor() {
        return rutVendedor;
    }

    public void setRutVendedor(int rutVendedor) {
        this.rutVendedor = rutVendedor;
    }

    @XmlTransient
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rutCliente != null ? rutCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.rutCliente == null && other.rutCliente != null) || (this.rutCliente != null && !this.rutCliente.equals(other.rutCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.Cliente[ rutCliente=" + rutCliente + " ]";
    }
    
}
