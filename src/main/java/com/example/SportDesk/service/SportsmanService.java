package com.example.SportDesk.service;

import com.example.SportDesk.domain.Sportsman;
import com.example.SportDesk.domain.User;

import com.example.SportDesk.repos.SportsmanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class SportsmanService {
    @Autowired
    private SportsmanRepo sportsmanRepo;


    public Page<Sportsman> sportsmanListForUser(Pageable pageable, User currentUser) {
        return sportsmanRepo.findByUser(pageable, currentUser);
    }
}
