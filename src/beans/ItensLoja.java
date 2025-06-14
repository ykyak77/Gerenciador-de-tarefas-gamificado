/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ifsp
 */
@Entity
@Table(name = "itens_loja")
@NamedQueries({
    @NamedQuery(name = "ItensLoja.findAll", query = "SELECT i FROM ItensLoja i"),
    @NamedQuery(name = "ItensLoja.findByIdLoja", query = "SELECT i FROM ItensLoja i WHERE i.idLoja = :idLoja"),
    @NamedQuery(name = "ItensLoja.findByNome", query = "SELECT i FROM ItensLoja i WHERE i.nome = :nome"),
    @NamedQuery(name = "ItensLoja.findByPreco", query = "SELECT i FROM ItensLoja i WHERE i.preco = :preco")})
public class ItensLoja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_loja")
    private Integer idLoja;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Lob
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "preco")
    private int preco;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemId")
    private Collection<Inventario> inventarioCollection;

    public ItensLoja() {
    }

    public ItensLoja(Integer idLoja) {
        this.idLoja = idLoja;
    }

    public ItensLoja(Integer idLoja, String nome, int preco) {
        this.idLoja = idLoja;
        this.nome = nome;
        this.preco = preco;
    }

    public Integer getIdLoja() {
        return idLoja;
    }

    public void setIdLoja(Integer idLoja) {
        this.idLoja = idLoja;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public Collection<Inventario> getInventarioCollection() {
        return inventarioCollection;
    }

    public void setInventarioCollection(Collection<Inventario> inventarioCollection) {
        this.inventarioCollection = inventarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLoja != null ? idLoja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItensLoja)) {
            return false;
        }
        ItensLoja other = (ItensLoja) object;
        if ((this.idLoja == null && other.idLoja != null) || (this.idLoja != null && !this.idLoja.equals(other.idLoja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.ItensLoja[ idLoja=" + idLoja + " ]";
    }
    
}
