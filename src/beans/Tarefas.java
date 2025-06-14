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
@Table(name = "tarefas")
@NamedQueries({
    @NamedQuery(name = "Tarefas.findAll", query = "SELECT t FROM Tarefas t"),
    @NamedQuery(name = "Tarefas.findByIdTarefas", query = "SELECT t FROM Tarefas t WHERE t.idTarefas = :idTarefas"),
    @NamedQuery(name = "Tarefas.findByDescricao", query = "SELECT t FROM Tarefas t WHERE t.descricao = :descricao"),
    @NamedQuery(name = "Tarefas.findByRecompensa", query = "SELECT t FROM Tarefas t WHERE t.recompensa = :recompensa"),
    @NamedQuery(name = "Tarefas.findByConcluido", query = "SELECT t FROM Tarefas t WHERE t.concluido = :concluido")})
public class Tarefas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tarefas")
    private Integer idTarefas;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "recompensa")
    private Integer recompensa;
    @Column(name = "concluido")
    private Boolean concluido;
    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios usuarioId;

    public Tarefas() {
    }

    public Tarefas(Integer idTarefas) {
        this.idTarefas = idTarefas;
    }

    public Tarefas(Integer idTarefas, String descricao) {
        this.idTarefas = idTarefas;
        this.descricao = descricao;
    }

    public Integer getIdTarefas() {
        return idTarefas;
    }

    public void setIdTarefas(Integer idTarefas) {
        this.idTarefas = idTarefas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(Integer recompensa) {
        this.recompensa = recompensa;
    }

    public Boolean getConcluido() {
        return concluido;
    }

    public void setConcluido(Boolean concluido) {
        this.concluido = concluido;
    }

    public Usuarios getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuarios usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTarefas != null ? idTarefas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarefas)) {
            return false;
        }
        Tarefas other = (Tarefas) object;
        if ((this.idTarefas == null && other.idTarefas != null) || (this.idTarefas != null && !this.idTarefas.equals(other.idTarefas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Tarefas[ idTarefas=" + idTarefas + " ]";
    }
    
}
