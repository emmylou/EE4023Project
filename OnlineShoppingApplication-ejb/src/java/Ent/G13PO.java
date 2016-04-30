/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ent;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Fierce
 */
@Entity
@Table(name = "G13PO")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "G13PO.getHighestPurchaseId", query = "SELECT MAX(p.purchaseOrderId) FROM G13PO p"),
    @NamedQuery(name = "G13PO.findByQuantity", query = "SELECT p FROM G13PO p WHERE p.quantity = :quantity")})
public class G13PO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PurchaseOrderId")
    private Integer purchaseOrderId;
     //username
    //@Size(max = 30)
    @Column(name = "quantity")
    private int quantity;
    
    //password
    @Size(max = 30)
    @Column(name = "userID")
    private long userID;
    
    //password
    @Size(max = 300)
    @Column(name = "productid")
    private int productid;
 
    //address
    @Size(max = 30)
    @Column(name = "company")
    private String company;
    
    //usertype: cutomer/admin
    @Size(max = 30)
    @Column(name = "orderDate")
    //@Temporal(TemporalType.DATE)
    private String orderDate;
    
   
    public G13PO(int id)
    {
        this.purchaseOrderId = id;
    }
    
    public G13PO()
    {
    }
    
    public Integer getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Integer purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    
    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (purchaseOrderId != null ? purchaseOrderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof G13PO)) {
            return false;
        }
        G13PO other = (G13PO) object;
        if ((this.purchaseOrderId == null && other.purchaseOrderId != null) || (this.purchaseOrderId != null && !this.purchaseOrderId.equals(other.purchaseOrderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ent.G13PO[ id=" + purchaseOrderId + " ]";
    }
    
}
