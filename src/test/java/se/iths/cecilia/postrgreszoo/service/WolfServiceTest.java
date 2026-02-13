package se.iths.cecilia.postrgreszoo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.cecilia.postrgreszoo.exception.WolfNotFoundException;
import se.iths.cecilia.postrgreszoo.model.Wolf;
import se.iths.cecilia.postrgreszoo.repository.WolfRepository;
import se.iths.cecilia.postrgreszoo.validator.WolfValidator;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WolfServiceTest {

    @Mock
    WolfRepository wolfRepository;

    @Mock
    WolfValidator wolfValidator;

    @InjectMocks
    WolfService wolfService;

    @Test
    void getWolfShouldReturnWolf() {
        Wolf wolf = new Wolf();
        wolf.setId(1L);

        when(wolfRepository.findById(1L)).thenReturn(Optional.of(wolf));

        Wolf result = wolfService.getWolf(1L);

        assertEquals(1L, result.getId());
        verify(wolfRepository).findById(1L);
    }

    @Test
    void getAllWolvesShouldReturnListOfWolves() {
        List<Wolf> wolves = List.of(new Wolf(), new Wolf());

        when(wolfRepository.findAll()).thenReturn(wolves);

        List<Wolf> result = wolfService.getAllWolves();

        assertEquals(2, result.size());
        verify(wolfRepository).findAll();
    }

    @Test
    void createWolfShouldCallValidateMethodsAndSave() {
        Wolf wolf = new Wolf();

        when(wolfRepository.save(wolf)).thenReturn(wolf);

        Wolf result = wolfService.createWolf(wolf);

        verify(wolfValidator).verifyNameIsNotNullOrBlank(wolf);
        verify(wolfValidator).verifyAgeIsValid(wolf);
        verify(wolfValidator).verifyColorIsNotNullOrBlank(wolf);
        verify(wolfValidator).verifyHowlKeyIsValid(wolf);
        verify(wolfRepository).save(wolf);

        assertEquals(wolf, result);
    }

    @Test
    void updateWolfShouldCallUpdate() {
        Wolf wolf = new Wolf();

        when(wolfRepository.findById(1L)).thenReturn(Optional.of(wolf));
        when(wolfRepository.save(wolf)).thenReturn(wolf);

        Wolf result = wolfService.updateWolf(1L, wolf);

        verify(wolfRepository).findById(1L);
        verify(wolfRepository).save(wolf);

        assertEquals(1L, result.getId());
    }

    @Test
    void deleteWolfShouldCallDelete() {
        wolfService.deleteWolf(1L);

        verify(wolfRepository).deleteById(1L);
    }

    //Tests of exceptions

    @Test
    void getWolfShouldThrowExceptionWhenNotFound() {
        when(wolfRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(WolfNotFoundException.class,
                () -> wolfService.getWolf(1L));

        verify(wolfRepository).findById(1L);
    }

    @Test
    void updateWolfShouldThrowExceptionWhenNotFound() {
        Wolf wolf = new Wolf();

        when(wolfRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(WolfNotFoundException.class,
                () -> wolfService.updateWolf(1L, wolf));

        verify(wolfRepository).findById(1L);
        verify(wolfRepository, never()).save(any());
    }
}
