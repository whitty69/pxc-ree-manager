package pxc.ree.manager.resources.model;

import javax.persistence.*;

/**
 * Created by iowp01 on 21.07.2016.
 */

@Entity()
@Table(name = "reprop")
public class REEProperty {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    String propertyId;

    @Column(name = "propname", nullable = false)
    String propertyName;

    // value of the property
    @Column(name = "propvalue", nullable = false)
    String propertyValue;

    // parent Id
    @Column(name = "entryId", nullable = false)
    String entryId;


    // for jpa
    public REEProperty() {
    }

    public REEProperty(String propertyName, String propertyValue, String entryId) {
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
        this.entryId = entryId;
    }

    public String getPropertyId() {
        return this.propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyName() {
        return this.propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyValue() {
        return this.propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    public String getEntryId() {
        return this.entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }
}
