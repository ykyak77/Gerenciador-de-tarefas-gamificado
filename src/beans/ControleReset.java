/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Kayky Henrique
 */
@Entity
@Table(name = "controle_reset")
@NamedQueries({
    @NamedQuery(name = "ControleReset.findAll", query = "SELECT c FROM ControleReset c"),
    @NamedQuery(name = "ControleReset.findById", query = "SELECT c FROM ControleReset c WHERE c.id = :id"),
    @NamedQuery(name = "ControleReset.findByUltimaExecucao", query = "SELECT c FROM ControleReset c WHERE c.ultimaExecucao = :ultimaExecucao")})
public class ControleReset implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ultima_execucao")
    @Temporal(TemporalType.DATE)
    private Date ultimaExecucao;

    public ControleReset() {
    }

    public ControleReset(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getUltimaExecucao() {
        return ultimaExecucao;
    }

    public void setUltimaExecucao(Date ultimaExecucao) {
        this.ultimaExecucao = ultimaExecucao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ControleReset)) {
            return false;
        }
        ControleReset other = (ControleReset) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.ControleReset[ id=" + id + " ]";
    }
    
}
