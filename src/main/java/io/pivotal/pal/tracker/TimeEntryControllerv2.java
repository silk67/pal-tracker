package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryControllerv2 {
    TimeEntryRepository timeEntryRepository;

    public TimeEntryControllerv2(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping(value="/time-entries", headers="X-api-version=2.0")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        return new ResponseEntity(timeEntryRepository.create(timeEntryToCreate), HttpStatus.CREATED);
    }

    @PutMapping(value="/time-entries/{id}", headers="X-api-version=2.0")
    public ResponseEntity update(@PathVariable long id, @RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry res = timeEntryRepository.update(id, timeEntryToCreate);
        if (res == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @DeleteMapping(value="/time-entries/{id}", headers="X-api-version=2.0")
    public ResponseEntity<TimeEntry> delete(@PathVariable long id) {
        timeEntryRepository.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value="/time-entries/{id}", headers="X-api-version=2.0")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry res = timeEntryRepository.find(id);
        if (res == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @GetMapping(value="/time-entries", headers="X-api-version=2.0")
    public ResponseEntity<String> NoList() {
        return new ResponseEntity("Some Version 2.0 content", HttpStatus.OK);
    }

    @GetMapping(value="/time-entries/status", headers="X-api-version=2.0")
    public ResponseEntity<String> status() {
        return new ResponseEntity("Statused", HttpStatus.OK);
    }

}
