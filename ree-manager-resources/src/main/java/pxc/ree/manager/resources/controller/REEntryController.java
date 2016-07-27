package pxc.ree.manager.resources.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pxc.ree.manager.resources.exceptions.AbstractCustomException;
import pxc.ree.manager.resources.exceptions.CustomNotFoundException;
import pxc.ree.manager.resources.model.REEntry;
import pxc.ree.manager.resources.service.REEService;

import java.util.List;

import static java.lang.System.currentTimeMillis;

/**
 * Created by iowp01 on 20.07.2016.
 */
@RestController
@RequestMapping("/pxc/ree/entry")
public class REEntryController {

    @Autowired
    REEService reeService;

    public REEntryController() {
    }

    public REEntryController(REEService reeService) {
        this.reeService = reeService;
    }

    @RequestMapping(value = "/alive/{timestamp}", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET)
    String alive(@PathVariable(value = "timestamp") Long timestamp) throws AbstractCustomException {
        return "response took " + (currentTimeMillis() - timestamp);
    }

    @RequestMapping(value = "/byid/{id}", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET)
    ResponseEntity<REEntry> getById(@PathVariable(value = "id") String id) throws AbstractCustomException {
        REEntry REEntry = reeService.getEntryById(id);
        if(REEntry != null){
            return new ResponseEntity<>(REEntry, HttpStatus.OK);
        }else{
            return new ResponseEntity(new CustomNotFoundException("no system found for id: " + id), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/byname/{name}", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET)
    ResponseEntity<REEntry> getByName(@PathVariable(value = "name") String name) throws AbstractCustomException {
        REEntry REEntry = reeService.getEntryByName(name);
        if(REEntry != null){
            return new ResponseEntity<>(REEntry, HttpStatus.OK);
        }else{
            return new ResponseEntity(new CustomNotFoundException("no system found for name: " + name), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/list", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET)
    ResponseEntity<List<REEntry>> list() throws AbstractCustomException {
        return new ResponseEntity<> (reeService.getAllEntries(), HttpStatus.OK);
    }



    @RequestMapping(value = "/createEntry", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    ResponseEntity<REEntry> createEntry(@RequestBody REEntry REEntry) throws AbstractCustomException {
        return new ResponseEntity<>(reeService.createEntry(REEntry), HttpStatus.OK);
    }

}
