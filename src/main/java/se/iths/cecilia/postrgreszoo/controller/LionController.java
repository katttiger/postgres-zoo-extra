package se.iths.cecilia.postrgreszoo.controller;

import jakarta.servlet.ServletConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import se.iths.cecilia.postrgreszoo.model.Lion;
import se.iths.cecilia.postrgreszoo.service.LionService;

@Controller
@RequestMapping("/lion")
public class LionController {

    // TODO, Inte klart :)

    private final LionService lionService;
    private final ServletConfig servletConfig;

    public LionController(LionService lionService, ServletConfig servletConfig) {
        this.lionService = lionService;
        this.servletConfig = servletConfig;
    }

    // Create


    // Get by ID
    @GetMapping("/{id}")
    public String getLionById(@PathVariable Long id, Model model) {
        model.addAttribute("lion", lionService.getLionById(id));
        return "lion";
    }

    // Get all
    @GetMapping
    public String getAllLions(Model model) {
        model.addAttribute("lions", lionService.getAllLions());
        return "lions";
    }

    // Update
    public String updateLion(@PathVariable Long id, @ModelAttribute Lion lion) {
        Lion updatedLion = lionService.updateLion(id, lion);
        return "";
    }


    // Delete


}
