package pxc.ree.manager.resources.tests;


/**
 * Created by iowp01 on 19.07.2016.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pxc.ree.manager.resources.REEManagerResourceApp;
import pxc.ree.manager.resources.controller.REEPropertyController;
import pxc.ree.manager.resources.controller.REEntryController;
import pxc.ree.manager.resources.model.REEProperty;
import pxc.ree.manager.resources.model.REEPropertyRepository;
import pxc.ree.manager.resources.model.REEntry;
import pxc.ree.manager.resources.service.REEService;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {REEManagerResourceApp.class})
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class REEPropertyControllerTest {

    private static final String URL_PREFIX = "/pxc/ree/property/";
   
    private MockMvc mvc;

    @Autowired
    REEService reeService;

    @Autowired
    REEPropertyRepository propertyRepository;



    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new REEPropertyController(reeService)).build();
        reeService.setPropertyRepository(propertyRepository);
        propertyRepository.deleteAll();
    }

    @Test
    public void testAlive() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(URL_PREFIX + "alive/" + System.currentTimeMillis()).accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateAndGet() throws Exception {

        String id = UUID.randomUUID().toString();
        REEProperty reeProperty = new REEProperty("name", "descr", id);
        reeProperty.setPropertyId(id);

        ObjectMapper mapper = new ObjectMapper();

        mvc.perform(MockMvcRequestBuilders.post(URL_PREFIX + "createProperty").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(reeProperty)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.propertyId", is(id)));

        mvc.perform(MockMvcRequestBuilders.get(URL_PREFIX + "byid/" + id).accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.propertyId", is(id)));

        mvc.perform(MockMvcRequestBuilders.get(URL_PREFIX + "byname/name").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.propertyName", is("name")));

    }

    @Test
    public void testNotFound() throws Exception {

        String id = UUID.randomUUID().toString();

        mvc.perform(MockMvcRequestBuilders.get(URL_PREFIX + "byid/" + id).accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());

    }
}