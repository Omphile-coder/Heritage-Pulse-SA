/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.model.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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

/**
 *
 * @author lucas
 */
@Entity
@Table(name = "REMEDIES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Remedies.findAll", query = "SELECT r FROM Remedies r"),
    @NamedQuery(name = "Remedies.findByRemedyId", query = "SELECT r FROM Remedies r WHERE r.remedyId = :remedyId"),
    @NamedQuery(name = "Remedies.findByIndigenousName", query = "SELECT r FROM Remedies r WHERE r.indigenousName = :indigenousName"),
    @NamedQuery(name = "Remedies.findByScientificName", query = "SELECT r FROM Remedies r WHERE r.scientificName = :scientificName"),
    @NamedQuery(name = "Remedies.findBySafetyDisclaimer", query = "SELECT r FROM Remedies r WHERE r.safetyDisclaimer = :safetyDisclaimer")})
public class Remedies implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "REMEDY_ID")
    private Integer remedyId;
    @Size(max = 100)
    @Column(name = "INDIGENOUS_NAME")
    private String indigenousName;
    @Size(max = 100)
    @Column(name = "SCIENTIFIC_NAME")
    private String scientificName;
    @Lob
    @Size(max = 32700)
    @Column(name = "TRADITIONAL_USAGE")
    private String traditionalUsage;
    @Size(max = 500)
    @Column(name = "SAFETY_DISCLAIMER")
    private String safetyDisclaimer;
    @JoinColumn(name = "TRIBE_ID", referencedColumnName = "TRIBE_ID")
    @ManyToOne
    private Tribes tribeId;

    public Remedies() {
    }

    public Remedies(Integer remedyId) {
        this.remedyId = remedyId;
    }

    public Integer getRemedyId() {
        return remedyId;
    }

    public void setRemedyId(Integer remedyId) {
        this.remedyId = remedyId;
    }

    public String getIndigenousName() {
        return indigenousName;
    }

    public void setIndigenousName(String indigenousName) {
        this.indigenousName = indigenousName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getTraditionalUsage() {
        return traditionalUsage;
    }

    public void setTraditionalUsage(String traditionalUsage) {
        this.traditionalUsage = traditionalUsage;
    }

    public String getSafetyDisclaimer() {
        return safetyDisclaimer;
    }

    public void setSafetyDisclaimer(String safetyDisclaimer) {
        this.safetyDisclaimer = safetyDisclaimer;
    }

    public Tribes getTribeId() {
        return tribeId;
    }

    public void setTribeId(Tribes tribeId) {
        this.tribeId = tribeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (remedyId != null ? remedyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Remedies)) {
            return false;
        }
        Remedies other = (Remedies) object;
        if ((this.remedyId == null && other.remedyId != null) || (this.remedyId != null && !this.remedyId.equals(other.remedyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.ac.tut.model.entity.Remedies[ remedyId=" + remedyId + " ]";
    }
    
}
