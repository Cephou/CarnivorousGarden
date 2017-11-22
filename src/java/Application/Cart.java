/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Laurent
 */
@Entity
@Table(name = "cart")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cart.findAll", query = "SELECT c FROM Cart c ORDER BY c.dateCart ASC")
    , @NamedQuery(name = "Cart.findByIdCart", query = "SELECT c FROM Cart c WHERE c.idCart = :idCart")
    , @NamedQuery(name = "Cart.findByMailCart", query = "SELECT c FROM Cart c WHERE c.mailCart = :mailCart")
    , @NamedQuery(name = "Cart.findByNameCart", query = "SELECT c FROM Cart c WHERE c.nameCart = :nameCart")
    , @NamedQuery(name = "Cart.findByDateCart", query = "SELECT c FROM Cart c WHERE c.dateCart = :dateCart")})
public class Cart implements Serializable {

    @JoinTable(name = "cartlist", joinColumns = {
        @JoinColumn(name = "idCart", referencedColumnName = "idCart")}, inverseJoinColumns = {
        @JoinColumn(name = "idPlant", referencedColumnName = "idPlant")})
    @ManyToMany
    private Collection<Plant> plantCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCart")
    private Integer idCart;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "mailCart")
    private String mailCart;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nameCart")
    private String nameCart;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateCart")
    @Temporal(TemporalType.DATE)
    private Date dateCart;

    public Cart() {
    }

    public Cart(Integer idCart) {
        this.idCart = idCart;
    }

    public Cart(Integer idCart, String mailCart, String nameCart, Date dateCart) {
        this.idCart = idCart;
        this.mailCart = mailCart;
        this.nameCart = nameCart;
        this.dateCart = dateCart;
    }

    public Integer getIdCart() {
        return idCart;
    }

    public void setIdCart(Integer idCart) {
        this.idCart = idCart;
    }

    public String getMailCart() {
        return mailCart;
    }

    public void setMailCart(String mailCart) {
        this.mailCart = mailCart;
    }

    public String getNameCart() {
        return nameCart;
    }

    public void setNameCart(String nameCart) {
        this.nameCart = nameCart;
    }

    public Date getDateCart() {
        return dateCart;
    }

    public void setDateCart(Date dateCart) {
        this.dateCart = dateCart;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCart != null ? idCart.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cart)) {
            return false;
        }
        Cart other = (Cart) object;
        if ((this.idCart == null && other.idCart != null) || (this.idCart != null && !this.idCart.equals(other.idCart))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Application.Cart[ idCart=" + idCart + " ]";
    }

    @XmlTransient
    public Collection<Plant> getPlantCollection() {
        return plantCollection;
    }

    public void setPlantCollection(Collection<Plant> plantCollection) {
        this.plantCollection = plantCollection;
    }
    
}
