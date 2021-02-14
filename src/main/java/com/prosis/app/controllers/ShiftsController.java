package com.prosis.app.controllers;

import com.prosis.app.DTOs.CloseShiftRequest;
import com.prosis.app.entities.BoxEntity;
import com.prosis.app.services.BoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shifts")
public class ShiftsController {
    private final BoxService boxService;

    @Autowired
    public ShiftsController(BoxService boxService) {
        this.boxService = boxService;
    }

    @PutMapping
    private ResponseEntity<?> closeShift(@RequestBody CloseShiftRequest closeShiftRequest) {
        BoxEntity boxEntity = boxService.closeShift(closeShiftRequest);
        if (boxEntity != null)
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
