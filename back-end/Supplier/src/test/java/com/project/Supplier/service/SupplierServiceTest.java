package com.project.Supplier.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.project.Supplier.entity.Supplier;
import com.project.Supplier.entity.SupplierPart;
import com.project.Supplier.entity.SupplierPartModel;
import com.project.Supplier.exception.SupplierPartNotFoundException;
import com.project.Supplier.repository.SupplierPartRepository;
import com.project.Supplier.repository.SupplierRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class SupplierServiceTest {
    @Mock
    private SupplierPartRepository supplierPartRepository;

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private SupplierService supplierService;

    /**
     * Method under test: {@link SupplierService#findByPartId(int)}
     */
    @Test
    void testFindByPartId() throws SupplierPartNotFoundException {
        when(supplierPartRepository.getByPartId(anyInt())).thenReturn(new ArrayList<>());
        assertTrue(supplierService.findByPartId(1).isEmpty());
        verify(supplierPartRepository).getByPartId(anyInt());
    }

    /**
     * Method under test: {@link SupplierService#findByPartId(int)}
     */
    @Test
    void testFindByPartId1() throws SupplierPartNotFoundException {
        Supplier supplier = new Supplier();
        supplier.setEmail("jane.doe@example.org");
        supplier.setFeedback(1);
        supplier.setLocation("Inside FindSupplierByPartId of SupplierServive");
        supplier.setPhone("4105551212");
        supplier.setSupplierId(123);
        supplier.setSupplierName("Inside FindSupplierByPartId of SupplierServive");

        SupplierPart supplierPart = new SupplierPart();
        supplierPart.setDeliveryDate(mock(Date.class));
        supplierPart.setPartId(123);
        supplierPart.setQuantitiy(1);
        supplierPart.setSupplier(supplier);
        supplierPart.setSupplierPartId(123);
        supplierPart.setSupplierPartName("Inside FindSupplierByPartId of SupplierServive");

        ArrayList<SupplierPart> supplierPartList = new ArrayList<>();
        supplierPartList.add(supplierPart);
        when(supplierPartRepository.getByPartId(anyInt())).thenReturn(supplierPartList);
        assertEquals(1, supplierService.findByPartId(1).size());
        verify(supplierPartRepository).getByPartId(anyInt());
    }

    /**
     * Method under test: {@link SupplierService#addSupplier(SupplierPartModel)}
     */
    @Test
    void testAddSupplier() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setEmail("jane.doe@example.org");
        supplier.setFeedback(1);
        supplier.setLocation("Location");
        supplier.setPhone("4105551212");
        supplier.setSupplierId(123);
        supplier.setSupplierName("Supplier Name");

        SupplierPart supplierPart = new SupplierPart();
        supplierPart.setDeliveryDate(mock(Date.class));
        supplierPart.setPartId(123);
        supplierPart.setQuantitiy(1);
        supplierPart.setSupplier(supplier);
        supplierPart.setSupplierPartId(123);
        supplierPart.setSupplierPartName("Supplier Part Name");
        when(supplierPartRepository.save(any())).thenReturn(supplierPart);

        Supplier supplier1 = new Supplier();
        supplier1.setEmail("jane.doe@example.org");
        supplier1.setFeedback(1);
        supplier1.setLocation("Location");
        supplier1.setPhone("4105551212");
        supplier1.setSupplierId(123);
        supplier1.setSupplierName("Supplier Name");
        when(supplierRepository.save(any())).thenReturn(supplier1);
        assertSame(supplierPart, supplierService.addSupplier(new SupplierPartModel()));
        verify(supplierPartRepository).save(any());
        verify(supplierRepository).save(any());
    }

    /**
     * Method under test: {@link SupplierService#editSupplier(SupplierPartModel)}
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
        Optional<Supplier> ofResult = Optional.of(supplier);

        Supplier supplier1 = new Supplier();
        supplier1.setEmail("jane.doe@example.org");
        supplier1.setFeedback(1);
        supplier1.setLocation("Location");
        supplier1.setPhone("4105551212");
        supplier1.setSupplierId(123);
        supplier1.setSupplierName("Supplier Name");
        when(supplierRepository.save(any())).thenReturn(supplier1);
        when(supplierRepository.findById(any())).thenReturn(ofResult);
        assertSame(supplier1, supplierService.editSupplier(new SupplierPartModel()));
        verify(supplierRepository).save(any());
        verify(supplierRepository).findById(any());
    }


    /**
     * Method under test: {@link SupplierService#editSupplier(SupplierPartModel)}
     */
    @Test
    void testEditSupplier1() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setEmail("jane.doe@example.org");
        supplier.setFeedback(1);
        supplier.setLocation("Location");
        supplier.setPhone("4105551212");
        supplier.setSupplierId(123);
        supplier.setSupplierName("Supplier Name");
        when(supplierRepository.save(any())).thenReturn(supplier);
        when(supplierRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> supplierService.editSupplier(new SupplierPartModel()));
        verify(supplierRepository).findById(any());
    }

    /**
     * Method under test: {@link SupplierService#updateFeedback(SupplierPartModel)}
     */
    @Test
    void testUpdateFeedback() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setEmail("jane.doe@example.org");
        supplier.setFeedback(1);
        supplier.setLocation("Location");
        supplier.setPhone("4105551212");
        supplier.setSupplierId(123);
        supplier.setSupplierName("Supplier Name");
        Optional<Supplier> ofResult = Optional.of(supplier);

        Supplier supplier1 = new Supplier();
        supplier1.setEmail("jane.doe@example.org");
        supplier1.setFeedback(1);
        supplier1.setLocation("Location");
        supplier1.setPhone("4105551212");
        supplier1.setSupplierId(123);
        supplier1.setSupplierName("Supplier Name");
        when(supplierRepository.save(any())).thenReturn(supplier1);
        when(supplierRepository.findById(any())).thenReturn(ofResult);
        supplierService.updateFeedback(new SupplierPartModel());
        verify(supplierRepository).save(any());
        verify(supplierRepository).findById(any());
    }

    /**
     * Method under test: {@link SupplierService#updateFeedback(SupplierPartModel)}
     */
    @Test
    void testUpdateFeedback1() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setEmail("jane.doe@example.org");
        supplier.setFeedback(1);
        supplier.setLocation("Location");
        supplier.setPhone("4105551212");
        supplier.setSupplierId(123);
        supplier.setSupplierName("Supplier Name");
        when(supplierRepository.save(any())).thenReturn(supplier);
        when(supplierRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> supplierService.updateFeedback(new SupplierPartModel()));
        verify(supplierRepository).findById(any());
    }

    /**
     * Method under test: {@link SupplierService#getSupplier(int)}
     */
    @Test
    void testGetSupplier() {
        when(supplierPartRepository.getByPartId(anyInt())).thenReturn(new ArrayList<>());
        assertTrue(supplierService.getSupplier(123).isEmpty());
        verify(supplierPartRepository).getByPartId(anyInt());
    }

    /**
     * Method under test: {@link SupplierService#getSupplier(int)}
     */
    @Test
    void testGetSupplier1() {
        Supplier supplier = new Supplier();
        supplier.setEmail("jane.doe@example.org");
        supplier.setFeedback(1);
        supplier.setLocation("getSupplier of Supplier Service");
        supplier.setPhone("4105551212");
        supplier.setSupplierId(123);
        supplier.setSupplierName("getSupplier of Supplier Service");

        SupplierPart supplierPart = new SupplierPart();
        supplierPart.setDeliveryDate(mock(Date.class));
        supplierPart.setPartId(123);
        supplierPart.setQuantitiy(1);
        supplierPart.setSupplier(supplier);
        supplierPart.setSupplierPartId(123);
        supplierPart.setSupplierPartName("getSupplier of Supplier Service");

        ArrayList<SupplierPart> supplierPartList = new ArrayList<>();
        supplierPartList.add(supplierPart);
        when(supplierPartRepository.getByPartId(anyInt())).thenReturn(supplierPartList);
        assertTrue(supplierService.getSupplier(123).isEmpty());
        verify(supplierPartRepository).getByPartId(anyInt());
    }

    /**
     * Method under test: {@link SupplierService#toSupplier(Supplier)}
     */
    @Test
    void testToSupplier() {
        Supplier supplier = new Supplier();
        supplier.setEmail("jane.doe@example.org");
        supplier.setFeedback(1);
        supplier.setLocation("Location");
        supplier.setPhone("4105551212");
        supplier.setSupplierId(123);
        supplier.setSupplierName("Supplier Name");
        Supplier actualToSupplierResult = supplierService.toSupplier(supplier);
        assertEquals("jane.doe@example.org", actualToSupplierResult.getEmail());
        assertEquals("Supplier Name", actualToSupplierResult.getSupplierName());
        assertEquals(123, actualToSupplierResult.getSupplierId());
        assertEquals("4105551212", actualToSupplierResult.getPhone());
        assertEquals("Location", actualToSupplierResult.getLocation());
        assertEquals(1, actualToSupplierResult.getFeedback());
    }

    /**
     * Method under test: {@link SupplierService#toSupplier(Supplier)}
     */
    @Test
    void testToSupplier1() {
        Supplier supplier = mock(Supplier.class);
        when(supplier.getFeedback()).thenReturn(1);
        when(supplier.getSupplierId()).thenReturn(123);
        when(supplier.getEmail()).thenReturn("jane.doe@example.org");
        when(supplier.getLocation()).thenReturn("Location");
        when(supplier.getPhone()).thenReturn("4105551212");
        when(supplier.getSupplierName()).thenReturn("Supplier Name");
        doNothing().when(supplier).setEmail(any());
        doNothing().when(supplier).setFeedback(anyInt());
        doNothing().when(supplier).setLocation(any());
        doNothing().when(supplier).setPhone(any());
        doNothing().when(supplier).setSupplierId(anyInt());
        doNothing().when(supplier).setSupplierName(any());
        supplier.setEmail("jane.doe@example.org");
        supplier.setFeedback(1);
        supplier.setLocation("Location");
        supplier.setPhone("4105551212");
        supplier.setSupplierId(123);
        supplier.setSupplierName("Supplier Name");
        Supplier actualToSupplierResult = supplierService.toSupplier(supplier);
        assertEquals("jane.doe@example.org", actualToSupplierResult.getEmail());
        assertEquals("Supplier Name", actualToSupplierResult.getSupplierName());
        assertEquals(123, actualToSupplierResult.getSupplierId());
        assertEquals("4105551212", actualToSupplierResult.getPhone());
        assertEquals("Location", actualToSupplierResult.getLocation());
        assertEquals(1, actualToSupplierResult.getFeedback());
        verify(supplier).getFeedback();
        verify(supplier).getSupplierId();
        verify(supplier).getEmail();
        verify(supplier).getLocation();
        verify(supplier).getPhone();
        verify(supplier).getSupplierName();
        verify(supplier).setEmail(any());
        verify(supplier).setFeedback(anyInt());
        verify(supplier).setLocation(any());
        verify(supplier).setPhone(any());
        verify(supplier).setSupplierId(anyInt());
        verify(supplier).setSupplierName(any());
    }
}

