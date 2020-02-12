package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/whiskies")
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @Autowired
    DistilleryRepository distilleryRepository;

    @GetMapping(value = "/{year}")
    public ResponseEntity<List<Whisky>> findWhiskiesFromCertainYear(@PathVariable int year) {
        return new ResponseEntity<>(whiskyRepository.findByYear(year), HttpStatus.OK);
    }

    @GetMapping(value = "/distillery")
    public ResponseEntity<List<Whisky>> findWhiskiesFromCertainDistilleryAndAge(@RequestParam(name = "named") String name, @RequestParam(name = "age") int age) {
        Distillery foundDistillery = distilleryRepository.findDistinctDistilleryByName(name);
        return new ResponseEntity<>(whiskyRepository.findWhiskiesByDistilleryAndAge(foundDistillery, age), HttpStatus.OK);
    }

}
