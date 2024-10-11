package com.misafir.controller.impl;

import com.misafir.dto.DtoListing;
import com.misafir.services.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/listing")
public class ListingControllerImpl {

    @Autowired
    private ListingService listingService;

    @GetMapping("/all")
    public ResponseEntity<List<DtoListing>> getAllListings() {
        List<DtoListing> listings = listingService.getAllListings();
        return ResponseEntity.ok(listings);
    }
}
