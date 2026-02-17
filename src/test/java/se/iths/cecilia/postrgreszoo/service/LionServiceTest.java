package se.iths.cecilia.postrgreszoo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.cecilia.postrgreszoo.exception.lionExceptions.LionNotFoundException;
import se.iths.cecilia.postrgreszoo.model.Lion;
import se.iths.cecilia.postrgreszoo.repository.LionRepository;
import se.iths.cecilia.postrgreszoo.validator.LionValidator;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class LionServiceTest {

    @Mock
    LionRepository lionRepository;

    @Mock
    LionValidator lionValidator;

    @InjectMocks
    LionService lionService;

    @Test
    public void createLionTest() {
        Lion lion = new Lion(1L, "Simba", 14, 50, 2);

        lionService.createLion(lion);

        Mockito.verify(lionValidator).validate(lion);
        Mockito.verify(lionRepository).save(lion);
    }

    @Test
    public void getLionByIdTest() {
        Lion lion = new Lion(4L, "Kang", 65, 110, 124);

        Mockito.when(lionRepository.findById(4L)).thenReturn(Optional.of(lion));

        Lion result = lionService.getLionById(4L);

        assertEquals(lion, result);
        Mockito.verify(lionRepository).findById(4L);
    }

    @Test
    public void getAllLionsTest() {
        List<Lion> lionList = List.of(
                new Lion(1L, "Simba", 15, 120, 4),
                new Lion(2L, "Cosmo", 17, 155, 20),
                new Lion(3L, "Rolf", 65, 220, 56)
        );

        Mockito.when(lionRepository.findAll()).thenReturn(lionList);

        List<Lion> result = lionService.getAllLions();

        assertEquals("Rolf", result.get(2).getName());
        Mockito.verify(lionRepository).findAll();
    }

    @Test
    public void updateLionUpdate() {
        Lion existingLion = new Lion(1L, "Simba", 15, 110, 11);
        Lion updatedLion = new Lion(1L, "Simba", 16, 135, 16);

        Mockito.when(lionRepository.findById(1L)).thenReturn(Optional.of(existingLion));

        Mockito.when(lionRepository.save(existingLion)).thenReturn(existingLion);

        Lion result = lionService.updateLion(1L, updatedLion);

        Mockito.verify(lionRepository).findById(1L);
        Mockito.verify(lionValidator).validateUpdate(updatedLion);
        Mockito.verify(lionRepository).save(existingLion);

        assertEquals(16, result.getAge());
        assertEquals(135, result.getWeight());
        assertEquals(16, result.getKills());
    }

    @Test
    public void deleteLion() {
        Mockito.when(lionRepository.existsById(1L)).thenReturn(true);

        lionService.deleteLion(1L);

        Mockito.verify(lionRepository).existsById(1L);
        Mockito.verify(lionRepository).deleteById(1L);
    }

    @Test
    public void getLionByIdExceptionTest() {
        Mockito.when(lionRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(LionNotFoundException.class, () -> lionService.getLionById(1L));

        Mockito.verify(lionRepository).findById(1L);
    }

    @Test
    public void updateLionExceptionTest() {
        Lion updatedLion = new Lion(2L, "Simba", 16, 135, 16);

        Mockito.when(lionRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(LionNotFoundException.class, () -> lionService.updateLion(2L, updatedLion));

        Mockito.verify(lionRepository).findById(2L);
    }

    @Test
    public void deleteLionExceptionTest() {
        Mockito.when(lionRepository.existsById(1L)).thenReturn(false);

        assertThrows(LionNotFoundException.class, () -> lionService.deleteLion(1L));

        Mockito.verify(lionRepository).existsById(1L);
    }
}
