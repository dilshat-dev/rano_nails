package com.rano.rano.controller;

import com.rano.rano.repository.VisitLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private VisitLogRepository visitLogRepository;

    @GetMapping("/stats")
    public String stats(Model model) {
        List<Map<String, Object>> stats = visitLogRepository.countVisitsByDay();
        model.addAttribute("stats", stats);
        return "stats";
    }

}
