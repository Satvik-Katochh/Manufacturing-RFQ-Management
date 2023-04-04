package com.mfpe.plant.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mfpe.plant.exception.PartNotFoundException;
import com.mfpe.plant.model.Demand;
import com.mfpe.plant.model.Part;
import com.mfpe.plant.model.ReorderRules;
import com.mfpe.plant.repo.DemandRepository;
import com.mfpe.plant.repo.PartRepository;
import com.mfpe.plant.repo.ReorderRulesRepository;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class PlantServiceTest {
    @Mock
    private DemandRepository demandRepository;

    @Mock
    private PartRepository partRepository;

    @InjectMocks
    private PlantService plantService;

    @Mock
    private ReorderRulesRepository reorderRulesRepository;

    /**
     * Method under test: {@link PlantService#viewPartsReorder()}
     */
    @Test
    void testViewPartsReorder() {
        when(demandRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(plantService.viewPartsReorder().isEmpty());
        verify(demandRepository).findAll();
    }

    /**
     * Method under test: {@link PlantService#viewPartsReorder()}
     */
    @Test
    void testViewPartsReorder1() {
        Demand demand = new Demand();
        demand.setDemandCount(3);
        demand.setDemandId(123);
        demand.setDemandRaisedDate("2020-03-01");
        demand.setPartId(123);

        ArrayList<Demand> demandList = new ArrayList<>();
        demandList.add(demand);
        when(demandRepository.findAll()).thenReturn(demandList);

        Part part = new Part();
        part.setPartDescription("Part Description");
        part.setPartId(123);
        part.setPartSpecification("Part Specification");
        part.setStockInHand(1);
        Optional<Part> ofResult = Optional.of(part);
        when(partRepository.findById(any())).thenReturn(ofResult);

        ReorderRules reorderRules = new ReorderRules();
        reorderRules.setMaxQuantity(3);
        reorderRules.setMinQuantity(1);
        reorderRules.setPartId(123);
        Optional<ReorderRules> ofResult1 = Optional.of(reorderRules);
        when(reorderRulesRepository.findById(any())).thenReturn(ofResult1);
        assertEquals(1, plantService.viewPartsReorder().size());
        verify(demandRepository).findAll();
        verify(partRepository).findById(any());
        verify(reorderRulesRepository).findById(any());
    }

    /**
     * Method under test: {@link PlantService#viewPartsReorder()}
     */
    @Test
    void testViewPartsReorder2() {
        Demand demand = new Demand();
        demand.setDemandCount(3);
        demand.setDemandId(123);
        demand.setDemandRaisedDate("2020-03-01");
        demand.setPartId(123);

        ArrayList<Demand> demandList = new ArrayList<>();
        demandList.add(demand);
        when(demandRepository.findAll()).thenReturn(demandList);

        Part part = new Part();
        part.setPartDescription("Part Description");
        part.setPartId(123);
        part.setPartSpecification("Part Specification");
        part.setStockInHand(1);
        Optional<Part> ofResult = Optional.of(part);
        when(partRepository.findById(any())).thenReturn(ofResult);
        when(reorderRulesRepository.findById(any()))
                .thenThrow(new NoSuchElementException("Inside viewPartsReorderof service"));
        assertThrows(NoSuchElementException.class, () -> plantService.viewPartsReorder());
        verify(demandRepository).findAll();
        verify(partRepository).findById(any());
        verify(reorderRulesRepository).findById(any());
    }

    /**
     * Method under test: {@link PlantService#viewPartsReorder()}
     */
    @Test
    void testViewPartsReorder3() {
        Demand demand = new Demand();
        demand.setDemandCount(-2);
        demand.setDemandId(123);
        demand.setDemandRaisedDate("2020-03-01");
        demand.setPartId(123);

        ArrayList<Demand> demandList = new ArrayList<>();
        demandList.add(demand);
        when(demandRepository.findAll()).thenReturn(demandList);

        Part part = new Part();
        part.setPartDescription("Part Description");
        part.setPartId(123);
        part.setPartSpecification("Part Specification");
        part.setStockInHand(1);
        Optional<Part> ofResult = Optional.of(part);
        when(partRepository.findById(any())).thenReturn(ofResult);

        ReorderRules reorderRules = new ReorderRules();
        reorderRules.setMaxQuantity(3);
        reorderRules.setMinQuantity(1);
        reorderRules.setPartId(123);
        Optional<ReorderRules> ofResult1 = Optional.of(reorderRules);
        when(reorderRulesRepository.findById(any())).thenReturn(ofResult1);
        assertTrue(plantService.viewPartsReorder().isEmpty());
        verify(demandRepository).findAll();
        verify(partRepository).findById(any());
        verify(reorderRulesRepository).findById(any());
    }

    /**
     * Method under test: {@link PlantService#viewStockInHand(int)}
     */
    @Test
    void testViewStockInHand() throws PartNotFoundException {
        Part part = new Part();
        part.setPartDescription("Part Description");
        part.setPartId(123);
        part.setPartSpecification("Part Specification");
        part.setStockInHand(1);
        Optional<Part> ofResult = Optional.of(part);
        when(partRepository.findById(any())).thenReturn(ofResult);
        assertSame(part, plantService.viewStockInHand(123));
        verify(partRepository).findById(any());
    }

    /**
     * Method under test: {@link PlantService#viewStockInHand(int)}
     */
    @Test
    void testViewStockInHand1() throws PartNotFoundException {
        when(partRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(PartNotFoundException.class, () -> plantService.viewStockInHand(123));
        verify(partRepository).findById(any());
    }

    /**
     * Method under test: {@link PlantService#viewStockInHand(int)}
     */
    @Test
    void testViewStockInHand2() throws PartNotFoundException {
        when(partRepository.findById(any()))
                .thenThrow(new NoSuchElementException("Inside viewStockInHand of service"));
        assertThrows(NoSuchElementException.class, () -> plantService.viewStockInHand(123));
        verify(partRepository).findById(any());
    }

    /**
     * Method under test: {@link PlantService#updateMinMaxQuantities(ReorderRules)}
     */
    @Test
    void testUpdateMinMaxQuantities() {
        Demand demand = new Demand();
        demand.setDemandCount(3);
        demand.setDemandId(123);
        demand.setDemandRaisedDate("2020-03-01");
        demand.setPartId(123);
        when(demandRepository.getByPartId(anyInt())).thenReturn(demand);

        ReorderRules reorderRules = new ReorderRules();
        reorderRules.setMaxQuantity(3);
        reorderRules.setMinQuantity(1);
        reorderRules.setPartId(123);
        Optional<ReorderRules> ofResult = Optional.of(reorderRules);
        when(reorderRulesRepository.findById(any())).thenReturn(ofResult);

        ReorderRules reorderRules1 = new ReorderRules();
        reorderRules1.setMaxQuantity(3);
        reorderRules1.setMinQuantity(1);
        reorderRules1.setPartId(123);
        assertEquals("Maximum limit exceeding", plantService.updateMinMaxQuantities(reorderRules1));
        verify(demandRepository).getByPartId(anyInt());
        verify(reorderRulesRepository).findById(any());
    }

    /**
     * Method under test: {@link PlantService#updateMinMaxQuantities(ReorderRules)}
     */
    @Test
    void testUpdateMinMaxQuantities1() {
        when(demandRepository.getByPartId(anyInt()))
                .thenThrow(new NoSuchElementException("Inside updateMinMaxQuantities of service"));

        ReorderRules reorderRules = new ReorderRules();
        reorderRules.setMaxQuantity(3);
        reorderRules.setMinQuantity(1);
        reorderRules.setPartId(123);
        Optional<ReorderRules> ofResult = Optional.of(reorderRules);
        when(reorderRulesRepository.findById(any())).thenReturn(ofResult);

        ReorderRules reorderRules1 = new ReorderRules();
        reorderRules1.setMaxQuantity(3);
        reorderRules1.setMinQuantity(1);
        reorderRules1.setPartId(123);
        assertEquals("PartId not found. Please provide valid partId to update min and max quantities",
                plantService.updateMinMaxQuantities(reorderRules1));
        verify(demandRepository).getByPartId(anyInt());
        verify(reorderRulesRepository).findById(any());
    }

    /**
     * Method under test: {@link PlantService#updateMinMaxQuantities(ReorderRules)}
     */
    @Test
    void testUpdateMinMaxQuantities2() {
        Demand demand = new Demand();
        demand.setDemandCount(20);
        demand.setDemandId(123);
        demand.setDemandRaisedDate("2020-03-01");
        demand.setPartId(123);
        when(demandRepository.getByPartId(anyInt())).thenReturn(demand);

        ReorderRules reorderRules = new ReorderRules();
        reorderRules.setMaxQuantity(3);
        reorderRules.setMinQuantity(1);
        reorderRules.setPartId(123);
        Optional<ReorderRules> ofResult = Optional.of(reorderRules);
        when(reorderRulesRepository.findById(any())).thenReturn(ofResult);

        ReorderRules reorderRules1 = new ReorderRules();
        reorderRules1.setMaxQuantity(3);
        reorderRules1.setMinQuantity(1);
        reorderRules1.setPartId(123);
        assertEquals("Minimum limit receding", plantService.updateMinMaxQuantities(reorderRules1));
        verify(demandRepository).getByPartId(anyInt());
        verify(reorderRulesRepository).findById(any());
    }


    /**
     * Method under test: {@link PlantService#updateMinMaxQuantities(ReorderRules)}
     */
    @Test
    void testUpdateMinMaxQuantities3() {
        Demand demand = new Demand();
        demand.setDemandCount(3);
        demand.setDemandId(123);
        demand.setDemandRaisedDate("2020-03-01");
        demand.setPartId(123);
        when(demandRepository.getByPartId(anyInt())).thenReturn(demand);
        when(reorderRulesRepository.findById(any())).thenReturn(Optional.empty());

        ReorderRules reorderRules = new ReorderRules();
        reorderRules.setMaxQuantity(3);
        reorderRules.setMinQuantity(1);
        reorderRules.setPartId(123);
        assertEquals("PartId not found. Please provide valid partId to update min and max quantities",
                plantService.updateMinMaxQuantities(reorderRules));
        verify(reorderRulesRepository).findById(any());
    }

    /**
     * Method under test: {@link PlantService#viewDemand(int)}
     */
    @Test
    void testViewDemand() {
        Demand demand = new Demand();
        demand.setDemandCount(3);
        demand.setDemandId(123);
        demand.setDemandRaisedDate("2020-03-01");
        demand.setPartId(123);
        when(demandRepository.getByPartId(anyInt())).thenReturn(demand);
        assertEquals(3, plantService.viewDemand(123));
        verify(demandRepository).getByPartId(anyInt());
    }

    /**
     * Method under test: {@link PlantService#viewDemand(int)}
     */
    @Test
    void testViewDemand1() {
        when(demandRepository.getByPartId(anyInt())).thenThrow(new NoSuchElementException("foo"));
        assertThrows(NoSuchElementException.class, () -> plantService.viewDemand(123));
        verify(demandRepository).getByPartId(anyInt());
    }
}

