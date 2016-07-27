package pxc.ree.manager.resources.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pxc.ree.manager.resources.exceptions.AbstractCustomException;
import pxc.ree.manager.resources.exceptions.CustomNotFoundException;
import pxc.ree.manager.resources.model.REESystem;
import pxc.ree.manager.resources.service.REEService;

import java.util.Date;
import java.util.List;

import static java.lang.System.*;

/**
 * Created by iowp01 on 20.07.2016.
 */
@RestController
@RequestMapping("/pxc/ree/system")
public class REESystemController {

    @Autowired
    REEService reeService;

    public REESystemController() {
    }

    public REESystemController(REEService reeService) {
        this.reeService = reeService;
    }

    //origins = {"http://localhost:8080"}, methods = {RequestMethod.GET}

    @RequestMapping(value = "/alive/{timestamp}", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET)
    @CrossOrigin(maxAge=3600)
    ResponseEntity<Long> alive(@PathVariable(value = "timestamp") Long timestamp) throws AbstractCustomException {
        //String resp = "response took " + (System.currentTimeMillis() - timestamp);
        return new ResponseEntity(new Long(currentTimeMillis()), HttpStatus.OK);
    }

    @RequestMapping(value = "/byid/{id}", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET)
    @CrossOrigin(maxAge=3600)
    ResponseEntity<REESystem> getById(@PathVariable(value = "id") String id) throws AbstractCustomException {
        REESystem REESystem = reeService.getSystemById(id);
        if(REESystem != null){
            return new ResponseEntity<>(REESystem, HttpStatus.OK);
        }else{
            return new ResponseEntity(new CustomNotFoundException("no system found for id: " + id), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/byname/{name}", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET)
    @CrossOrigin(maxAge=3600)
    ResponseEntity<REESystem> getByName(@PathVariable(value = "name") String name) throws AbstractCustomException {
        REESystem REESystem = reeService.getSystemByName(name);
        if(REESystem != null){
            return new ResponseEntity<>(REESystem, HttpStatus.OK);
        }else{
            return new ResponseEntity(new CustomNotFoundException("no system found for name: " + name), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/list", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET)
    @CrossOrigin(maxAge=3600)
    ResponseEntity<List<REESystem>> list() throws AbstractCustomException {
        return new ResponseEntity<> (reeService.getAllSystems(), HttpStatus.OK);
    }

    @RequestMapping(value = "/createSystem", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    @CrossOrigin(maxAge=3600)
    ResponseEntity<REESystem> createSystem(@RequestBody REESystem REESystem) throws AbstractCustomException {
        return new ResponseEntity<>(reeService.createSystem(REESystem), HttpStatus.OK);
    }

}
