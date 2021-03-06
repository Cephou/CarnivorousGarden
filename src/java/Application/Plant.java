/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Aminatha
 */
@Entity
@Table(name = "plant")
@XmlRootElement
@NamedQueries({
      @NamedQuery(name = "Plant.findAll", query = "SELECT p FROM Plant p ORDER BY p.genusPlant, p.speciePlant")
    , @NamedQuery(name = "Plant.getAllGenuses", query = "SELECT DISTINCT p.genusPlant FROM Plant p ORDER BY p.genusPlant, p.speciePlant")
    , @NamedQuery(name = "Plant.getPlantsByGenus", query = "SELECT p FROM Plant p WHERE p.genusPlant = :genusPlant ORDER BY p.genusPlant")    , @NamedQuery(name = "Plant.findByIdPlant", query = "SELECT p FROM Plant p WHERE p.idPlant = :idPlant")
    , @NamedQuery(name = "Plant.findByIdPlant", query = "SELECT p FROM Plant p WHERE p.idPlant = :idPlant")
    , @NamedQuery(name = "Plant.findByGenusPlant", query = "SELECT p FROM Plant p WHERE p.genusPlant = :genusPlant")
    , @NamedQuery(name = "Plant.findBySpeciePlant", query = "SELECT p FROM Plant p WHERE p.speciePlant = :speciePlant")
    , @NamedQuery(name = "Plant.findBySubSpeciePlant", query = "SELECT p FROM Plant p WHERE p.subSpeciePlant = :subSpeciePlant")
    , @NamedQuery(name = "Plant.findByVarietyPlant", query = "SELECT p FROM Plant p WHERE p.varietyPlant = :varietyPlant")
    , @NamedQuery(name = "Plant.findByPricePlant", query = "SELECT p FROM Plant p WHERE p.pricePlant = :pricePlant")
    , @NamedQuery(name = "Plant.findByNumStock", query = "SELECT p FROM Plant p WHERE p.numStock = :numStock")})
public class Plant implements Serializable {

    @ManyToMany(mappedBy = "plantCollection")
    private Collection<Cart> cartCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPlant")
    private Integer idPlant;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "genusPlant")
    private String genusPlant;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "speciePlant")
    private String speciePlant;
    @Size(max = 255)
    @Column(name = "subSpeciePlant")
    private String subSpeciePlant;
    @Size(max = 255)
    @Column(name = "varietyPlant")
    private String varietyPlant;
    @Lob
    @Size(max = 65535)
    @Column(name = "sunCondition")
    private String sunCondition;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pricePlant")
    private float pricePlant;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numStock")
    private int numStock;

    public Plant() {
    }

    public Plant(Integer idPlant) {
        this.idPlant = idPlant;
    }

    public Plant(Integer idPlant, String genusPlant, String speciePlant, float pricePlant, int numStock) {
        this.idPlant = idPlant;
        this.genusPlant = genusPlant;
        this.speciePlant = speciePlant;
        this.pricePlant = pricePlant;
        this.numStock = numStock;
    }

    public Integer getIdPlant() {
        return idPlant;
    }

    public void setIdPlant(Integer idPlant) {
        this.idPlant = idPlant;
    }

    public String getGenusPlant() {
        return genusPlant;
    }

    public void setGenusPlant(String genusPlant) {
        this.genusPlant = genusPlant;
    }

    public String getSpeciePlant() {
        return speciePlant;
    }

    public void setSpeciePlant(String speciePlant) {
        this.speciePlant = speciePlant;
    }

    public String getSubSpeciePlant() {
        return subSpeciePlant;
    }

    public void setSubSpeciePlant(String subSpeciePlant) {
        this.subSpeciePlant = subSpeciePlant;
    }

    public String getVarietyPlant() {
        return varietyPlant;
    }

    public void setVarietyPlant(String varietyPlant) {
        this.varietyPlant = varietyPlant;
    }

    public String getSunCondition() {
        return sunCondition;
    }

    public void setSunCondition(String sunCondition) {
        this.sunCondition = sunCondition;
    }

    public float getPricePlant() {
        return pricePlant;
    }

    public void setPricePlant(float pricePlant) {
        this.pricePlant = pricePlant;
    }

    public int getNumStock() {
        return numStock;
    }

    public void setNumStock(int numStock) {
        this.numStock = numStock;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlant != null ? idPlant.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plant)) {
            return false;
        }
        Plant other = (Plant) object;
        if ((this.idPlant == null && other.idPlant != null) || (this.idPlant != null && !this.idPlant.equals(other.idPlant))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Application.Plant[ idPlant=" + idPlant + " ]";
    }

    @XmlTransient
    public Collection<Cart> getCartCollection() {
        return cartCollection;
    }

    public void setCartCollection(Collection<Cart> cartCollection) {
        this.cartCollection = cartCollection;
    }
    
}
