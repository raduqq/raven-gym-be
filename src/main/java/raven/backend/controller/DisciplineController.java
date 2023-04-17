package raven.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import raven.backend.service.DisciplineService;

@RestController
public class DisciplineController {
    @Autowired
    private DisciplineService disciplineService;

    // Map to web
}
