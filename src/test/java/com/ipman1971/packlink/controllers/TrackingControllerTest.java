package com.ipman1971.packlink.controllers;

import com.ipman1971.packlink.domain.Tracking;
import com.ipman1971.packlink.services.TrackingService;
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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

/**
 * Created by jcorredera on 24/12/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = TrackingController.class, secure = false)
public class TrackingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrackingService trackingService;

    private String trackingJson = "{\n" +
                                    "  \"status\":\"WAITING_IN_HUB\",\n" +
                                    "  \"parcels\":2,\n" +
                                    "  \"weight\":null,\n" +
                                    "  \"reference\":\"ABCD123456\"\n" +
                                    "}";

    @Test
    public void push() throws Exception {
        when(trackingService.push(any(Tracking.class))).thenReturn("ABCD123456");
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/push")
                .accept(MediaType.APPLICATION_JSON)
                .content(trackingJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertThat("ABCD123456".getBytes(),is(equalTo(response.getContentAsByteArray())));
    }

}