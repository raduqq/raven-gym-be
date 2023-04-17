package raven.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import raven.backend.entity.Discipline;
import raven.backend.service.DisciplineService;

import java.util.List;

@RestController
public class DisciplineController {
    @Autowired
    private DisciplineService disciplineService;

    @PostMapping("/disciplines")
    public Discipline saveDiscipline(
            @RequestBody Discipline discipline)
    {
        return disciplineService.saveDiscipline(discipline);
    }

    @GetMapping("/disciplines")
    public List<Discipline> getDisciplines()
    {
        return disciplineService.getDisciplines();
    }

    @PutMapping("/disciplines/{id}")
    public Discipline updateDiscipline(@RequestBody Discipline discipline,
                                       @PathVariable("id") Integer disciplineId)
    {
        return disciplineService.updateDiscipline(discipline, disciplineId);
    }

    @DeleteMapping("/disciplines/{id}")
    public String deleteDisciplineById(@PathVariable("id")
                                       Integer disciplineId)
    {
        disciplineService.deleteDisciplineById(disciplineId);

        return "Deleted Successfully";
    }
}
