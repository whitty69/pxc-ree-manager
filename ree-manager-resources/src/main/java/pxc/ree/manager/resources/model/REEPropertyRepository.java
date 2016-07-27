package pxc.ree.manager.resources.model;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by iowp01 on 21.07.2016.
 */

public interface REEPropertyRepository extends MongoRepository<REEProperty, String> {


    public REEProperty findByPropertyId(String id);

    public REEProperty findByPropertyName(String name);

    public List<REEProperty> findByEntryId(String entryId);

}
