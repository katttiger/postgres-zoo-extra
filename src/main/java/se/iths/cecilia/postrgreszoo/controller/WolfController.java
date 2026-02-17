package se.iths.cecilia.postrgreszoo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iths.cecilia.postrgreszoo.model.Wolf;
import se.iths.cecilia.postrgreszoo.repository.WolfRepository;
import se.iths.cecilia.postrgreszoo.service.WolfService;

@Controller
public class WolfController {
    private final WolfService wolfService;
    private final WolfRepository wolfRepository;

    public WolfController(WolfService wolfService, WolfRepository wolfRepository) {
        this.wolfService = wolfService;
        this.wolfRepository = wolfRepository;
    }

    @GetMapping
    public String getAllWolves(Model model) {
        model.addAttribute("wolves", wolfService.getAllWolves());
        return "wolves";
    }

    @GetMapping("/{id}")
    public String getWolf(@PathVariable Long id, Model model) {
        model.addAttribute("wolf", wolfService.getWolf(id));
        return "wolf";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        return "createWolf";
    }

    @PostMapping("/create")
    public String createWolf(@ModelAttribute Wolf wolf) {
        wolfService.createWolf(wolf);
        return "redirect:/wolf";
    }


    @PutMapping("/{id}/update")
    public String updateWolf(@PathVariable Long id, @ModelAttribute Wolf wolf) {
        wolfService.updateWolf(id, wolf);
        return "redirect:/wolf";
    }

    @GetMapping("/{id}/update")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Wolf wolf = wolfService.getWolf(id);
        model.addAttribute("wolf", wolf);
        return "updateWolf";
    }


    @DeleteMapping("/{id}/delete")
    public String deleteWolf(@PathVariable Long id) {
        wolfService.deleteWolf(id);
        System.out.println("Delete method");
        return "redirect:/wolf";
    }


}
