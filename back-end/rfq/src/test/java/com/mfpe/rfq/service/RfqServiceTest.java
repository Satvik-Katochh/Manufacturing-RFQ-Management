package com.mfpe.rfq.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mfpe.rfq.model.AuthResponse;
import com.mfpe.rfq.model.Part;
import com.mfpe.rfq.model.RfqDetail;
import com.mfpe.rfq.model.Supplier;
import com.mfpe.rfq.repo.RfqDetailRepo;
import com.mfpe.rfq.restclient.AuthClient;
import com.mfpe.rfq.restclient.PartClient;
import com.mfpe.rfq.restclient.SupplierClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class RfqServiceTest {
    @Mock
    private AuthClient authClient;

    @Mock
    private PartClient partClient;

    @Mock
    private RfqDetailRepo rfqDetailRepo;

    @InjectMocks
    private RfqService rfqService;

    @Mock
    private SupplierClient supplierClient;


    /**
     * Method under test: {@link RfqService#getRfqOfPlant(int, String)}
     */
    @Test
    void testGetRfqOfPlant() throws Exception {
        when(authClient.getValidity((String) any())).thenReturn(new AuthResponse());
        when(partClient.viewDemand(anyInt())).thenReturn(1);
        when(partClient.viewPartsReorder()).thenReturn(new ArrayList<>());
        assertThrows(Exception.class, () -> rfqService.getRfqOfPlant(123, "ABC123"));
        verify(authClient).getValidity((String) any());
    }


    /**
     * Method under test: {@link RfqService#getRfqOfPlant(int, String)}
     */
    @Test
    void testGetRfqOfPlant2() throws Exception {
        when(authClient.getValidity((String) any())).thenReturn(new AuthResponse("1234", "Name", true));

        ArrayList<Part> partList = new ArrayList<>();
        partList.add(
                new Part(123, "Inside GetRfqDeatilsoPlant of RfqService", "Inside GetRfqDeatilsoPlant of RfqService", 3));
        when(partClient.viewDemand(anyInt())).thenReturn(1);
        when(partClient.viewPartsReorder()).thenReturn(partList);

        RfqDetail rfqDetail = new RfqDetail();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        rfqDetail.setExpectedSupplyDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        rfqDetail.setPartId(123);
        rfqDetail.setPartName("Part Name");
        rfqDetail.setQuantity(1);
        rfqDetail.setRfqId(123);
        rfqDetail.setSpecification("Specification");
        when(rfqDetailRepo.save((RfqDetail) any())).thenReturn(rfqDetail);
        assertSame(rfqDetail, rfqService.getRfqOfPlant(123, "ABC123"));
        verify(authClient).getValidity((String) any());
        verify(partClient).viewDemand(anyInt());
        verify(partClient).viewPartsReorder();
        verify(rfqDetailRepo).save((RfqDetail) any());
    }

    /**
     * Method under test: {@link RfqService#getPotentialVendorsOfRfq(int, String)}
     */
    @Test
    void testGetPotentialVendorsOfRfq() throws Exception {
        when(authClient.getValidity((String) any())).thenReturn(new AuthResponse("1234", "Name", true));

        RfqDetail rfqDetail = new RfqDetail();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        rfqDetail.setExpectedSupplyDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        rfqDetail.setPartId(123);
        rfqDetail.setPartName("Part Name");
        rfqDetail.setQuantity(1);
        rfqDetail.setRfqId(123);
        rfqDetail.setSpecification("Specification");
        Optional<RfqDetail> ofResult = Optional.of(rfqDetail);
        when(rfqDetailRepo.findById((Integer) any())).thenReturn(ofResult);
        ArrayList<Supplier> supplierList = new ArrayList<>();
        when(supplierClient.getSupplier(anyInt())).thenReturn(supplierList);
        List<Supplier> actualPotentialVendorsOfRfq = rfqService.getPotentialVendorsOfRfq(123, "ABC123");
        assertSame(supplierList, actualPotentialVendorsOfRfq);
        assertTrue(actualPotentialVendorsOfRfq.isEmpty());
        verify(authClient).getValidity((String) any());
        verify(rfqDetailRepo).findById((Integer) any());
        verify(supplierClient).getSupplier(anyInt());
    }

    /**
     * Method under test: {@link RfqService#getPotentialVendorsOfRfq(int, String)}
     */
    @Test
    void testGetPotentialVendorsOfRfq1() throws Exception {
        when(authClient.getValidity((String) any())).thenReturn(new AuthResponse("1234", "Name", true));

        RfqDetail rfqDetail = new RfqDetail();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        rfqDetail.setExpectedSupplyDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        rfqDetail.setPartId(123);
        rfqDetail.setPartName("Part Name");
        rfqDetail.setQuantity(1);
        rfqDetail.setRfqId(123);
        rfqDetail.setSpecification("Specification");
        Optional<RfqDetail> ofResult = Optional.of(rfqDetail);
        when(rfqDetailRepo.findById((Integer) any())).thenReturn(ofResult);
        when(supplierClient.getSupplier(anyInt())).thenThrow(new Exception("An error occurred"));
        assertThrows(Exception.class, () -> rfqService.getPotentialVendorsOfRfq(123, "ABC123"));
        verify(authClient).getValidity((String) any());
        verify(rfqDetailRepo).findById((Integer) any());
        verify(supplierClient).getSupplier(anyInt());
    }

    /**
     * Method under test: {@link RfqService#getPotentialVendorsOfRfq(int, String)}
     */
    @Test
    void testGetPotentialVendorsOfRfq2() throws Exception {
        when(authClient.getValidity((String) any())).thenReturn(new AuthResponse("1234", "Name", false));

        RfqDetail rfqDetail = new RfqDetail();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        rfqDetail.setExpectedSupplyDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        rfqDetail.setPartId(123);
        rfqDetail.setPartName("Part Name");
        rfqDetail.setQuantity(1);
        rfqDetail.setRfqId(123);
        rfqDetail.setSpecification("Specification");
        Optional<RfqDetail> ofResult = Optional.of(rfqDetail);
        when(rfqDetailRepo.findById((Integer) any())).thenReturn(ofResult);
        when(supplierClient.getSupplier(anyInt())).thenReturn(new ArrayList<>());
        assertThrows(Exception.class, () -> rfqService.getPotentialVendorsOfRfq(123, "ABC123"));
        verify(authClient).getValidity((String) any());
    }

    /**
     * Method under test: {@link RfqService#getPotentialVendorsOfRfq(int, String)}
     */
    @Test
    void testGetPotentialVendorsOfRfq3() throws Exception {
        when(authClient.getValidity((String) any())).thenReturn(new AuthResponse("1234", "Name", true));
        when(rfqDetailRepo.findById((Integer) any())).thenReturn(Optional.empty());
        when(supplierClient.getSupplier(anyInt())).thenReturn(new ArrayList<>());
        assertThrows(Exception.class, () -> rfqService.getPotentialVendorsOfRfq(123, "ABC123"));
        verify(authClient).getValidity((String) any());
        verify(rfqDetailRepo).findById((Integer) any());
    }
}

