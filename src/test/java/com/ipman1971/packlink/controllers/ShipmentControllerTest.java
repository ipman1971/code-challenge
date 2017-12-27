package com.ipman1971.packlink.controllers;

import com.ipman1971.packlink.domain.Shipment;
import com.ipman1971.packlink.services.ShipmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by jcorredera on 24/12/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = ShipmentController.class, secure = false)
public class ShipmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShipmentService service;

    private String shipmentJson = "{\n" +
                                "  \"reference\":\"ABCD123456\",\n" +
                                "  \"parcels\" : [\n" +
                                "  {\n" +
                                "    \"weight\":1,\n" +
                                "    \"width\": 10,\n" +
                                "    \"height\": 10,\n" +
                                "    \"lenght\": 10\n" +
                                "  },\n" +
                                "  {\n" +
                                "    \"weight\":2,\n" +
                                "    \"width\": 20,\n" +
                                "    \"height\": 20,\n" +
                                "    \"lenght\": 20\n" +
                                "  }\n" +
                                "  ]\n" +
                                "}";

    @Test
    public void register() throws Exception {
        when(service.register(any(Shipment.class))).thenReturn("ABCD123456");
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/register")
                .accept(MediaType.APPLICATION_JSON)
                .content(shipmentJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertThat("ABCD123456".getBytes(),is(equalTo(response.getContentAsByteArray())));
    }

}