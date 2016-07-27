package pxc.ree.manager.resources.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by iowp01 on 20.07.2016.
 */

@Entity
@Table(name = "reentry")
public class REEntry {


    @Id
    @Column(name = "propertyId", nullable = false)
    String entryId;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "descr")
    String description;

    @OneToMany(mappedBy = "entryId")
    private Set<REEProperty> reProperties = new HashSet<>();


    public REEntry() {
    }

    public REEntry(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getEntryId() {
        return this.entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<REEProperty> getReProperties() {
        return this.reProperties;
    }

    public void setReProperties(Set<REEProperty> reProperties) {
        this.reProperties = reProperties;
    }
}
