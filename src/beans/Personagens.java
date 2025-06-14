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
@Table(name = "personagens")
@NamedQueries({
    @NamedQuery(name = "Personagens.findAll", query = "SELECT p FROM Personagens p"),
    @NamedQuery(name = "Personagens.findByIdPersonagem", query = "SELECT p FROM Personagens p WHERE p.idPersonagem = :idPersonagem"),
    @NamedQuery(name = "Personagens.findByFoco", query = "SELECT p FROM Personagens p WHERE p.foco = :foco"),
    @NamedQuery(name = "Personagens.findByAgilidade", query = "SELECT p FROM Personagens p WHERE p.agilidade = :agilidade"),
    @NamedQuery(name = "Personagens.findByInteligencia", query = "SELECT p FROM Personagens p WHERE p.inteligencia = :inteligencia"),
    @NamedQuery(name = "Personagens.findByEnergia", query = "SELECT p FROM Personagens p WHERE p.energia = :energia"),
    @NamedQuery(name = "Personagens.findByPreco", query = "SELECT p FROM Personagens p WHERE p.preco = :preco")})
public class Personagens implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_personagem")
    private Integer idPersonagem;
    @Column(name = "foco")
    private Integer foco = 0;
    @Column(name = "agilidade")
    private Integer agilidade = 0;
    @Column(name = "inteligencia")
    private Integer inteligencia=0;
    @Column(name = "energia")
    private Integer energia = 0;
    @Column(name = "preco")
    private Integer preco = 150;
    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios usuarioId;

    public Personagens() {
    }

    public Personagens(Integer idPersonagem) {
        this.idPersonagem = idPersonagem;
    }

    public Integer getIdPersonagem() {
        return idPersonagem;
    }

    public void setIdPersonagem(Integer idPersonagem) {
        this.idPersonagem = idPersonagem;
    }

    public Integer getFoco() {
        return foco;
    }

    public void setFoco(Integer foco) {
        this.foco = foco;
    }

    public Integer getAgilidade() {
        return agilidade;
    }

    public void setAgilidade(Integer agilidade) {
        this.agilidade = agilidade;
    }

    public Integer getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(Integer inteligencia) {
        this.inteligencia = inteligencia;
    }

    public Integer getEnergia() {
        return energia;
    }

    public void setEnergia(Integer energia) {
        this.energia = energia;
    }

    public Integer getPreco() {
        return preco;
    }

    public void setPreco(Integer preco) {
        this.preco = preco;
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
        hash += (idPersonagem != null ? idPersonagem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personagens)) {
            return false;
        }
        Personagens other = (Personagens) object;
        if ((this.idPersonagem == null && other.idPersonagem != null) || (this.idPersonagem != null && !this.idPersonagem.equals(other.idPersonagem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Personagens[ idPersonagem=" + idPersonagem + " ]";
    }
    
}
