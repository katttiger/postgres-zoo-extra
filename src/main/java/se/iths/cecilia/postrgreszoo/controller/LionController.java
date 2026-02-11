package se.iths.cecilia.postrgreszoo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iths.cecilia.postrgreszoo.model.Lion;
import se.iths.cecilia.postrgreszoo.service.LionService;

@Controller
@RequestMapping("/lions")
public class LionController {

    private final LionService lionService;

    public LionController(LionService lionService) {
        this.lionService = lionService;
    }

    @PostMapping
    public String createLion(@ModelAttribute Lion lion) {
        lionService.createLion(lion);
        return "redirect:/lions";
    }

    @GetMapping("/new")
    public String showCreateForm() {
        return "create-lion";
    }

    @GetMapping("/{id}")
    public String getLionById(@PathVariable Long id, Model model) {
        model.addAttribute("lion", lionService.getLionById(id));
        return "lion";
    }

    @GetMapping
    public String getAllLions(Model model) {
        model.addAttribute("lions", lionService.getAllLions());
        return "lions";
    }

    @PutMapping("/{id}")
    public String updateLion(@PathVariable Long id, @ModelAttribute Lion lion) {
        Lion updatedLion = lionService.updateLion(id, lion);
        return "redirect:/lions";
    }

    @GetMapping("/{id}/update")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Lion lion = lionService.getLionById(id);
        model.addAttribute("lion", lion);
        return "update-lion";
    }

    @GetMapping("/{id}/delete")
    public String deleteLion(@PathVariable Long id) {
        lionService.deleteLion(id);
        return "redirect:/lions";
    }
}
