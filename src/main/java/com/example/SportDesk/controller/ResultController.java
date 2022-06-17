package com.example.SportDesk.controller;
import com.example.SportDesk.domain.Message;
import com.example.SportDesk.domain.User;
import com.example.SportDesk.domain.dto.MessageDto;
import com.example.SportDesk.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResultController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/results")
    public String results(
            @RequestParam(required = false, defaultValue = "")
                    String filter,
            Model model,
            @PageableDefault(sort = {"date"}, direction = Sort.Direction.DESC) Pageable pageable,
            @AuthenticationPrincipal User user
    ) {
        Page<MessageDto> page = messageService.messageList(pageable, filter, user);

        model.addAttribute("page", page);
        model.addAttribute("url", "/main");
        model.addAttribute("filter", filter);

        return "results";
    }

}