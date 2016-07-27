package pxc.ree.manager.resources.model;


import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by iowp01 on 21.07.2016.
 */

public interface REESystemRepository extends MongoRepository<REESystem, String> {


    public REESystem findBySystemId(String systemId);

    public REESystem findByName(String name);
}
