package pxc.ree.manager.resources.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pxc.ree.manager.resources.exceptions.CustomNotFoundException;
import pxc.ree.manager.resources.model.*;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.UUID;

/**
 * Created by iowp01 on 20.07.2016.
 */

@Service
public class REEService {

    @Autowired
    REESystemRepository systemRepository;

    @Autowired
    REEntryRepository entryRepository;

    @Autowired
    REEPropertyRepository propertyRepository;
    // logger
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * The method gets the entry based on the Id
     *
     * @param id
     * @return the found {link:ResEnvEntry}
     * @throws CustomNotFoundException
     */
    public REEntry getById(String id) throws CustomNotFoundException {

        // output the id in the logging console
        LOGGER.debug("getById (id:" + id + ")");
        //TODO: get the entry from a repository
        REEntry entry = new REEntry();
        entry.setEntryId(id);
        //if we got an entry return it
        if (entry != null) {
            return entry;
        }
        // else throw a not found exception
        throw new CustomNotFoundException("no entry found for id {" + id + "}");
    }

    public REESystem createSystem(REESystem system) {

        if (system.getSystemId() == null) {
            system.setSystemId(UUID.randomUUID().toString());
        }
        final REESystem REESystem = systemRepository.save(system);

        return REESystem;

    }


    public REEntry createEntry(REEntry entry) {

        if (entry.getEntryId() == null) {
            entry.setEntryId(UUID.randomUUID().toString());
        }
        final REEntry REEntry = entryRepository.save(entry);

        return REEntry;

    }
    public void setSystemRepository(REESystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    public void setEntryRepository(REEntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public List<REESystem> getAllSystems() {
        return systemRepository.findAll();
    }


    public REESystem getSystemById(String id) {
            return systemRepository.findBySystemId(id);
    }

    public REESystem getSystemByName(String name) {
            return systemRepository.findByName(name);
    }

    public List<REEntry> getAllEntries() {
        return entryRepository.findAll();
    }


    public REEntry getEntryById(String id) {
        return entryRepository.findByEntryId(id);
    }

    public REEntry getEntryByName(String key) {
        return entryRepository.findByName(key);
    }

    public REEProperty getPropertyById(String id) {
        return propertyRepository.findByPropertyId(id);
    }

    public REEProperty getPropertyByName(String name) {
        return propertyRepository.findByPropertyName(name);
    }

    public List<REEProperty> getAllProperties() {
        return propertyRepository.findAll();
    }

    public List<REEProperty> getAllPropertiesByEntryId(String entryId) {
        return propertyRepository.findByEntryId(entryId);
    }

    public REEProperty createProperty(REEProperty reeProperty) {
        if (reeProperty.getPropertyId() == null) {
            reeProperty.setPropertyId(UUID.randomUUID().toString());
        }
        return propertyRepository.save(reeProperty);
    }

    public void purge() {
        propertyRepository.deleteAll();
        entryRepository.deleteAll();
        systemRepository.deleteAll();
    }

    public void setPropertyRepository(REEPropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }
}
