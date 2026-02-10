package se.iths.cecilia.postrgreszoo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iths.cecilia.postrgreszoo.model.Monkey;
import se.iths.cecilia.postrgreszoo.repository.MonkeyRepository;
import se.iths.cecilia.postrgreszoo.service.MonkeyService;

@Controller
@RequestMapping("/monkeys")
public class MonkeyController {

    private final MonkeyService monkeyService;
    private final MonkeyRepository monkeyRepository;

    public MonkeyController(MonkeyService monkeyService, MonkeyRepository monkeyRepository) {
        this.monkeyService = monkeyService;
        this.monkeyRepository = monkeyRepository;
    }

    @GetMapping
    public String getAllMonkeys(Model model) {
        model.addAttribute("monkeys", monkeyService.getAllMonkeys());
        return "monkeys";
    }

    @GetMapping("/{id}")
    public String getMonkeyById(@PathVariable Long id, Model model) {
        model.addAttribute("monkey", monkeyService.getMonkey(id));
        return "monkey";
    }

    @PostMapping("/create")
    public String saveMonkey(@ModelAttribute Monkey monkey) {
        monkeyService.createMonkey(monkey);
        return "redirect:/monkeys";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        return "createMonkey";
    }

    @PostMapping
    public String createMonkey(@ModelAttribute Monkey monkey) {
        monkeyService.createMonkey(monkey);
        return "redirect:/monkeys";
    }

    @PutMapping("/{id}")
    public String updateMonkey(@PathVariable Long id, @ModelAttribute Monkey monkey) {
        monkeyService.updateMonkey(id, monkey);
        return "redirect:/monkeys";
    }

    @GetMapping("/{id}/update")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Monkey monkey = monkeyService.getMonkey(id);
        model.addAttribute("monkey", monkey);
        return "redirect:/monkeys";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteMonkeyById(@PathVariable Long id) {
        monkeyService.deleteMonkey(id);
        return "redirect:/monkeys";
    }
}
