package com.rano.rano.controller;

import com.rano.rano.entity.ButtonClick;
import com.rano.rano.repository.ButtonClickRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/track")
public class ButtonClickController {

    private final ButtonClickRepository buttonClickRepository;

    public ButtonClickController(ButtonClickRepository buttonClickRepository) {
        this.buttonClickRepository = buttonClickRepository;
    }

    @PostMapping("/click")
    public void logClick(@RequestParam String label) {
        ButtonClick click = new ButtonClick();
        click.setLabel(label);
        click.setClickDate(LocalDate.now());
        buttonClickRepository.save(click);
    }
}
