/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ifsp
 */
@Entity
@Table(name = "inventario")
@NamedQueries({
    @NamedQuery(name = "Inventario.findAll", query = "SELECT i FROM Inventario i"),
    @NamedQuery(name = "Inventario.findByIdInvetario", query = "SELECT i FROM Inventario i WHERE i.idInvetario = :idInvetario")})
public class Inventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_invetario")
    private Integer idInvetario;
    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios usuarioId;
    @JoinColumn(name = "item_id", referencedColumnName = "id_loja")
    @ManyToOne(optional = false)
    private ItensLoja itemId;

    public Inventario() {
    }

    public Inventario(Integer idInvetario) {
        this.idInvetario = idInvetario;
    }

    public Integer getIdInvetario() {
        return idInvetario;
    }

    public void setIdInvetario(Integer idInvetario) {
        this.idInvetario = idInvetario;
    }

    public Usuarios getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuarios usuarioId) {
        this.usuarioId = usuarioId;
    }

    public ItensLoja getItemId() {
        return itemId;
    }

    public void setItemId(ItensLoja itemId) {
        this.itemId = itemId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInvetario != null ? idInvetario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventario)) {
            return false;
        }
        Inventario other = (Inventario) object;
        if ((this.idInvetario == null && other.idInvetario != null) || (this.idInvetario != null && !this.idInvetario.equals(other.idInvetario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Inventario[ idInvetario=" + idInvetario + " ]";
    }
    
}
