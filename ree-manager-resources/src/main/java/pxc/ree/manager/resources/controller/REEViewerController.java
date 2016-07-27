package pxc.ree.manager.resources.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pxc.ree.manager.resources.exceptions.AbstractCustomException;
import pxc.ree.manager.resources.model.REEntry;
import pxc.ree.manager.resources.service.REEService;

/**
 * Created by iowp01 on 20.07.2016.
 */
@RestController
@RequestMapping("/pxc/ree/viewer")
public class REEViewerController {

    @Autowired
    REEService reeService;

    public REEViewerController() {
    }

    public REEViewerController(REEService reeService) {
        this.reeService = reeService;
    }

    @RequestMapping(value = "/alive/{timestamp}", produces = {"application/json"}, method = RequestMethod.GET)
    String alive(@PathVariable(value = "timestamp") Long timestamp) throws AbstractCustomException {
        return "response took " + (System.currentTimeMillis() - timestamp);
    }

    @RequestMapping(value = "/byid/{id}", produces = {"application/json"}, method = RequestMethod.GET)
    @ResponseBody
    REEntry getById(@PathVariable(value = "id") String id) throws AbstractCustomException {
        return reeService.getById(id);
    }

}
