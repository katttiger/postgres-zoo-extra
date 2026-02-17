package se.iths.cecilia.postrgreszoo.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import se.iths.cecilia.postrgreszoo.model.Monkey;
import se.iths.cecilia.postrgreszoo.repository.MonkeyRepository;
import se.iths.cecilia.postrgreszoo.service.MonkeyService;
import se.iths.cecilia.postrgreszoo.validator.MonkeyValidator;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MonkeyIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    MonkeyRepository monkeyRepository;

    MonkeyController monkeyController;

    MonkeyService monkeyService;

    @BeforeEach
    void setup() {
        this.monkeyService = new MonkeyService(monkeyRepository, new MonkeyValidator());
        this.monkeyController = new MonkeyController(monkeyService, monkeyRepository);
        this.mockMvc = MockMvcBuilders.standaloneSetup(monkeyController).build();
    }

    @Test
    @DisplayName("Assert created entity is saved")
    void createEntityIsSavedTest() {
        Monkey monkey = new Monkey(1L, "John", "Gorilla", "Angola", "Y22");
        //setup -> prepare monkey. Prepare response

        //expect -> monkey saved is the one returned.
        //verify by fetching the monkey from H2-db.
    }

    @Test
    @DisplayName("Fetch entity and verify it is correct")
    void fetchEntityAndVerifyItIsCorrect() {
        Monkey monkey = new Monkey(1L, "John", "Gorilla", "Angola", "Y22");
    }

    @Test
    @DisplayName("Fetch all entities and verify amount")
    void fetchAllEntitiesAndVerifyAmount() {


    }
}
