package com.example.SportDesk.controller;

import com.example.SportDesk.domain.Message;
import com.example.SportDesk.domain.Sportsman;
import com.example.SportDesk.domain.User;
import com.example.SportDesk.service.SportsmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class RegController {

    @Autowired
    private SportsmanService sportsmanService;



    @PostMapping("/register")
    public String addUser(
            @RequestParam("firstname") String name,
            @RequestParam("lastname") String lastname,
            @Valid User user,
            Model model){


        model.addAttribute("messageType","info");
        model.addAttribute("message", "Посетите ваш почтовый ящик и подтвердите ваш адрес электронной почты");
        return "main";
    }

    @GetMapping("/user-sportsmen/{user}")
    public String userMessges(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            Model model,
            @RequestParam(required = false) Sportsman sportsman,
            @RequestParam(required = false) Message message,
            @PageableDefault(sort = {"lastname"}, direction = Sort.Direction.ASC) Pageable pageable
    ){

        Page<Sportsman> page = sportsmanService.sportsmanListForUser(pageable, currentUser);

        model.addAttribute("sportsman", sportsman);
        model.addAttribute("message", message);
        model.addAttribute("page", page);
        model.addAttribute("url", "/user-messages/" + user);

        return "UserSportsmen";
    }
}
