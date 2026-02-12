package se.iths.cecilia.postrgreszoo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iths.cecilia.postrgreszoo.model.Puma;
import se.iths.cecilia.postrgreszoo.service.PumaService;

@Controller
@RequestMapping("/pumas")
public class PumaController {

    private final PumaService service;

    public PumaController(PumaService service) {
        this.service = service;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("pumas", service.getAll());
        return "pumas";
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable Long id, Model model) {
        model.addAttribute("puma", service.getOne(id));
        return "puma";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("puma", new Puma());
        return "puma-form";
    }

    @PostMapping
    public String create(@ModelAttribute Puma puma) {
        service.create(puma);
        return "redirect:/pumas";
    }

    @GetMapping("/{id}/update")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("puma", service.getOne(id));
        return "puma-update";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Puma puma) {
        service.update(id, puma);
        return "redirect:/pumas";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/pumas";
    }
}
