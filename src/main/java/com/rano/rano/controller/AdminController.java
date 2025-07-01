package com.rano.rano.controller;

import com.rano.rano.repository.ButtonClickRepository;
import com.rano.rano.repository.VisitLogRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final VisitLogRepository visitLogRepository;
    private final ButtonClickRepository buttonClickRepository;

    public AdminController(VisitLogRepository visitLogRepository, ButtonClickRepository buttonClickRepository) {
        this.visitLogRepository = visitLogRepository;
        this.buttonClickRepository = buttonClickRepository;
    }

    @GetMapping("/stats")
    public String stats(Model model) {
        List<Map<String, Object>> stats = visitLogRepository.countVisitsByDay();
        List<Map<String, Object>> clicks = buttonClickRepository.countClicksByDayAndLabel();
        List<Map<String, Object>> totalClicks = buttonClickRepository.countTotalClicksByLabel();

        model.addAttribute("stats", stats);
        model.addAttribute("clicks", clicks);
        model.addAttribute("totalClicks", totalClicks);

        return "stats";
    }


}
