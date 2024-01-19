package com.project_rabbit.Epic;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/epic")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class EpicController {

    private final EpicService epicService;

    @PostMapping("/add")
    public ResponseEntity<Integer> createEpic(@RequestBody Epic epic) {
        Integer epicId = epicService.createEpic(epic);
        return new ResponseEntity<>(epicId, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Epic> findEpic(@PathVariable("id") Integer epicId) {
        Epic theEpic = epicService.findEpic(epicId).stream().findFirst().orElseThrow(() -> new RuntimeException(
                String.format("epic with id %s does not exist", epicId)));

        return new ResponseEntity<>(theEpic, HttpStatus.OK);
    }

    @GetMapping("/all-epics")
    public ResponseEntity<List<Epic>> getAllEpics() {
        List<Epic> epics = epicService.getAllEpics();
        return ResponseEntity.ok(epics);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Epic> updateEpic(@PathVariable("id") Integer epicId, @RequestBody Epic epic) {

        Epic theEpic = epicService.updateEpic(epicId, epic);
        return new ResponseEntity<>(theEpic, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteEpic(@PathVariable("id") Integer epicId) {
        epicService.deleteEpic(epicId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
