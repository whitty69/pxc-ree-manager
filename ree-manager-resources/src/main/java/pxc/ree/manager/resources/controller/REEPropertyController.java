package pxc.ree.manager.resources.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pxc.ree.manager.resources.exceptions.AbstractCustomException;
import pxc.ree.manager.resources.exceptions.CustomNotFoundException;
import pxc.ree.manager.resources.model.REEProperty;
import pxc.ree.manager.resources.service.REEService;

import java.util.List;

/**
 * Created by iowp01 on 20.07.2016.
 */
@RestController
@RequestMapping("/pxc/ree/property")
public class REEPropertyController {

    @Autowired
    REEService reeService;

    public REEPropertyController() {
    }

    public REEPropertyController(REEService reeService) {
        this.reeService = reeService;
    }

    @RequestMapping(value = "/alive/{timestamp}", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET)
    String alive(@PathVariable(value = "timestamp") Long timestamp) throws AbstractCustomException {
        return "response took " + (System.currentTimeMillis() - timestamp);
    }

    @RequestMapping(value = "/byid/{id}", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET)
    ResponseEntity<REEProperty> getById(@PathVariable(value = "id") String id) throws AbstractCustomException {
        REEProperty REEProperty = reeService.getPropertyById(id);
        if(REEProperty != null){
            return new ResponseEntity<>(REEProperty, HttpStatus.OK);
        }else{
            return new ResponseEntity(new CustomNotFoundException("no system found for id: " + id), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/byentryid/{entryid}", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET)
    ResponseEntity<List<REEProperty>> getByEntryId(@PathVariable(value = "entryid") String entryId) throws AbstractCustomException {
        return new ResponseEntity<> (reeService.getAllPropertiesByEntryId(entryId), HttpStatus.OK);
    }

    @RequestMapping(value = "/byname/{name}", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET)
    ResponseEntity<REEProperty> getByName(@PathVariable(value = "name") String name) throws AbstractCustomException {
        REEProperty REEProperty = reeService.getPropertyByName(name);
        if(REEProperty != null){
            return new ResponseEntity<>(REEProperty, HttpStatus.OK);
        }else{
            return new ResponseEntity(new CustomNotFoundException("no system found for name: " + name), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/list", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET)
    ResponseEntity<List<REEProperty>> list() throws AbstractCustomException {
        return new ResponseEntity<> (reeService.getAllProperties(), HttpStatus.OK);
    }



    @RequestMapping(value = "/createProperty", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    ResponseEntity<REEProperty> createProperty(@RequestBody REEProperty REEProperty) throws AbstractCustomException {
        return new ResponseEntity<>(reeService.createProperty(REEProperty), HttpStatus.OK);
    }

}
