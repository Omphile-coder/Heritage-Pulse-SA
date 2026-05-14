/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.model.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "ARCHIVE_ITEMS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ArchiveItems.findAll", query = "SELECT a FROM ArchiveItems a"),
    @NamedQuery(name = "ArchiveItems.findByItemId", query = "SELECT a FROM ArchiveItems a WHERE a.itemId = :itemId"),
    @NamedQuery(name = "ArchiveItems.findByTitle", query = "SELECT a FROM ArchiveItems a WHERE a.title = :title"),
    @NamedQuery(name = "ArchiveItems.findByCategory", query = "SELECT a FROM ArchiveItems a WHERE a.category = :category"),
    @NamedQuery(name = "ArchiveItems.findByProvince", query = "SELECT a FROM ArchiveItems a WHERE a.province = :province")})
public class ArchiveItems implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ITEM_ID")
    private Integer itemId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "TITLE")
    private String title;
    
    @Lob
    @Size(max = 32700)
    
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 50)
    
    @Column(name = "CATEGORY")
    private String category;
    @Size(max = 50)
    
    @Column(name = "PROVINCE")
    private String province;
    
    @Column(name = "FILE_PATH")
    private String filePath;

    @Column(name = "STATUS")
    private String status;
    
    
    @JoinColumn(name = "TRIBE_ID", referencedColumnName = "TRIBE_ID")
    @ManyToOne
    private Tribes tribeId;

    public ArchiveItems() {
    }

    public ArchiveItems(Integer itemId) {
        this.itemId = itemId;
    }

    public ArchiveItems(Integer itemId, String title) {
        this.itemId = itemId;
        this.title = title;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Tribes getTribeId() {
        return tribeId;
    }

    public void setTribeId(Tribes tribeId) {
        this.tribeId = tribeId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemId != null ? itemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArchiveItems)) {
            return false;
        }
        ArchiveItems other = (ArchiveItems) object;
        if ((this.itemId == null && other.itemId != null) || (this.itemId != null && !this.itemId.equals(other.itemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.ac.tut.model.entity.ArchiveItems[ itemId=" + itemId + " ]";
    }
    
}
