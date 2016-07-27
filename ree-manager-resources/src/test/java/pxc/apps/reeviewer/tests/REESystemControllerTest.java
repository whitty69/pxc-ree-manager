package pxc.ree.manager.resources.tests; /**
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
import pxc.ree.manager.resources.controller.REESystemController;
import pxc.ree.manager.resources.model.REESystem;
import pxc.ree.manager.resources.model.REESystemRepository;
import pxc.ree.manager.resources.service.REEService;

import java.nio.charset.Charset;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {REEManagerResourceApp.class})
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class REESystemControllerTest {

    private static final String URL_PREFIX = "/pxc/ree/system/";
    private static final String URL_TEMPLATE_SYS_CREATE = URL_PREFIX + "create?name=testsystem&desrc=testdescr&targetDBName=testDBName&id=";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mvc;

    @Autowired
    REEService reeService;

    @Autowired
    REESystemRepository systemRepository;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new REESystemController(reeService)).build();
        reeService.setSystemRepository(systemRepository);
        systemRepository.deleteAll();
    }

    @Test
    public void testAlive() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(URL_PREFIX + "alive/" + System.currentTimeMillis()).accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateAndGet() throws Exception {

        String id = UUID.randomUUID().toString();
        String testName = "test";

        REESystem REESystem = new REESystem(testName, "descr", "targetDBS");
        REESystem.setSystemId(id);

        ObjectMapper mapper = new ObjectMapper();

        mvc.perform(MockMvcRequestBuilders.post(URL_PREFIX + "createSystem").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(REESystem)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.systemId", is(id)));

        mvc.perform(MockMvcRequestBuilders.get(URL_PREFIX + "byid/" + id).accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.systemId", is(id)));

        mvc.perform(MockMvcRequestBuilders.get(URL_PREFIX + "byname/" + testName).accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name", is(testName)));

    }

    @Test
    public void testNotFound() throws Exception {

        String id = UUID.randomUUID().toString();

        mvc.perform(MockMvcRequestBuilders.get(URL_PREFIX + "byid/" + id).accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());

    }
}