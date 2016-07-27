package pxc.ree.manager.resources.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by iowp01 on 21.07.2016.
 */

@Entity
@Table(name = "resystems")
public class REESystem {

    @Id
    String systemId;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "descr")
    String description;

    @Column(name = "targetDBSchema", nullable = false)
    String targetDBSchema;

    public REESystem() {
    }

    public REESystem(String name, String description, String targetDBSchema) {
        this.name = name;
        this.description = description;
        this.targetDBSchema = targetDBSchema;
    }

    public String getSystemId() {
        return this.systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
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

    public String getTargetDBSchema() {
        return this.targetDBSchema;
    }

    public void setTargetDBSchema(String targetDBSchema) {
        this.targetDBSchema = targetDBSchema;
    }
}
