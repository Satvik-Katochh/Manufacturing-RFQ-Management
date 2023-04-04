package com.project.Supplier.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Supplier.entity.Supplier;
import com.project.Supplier.entity.SupplierPart;
import com.project.Supplier.entity.SupplierPartModel;
import com.project.Supplier.service.SupplierService;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {SupplierController.class})
@ExtendWith(SpringExtension.class)
class SupplierControllerTest {
    @Autowired
    private SupplierController supplierController;

    @MockBean
    private SupplierService supplierService;

    /**
     * Method under test: {@link SupplierController#addSupplierPart(SupplierPartModel)}
     */
    @Test
    void testAddSupplierPart() throws Exception {
        Date date = mock(Date.class);
        when(date.getTime()).thenReturn(10L);

        Supplier supplier = new Supplier();
        supplier.setEmail("jane.doe@example.org");
        supplier.setFeedback(1);
        supplier.setLocation("Location");
        supplier.setPhone("4105551212");
        supplier.setSupplierId(123);
        supplier.setSupplierName("Supplier Name");

        SupplierPart supplierPart = new SupplierPart();
        supplierPart.setDeliveryDate(date);
        supplierPart.setPartId(123);
        supplierPart.setQuantitiy(1);
        supplierPart.setSupplier(supplier);
        supplierPart.setSupplierPartId(123);
        supplierPart.setSupplierPartName("Supplier Part Name");
        when(supplierService.addSupplier((SupplierPartModel) any())).thenReturn(supplierPart);
        Date date1 = mock(Date.class);
        when(date1.getTime()).thenReturn(10L);

        SupplierPartModel supplierPartModel = new SupplierPartModel();
        supplierPartModel.setDeliveryDate(date1);
        supplierPartModel.setEmail("jane.doe@example.org");
        supplierPartModel.setFeedback(1);
        supplierPartModel.setLocation("Location");
        supplierPartModel.setPartId(123);
        supplierPartModel.setPhone("4105551212");
        supplierPartModel.setQuantitiy(1);
        supplierPartModel.setSupplierId(123);
        supplierPartModel.setSupplierName("Supplier Name");
        supplierPartModel.setSupplierPartId(123);
        supplierPartModel.setSupplierPartName("Supplier Part Name");
        String content = (new ObjectMapper()).writeValueAsString(supplierPartModel);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addSupplier")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(supplierController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"supplierPartId\":123,\"partId\":123,\"supplierPartName\":\"Supplier Part Name\",\"quantitiy\":1,\"deliveryDate"
                                        + "\":10,\"supplier\":{\"email\":\"jane.doe@example.org\",\"phone\":\"4105551212\",\"feedback\":1,\"location\":\"Location"
                                        + "\",\"supplierName\":\"Supplier Name\",\"supplierId\":123}}"));
    }

    /**
     * Method under test: {@link SupplierController#editSupplier(SupplierPartModel)}
     */
    @Test
    void testEditSupplier() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setEmail("jane.doe@example.org");
        supplier.setFeedback(1);
        supplier.setLocation("Location");
        supplier.setPhone("4105551212");
        supplier.setSupplierId(123);
        supplier.setSupplierName("Supplier Name");
        when(supplierService.editSupplier((SupplierPartModel) any())).thenReturn(supplier);
        Date date = mock(Date.class);
        when(date.getTime()).thenReturn(10L);

        SupplierPartModel supplierPartModel = new SupplierPartModel();
        supplierPartModel.setDeliveryDate(date);
        supplierPartModel.setEmail("jane.doe@example.org");
        supplierPartModel.setFeedback(1);
        supplierPartModel.setLocation("Location");
        supplierPartModel.setPartId(123);
        supplierPartModel.setPhone("4105551212");
        supplierPartModel.setQuantitiy(1);
        supplierPartModel.setSupplierId(123);
        supplierPartModel.setSupplierName("Supplier Name");
        supplierPartModel.setSupplierPartId(123);
        supplierPartModel.setSupplierPartName("Supplier Part Name");
        String content = (new ObjectMapper()).writeValueAsString(supplierPartModel);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/editSupplier")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(supplierController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"email\":\"jane.doe@example.org\",\"phone\":\"4105551212\",\"feedback\":1,\"location\":\"Location\",\"supplierName"
                                        + "\":\"Supplier Name\",\"supplierId\":123}"));
    }

    /**
     * Method under test: {@link SupplierController#getSupplierPartById(int)}
     */
    @Test
    void testGetSupplierPartById() throws Exception {
        when(supplierService.findByPartId(anyInt())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/viewsupplierofart/{id}", 1);
        MockMvcBuilders.standaloneSetup(supplierController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link SupplierController#updateFeedback(SupplierPartModel)}
     */
    @Test
    void testUpdateFeedback() throws Exception {
        doNothing().when(supplierService).updateFeedback((SupplierPartModel) any());
        Date date = mock(Date.class);
        when(date.getTime()).thenReturn(10L);

        SupplierPartModel supplierPartModel = new SupplierPartModel();
        supplierPartModel.setDeliveryDate(date);
        supplierPartModel.setEmail("jane.doe@example.org");
        supplierPartModel.setFeedback(1);
        supplierPartModel.setLocation("Location");
        supplierPartModel.setPartId(123);
        supplierPartModel.setPhone("4105551212");
        supplierPartModel.setQuantitiy(1);
        supplierPartModel.setSupplierId(123);
        supplierPartModel.setSupplierName("Supplier Name");
        supplierPartModel.setSupplierPartId(123);
        supplierPartModel.setSupplierPartName("Supplier Part Name");
        String content = (new ObjectMapper()).writeValueAsString(supplierPartModel);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/updateFeedback")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(supplierController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Updation done"));
    }

    /**
     * Method under test: {@link SupplierController#getSupplier(int)}
     */
    @Test
    void testGetSupplier() throws Exception {
        when(supplierService.getSupplier(anyInt())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getsupplier/{partId}", 123);
        MockMvcBuilders.standaloneSetup(supplierController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link SupplierController#getSupplier(int)}
     */
    @Test
    void testGetSupplier2() throws Exception {
        when(supplierService.getSupplier(anyInt())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/getsupplier/{partId}", 123);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(supplierController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

