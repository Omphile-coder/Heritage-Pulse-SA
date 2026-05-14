/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.model.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
 * @author lucas
 */
@Entity
@Table(name = "TRIBES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tribes.findAll", query = "SELECT t FROM Tribes t"),
    @NamedQuery(name = "Tribes.findByTribeId", query = "SELECT t FROM Tribes t WHERE t.tribeId = :tribeId"),
    @NamedQuery(name = "Tribes.findByTribeName", query = "SELECT t FROM Tribes t WHERE t.tribeName = :tribeName")})
public class Tribes implements Serializable {

    @OneToMany(mappedBy = "followedTribeId")
    private Collection<Users> usersCollection;

    @OneToMany(mappedBy = "tribeId")
    private Collection<ArchiveItems> archiveItemsCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRIBE_ID")
    private Integer tribeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TRIBE_NAME")
    private String tribeName;
    @OneToMany(mappedBy = "tribeId")
    private Collection<Remedies> remediesCollection;
    @OneToMany(mappedBy = "tribeId")
    private Collection<Events> eventsCollection;

    public Tribes() {
    }

    public Tribes(Integer tribeId) {
        this.tribeId = tribeId;
    }

    public Tribes(Integer tribeId, String tribeName) {
        this.tribeId = tribeId;
        this.tribeName = tribeName;
    }

    public Integer getTribeId() {
        return tribeId;
    }

    public void setTribeId(Integer tribeId) {
        this.tribeId = tribeId;
    }

    public String getTribeName() {
        return tribeName;
    }

    public void setTribeName(String tribeName) {
        this.tribeName = tribeName;
    }

    @XmlTransient
    public Collection<Remedies> getRemediesCollection() {
        return remediesCollection;
    }

    public void setRemediesCollection(Collection<Remedies> remediesCollection) {
        this.remediesCollection = remediesCollection;
    }

    @XmlTransient
    public Collection<Events> getEventsCollection() {
        return eventsCollection;
    }

    public void setEventsCollection(Collection<Events> eventsCollection) {
        this.eventsCollection = eventsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tribeId != null ? tribeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tribes)) {
            return false;
        }
        Tribes other = (Tribes) object;
        if ((this.tribeId == null && other.tribeId != null) || (this.tribeId != null && !this.tribeId.equals(other.tribeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.ac.tut.model.entity.Tribes[ tribeId=" + tribeId + " ]";
    }

    @XmlTransient
    public Collection<ArchiveItems> getArchiveItemsCollection() {
        return archiveItemsCollection;
    }

    public void setArchiveItemsCollection(Collection<ArchiveItems> archiveItemsCollection) {
        this.archiveItemsCollection = archiveItemsCollection;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }
    
}
