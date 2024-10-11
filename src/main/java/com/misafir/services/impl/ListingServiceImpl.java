package com.misafir.services.impl;

import com.misafir.dto.DtoListing;
import com.misafir.entities.Listing;
import com.misafir.repositories.ListingRepository;
import com.misafir.services.ListingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListingServiceImpl implements ListingService {

    @Autowired
    private ListingRepository listingRepository;

    @Override
    public List<DtoListing> getAllListings() {
        List<Listing> listings = listingRepository.findAll();

        // Map each Listing entity to DtoListing
        return listings.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private DtoListing convertToDto(Listing listing) {
        DtoListing dtoListing = new DtoListing();
        BeanUtils.copyProperties(listing, dtoListing); // Copy properties from entity to DTO
        return dtoListing;
    }
}
