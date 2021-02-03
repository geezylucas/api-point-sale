package com.prosis.app.controllers;

import com.prosis.app.DTOs.MovementsResponse;
import com.prosis.app.DTOs.ReturnsResponse;
import com.prosis.app.DTOs.SellRequest;
import com.prosis.app.DTOs.SellResponse;
import com.prosis.app.services.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sell")
public class SellController {
    private final SellService sellService;

    @Autowired
    public SellController(SellService sellService) {
        this.sellService = sellService;
    }

    @PostMapping
    public ResponseEntity<SellResponse> sellProducts(@RequestBody SellRequest sellRequest) {
        return new ResponseEntity<>(sellService.sellProducts(sellRequest), new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping("/movements")
    public ResponseEntity<?> allMovementsWithQuantity() {
        List<MovementsResponse> movementsResponseList = sellService.getMovementsAllWithQuantity();
        Map<String, Object> response = new HashMap<>();
        response.put("data", movementsResponseList);
        response.put("total", movementsResponseList.size());
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/returns")
    public ResponseEntity<List<ReturnsResponse>> allReturnsToday() {
        return new ResponseEntity<>(sellService.getReturnsToday(), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/returns/{id}")
    public ResponseEntity<?> detailsReturn(@PathVariable Integer id) {
        return new ResponseEntity<>(sellService.getDetailsReturn(id), new HttpHeaders(), HttpStatus.OK);
    }
}
