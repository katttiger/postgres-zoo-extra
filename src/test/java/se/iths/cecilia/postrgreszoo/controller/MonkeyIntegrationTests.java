package se.iths.cecilia.postrgreszoo.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import se.iths.cecilia.postrgreszoo.model.Monkey;
import se.iths.cecilia.postrgreszoo.repository.MonkeyRepository;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MonkeyIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    MonkeyRepository monkeyRepository;

    @BeforeEach
    void setup() {
        monkeyRepository.deleteAll();
    }

    @Test
    @DisplayName("Assert created entity is saved")
    void createEntityIsSavedTest() throws Exception {
        Monkey monkey = new Monkey("John", "Gorilla", "Angola", "Y22");

        mockMvc.perform(MockMvcRequestBuilders.post("/monkeys/create")
                        .formField("name", monkey.getName())
                        .formField("type", monkey.getType())
                        .formField("originCountry", monkey.getOriginCountry())
                        .formField("currentHabitat", monkey.getCurrentHabitat())
                        .characterEncoding("UTF-8"))
                .andExpect(status().is3xxRedirection());

        Assertions.assertNotNull(monkeyRepository.findById(1L));
    }

    @Test
    @DisplayName("Fetch entity and verify it is correct")
    void fetchEntityAndVerifyItIsCorrectTest() throws Exception {
        Monkey monkey = new Monkey("John", "Gorilla", "Angola", "Y22");
        monkeyRepository.save(monkey);

        mockMvc.perform(MockMvcRequestBuilders.get("/monkeys/1")
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk());

        List<Monkey> monkeys = monkeyRepository.findAll();
        Monkey monkeyReturned = monkeys.get(0);


        List<Boolean> monkeyChecks = new ArrayList<>();
        monkeyChecks.add(monkey.getName().equals(monkeyReturned.getName()));
        monkeyChecks.add(monkey.getType().equals(monkeyReturned.getType()));
        monkeyChecks.add(monkey.getOriginCountry().equals(monkeyReturned.getOriginCountry()));
        monkeyChecks.add(monkey.getCurrentHabitat().equals(monkeyReturned.getCurrentHabitat()));

        Assertions.assertFalse(monkeyChecks.contains(false));
    }

    @Test
    @DisplayName("Fetch all entities and verify amount")
    void fetchAllEntitiesAndVerifyAmountTest() throws Exception {
        Monkey monkey1 = new Monkey("Oscar", "Gorilla", "Angola", "Y22");
        Monkey monkey2 = new Monkey("Jane", "Lemur", "Madagaskar", "I92");
        Monkey monkey3 = new Monkey("Isak", "Chimpanzee", "Tanzania", "S32");

        monkeyRepository.save(monkey1);
        monkeyRepository.save(monkey2);
        monkeyRepository.save(monkey3);

        mockMvc.perform(MockMvcRequestBuilders.get("/monkeys/")
                .accept(MediaType.APPLICATION_FORM_URLENCODED)
                .characterEncoding("UTF-8")).andExpect(status().isOk());

        List<Monkey> monkeys = monkeyRepository.findAll();
        Assertions.assertEquals(3, monkeys.size());
    }
}
