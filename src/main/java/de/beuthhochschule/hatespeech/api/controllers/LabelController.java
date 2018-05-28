package de.beuthhochschule.hatespeech.api.controllers;


import de.beuthhochschule.hatespeech.api.model.Label;
import de.beuthhochschule.hatespeech.api.services.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/labels")
@CrossOrigin
public class LabelController {

    @Autowired
    private LabelService labelService;

    @GetMapping("/nb-comments")
    public List<Label> getNumberOfComments() {
        return labelService.getNbComments();
    }
}
