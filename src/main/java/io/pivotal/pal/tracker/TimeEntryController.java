package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        return new ResponseEntity(timeEntryRepository.create(timeEntryToCreate), HttpStatus.CREATED);
    }

    @PutMapping(value="/time-entries/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry res = timeEntryRepository.update(id, timeEntryToCreate);
        if (res == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long id) {
        timeEntryRepository.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry res = timeEntryRepository.find(id);
        if (res == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @GetMapping(value="/time-entries", headers="X-api-version=2.0")
    public ResponseEntity<String> list2_0() {
        return new ResponseEntity("Some Version 2.0 content", HttpStatus.OK);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        try {
            return new ResponseEntity(timeEntryRepository.list(), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
