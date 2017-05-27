package com.example.demo.service;

import com.example.demo.config.ApplicationContextProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Created by yuwang on 5/26/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestfulServiceTest {

    final String BASE_URL = "http://localhost:5000/";

    @Autowired
    private RestfulService controllerToTest;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controllerToTest).build();
        this.controllerToTest = controllerToTest = ApplicationContextProvider.getBean(RestfulService.class);
    }


    @Test
    public void testHello() throws Exception {
        Assert.assertEquals(controllerToTest.hello(), "Hello PayTM!");

    }

    @Test
    public void testSearchLCBO() throws Exception {
        Assert.assertTrue(controllerToTest.searchLCBO("wine", "tom").size() > 0);
    }


}