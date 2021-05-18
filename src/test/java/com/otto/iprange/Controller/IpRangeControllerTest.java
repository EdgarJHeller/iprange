package com.otto.iprange.Controller;

import com.otto.iprange.Service.IpRangeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(IpRangeController.class)
class IpRangeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IpRangeService ipRangeService;

    @Test
    void noValidRegionForHH() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("http://localhost:8080/?region=CN");
        MvcResult result = mvc.perform(request).andReturn();
        //assertEquals("no valid region", result.getResponse().getContentAsString());
        assertEquals("no valid region", result.getResponse().getContentAsString());
    }

    @Test
    void mimeTypeIsText() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("http://localhost:8080/?region=CN");
        MvcResult result = mvc.perform(request).andReturn();
        String type = result.getResponse().getHeader("Content-Type");
        assertEquals(type, MediaType.TEXT_PLAIN_VALUE);
    }

    @Test
    void getIpRangesForAllRegionForNoInput() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/?region=");
        MvcResult result = mvc.perform(request).andReturn();
        assertNotEquals("no valid region", result.getResponse().getContentAsString());
    }

    @Test
    void bodyNotNullForValidRegion() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/?region=CN");
        MvcResult result = mvc.perform(request).andReturn();
        assertNotNull(result.getResponse().getContentAsString());
    }

    @Test
    void bodyNotNullForNoSpecifiedRegion() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/?region=");
        MvcResult result = mvc.perform(request).andReturn();
        assertNotNull(result.getResponse().getContentAsString());
    }

}