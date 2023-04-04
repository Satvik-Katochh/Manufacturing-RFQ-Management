package com.mfpe.rfq.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.mfpe.rfq.model.RfqDetail;
import com.mfpe.rfq.model.Supplier;
import com.mfpe.rfq.service.RfqService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class RfqControllerTest {
    @InjectMocks
    private RfqController rfqController;

    @Mock
    private RfqService rfqService;

    /**
     * Method under test: {@link RfqController#getRfqOfPlant(int, String)}
     */
    @Test
    void testGetRfqOfPlant() throws Exception {
        RfqDetail rfqDetail = new RfqDetail();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        rfqDetail.setExpectedSupplyDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        rfqDetail.setPartId(123);
        rfqDetail.setPartName("Part Name");
        rfqDetail.setQuantity(1);
        rfqDetail.setRfqId(123);
        rfqDetail.setSpecification("Specification");
        when(rfqService.getRfqOfPlant(anyInt(), (String) any())).thenReturn(rfqDetail);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getrfqofplant/{partId}", 123)
                .header("Authorization", "Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==");
        MockMvcBuilders.standaloneSetup(rfqController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"rfqId\":123,\"partId\":123,\"partName\":\"Part Name\",\"expectedSupplyDate\":0,\"specification\":\"Specification"
                                        + "\",\"quantity\":1}"));
    }

    /**
     * Method under test: {@link RfqController#getPotentialVendorsOfRfq(int, String)}
     */
    @Test
    void testGetPotentialVendorsOfRfq() throws Exception {
        when(rfqService.getPotentialVendorsOfRfq(anyInt(), (String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getpotentialvendorsofrfq/{rfqId}", 123)
                .header("Authorization", "Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(rfqController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link RfqController#getPotentialVendorsOfRfq(int, String)}
     */
    @Test
    void testGetPotentialVendorsOfRfq1() throws Exception {
        ArrayList<Supplier> supplierList = new ArrayList<>();
        supplierList.add(new Supplier());
        when(rfqService.getPotentialVendorsOfRfq(anyInt(), (String) any())).thenReturn(supplierList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/getpotentialvendorsofrfq/{rfqId}", 123)
                .header("Authorization", "Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==");
        MockMvcBuilders.standaloneSetup(rfqController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"email\":null,\"phone\":null,\"feedback\":0,\"location\":null,\"supplierName\":null,\"supplierId\":null}]"));
    }
}

