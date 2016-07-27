package pxc.ree.manager.resources.model;


import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by iowp01 on 21.07.2016.
 */

public interface REEntryRepository extends MongoRepository<REEntry, String> {


    public REEntry findByEntryId(String entryId);

    public REEntry findByName(String name);
}
