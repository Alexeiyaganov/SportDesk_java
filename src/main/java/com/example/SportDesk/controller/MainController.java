package com.example.SportDesk.controller;

import com.example.SportDesk.domain.Message;
import com.example.SportDesk.domain.User;
import com.example.SportDesk.repos.MessageRepo;
import com.example.SportDesk.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Point;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private MessageService messageService;


    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(
            @RequestParam(required = false, defaultValue = "")
            String filter,
            Model model,
            @PageableDefault(sort = {"date"}, direction = Sort.Direction.ASC) Pageable pageable,
            @AuthenticationPrincipal User user
    ) {
        Page<Message> page = messageService.messageList(pageable, filter, user);

        model.addAttribute("page", page);
        model.addAttribute("url", "/main");
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Message message,
            BindingResult bindingResult,
            Model model,
            @RequestParam("localDateTime")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime localDateTime,
            @RequestParam("location") String location,
            @RequestParam("location_name") String location_name,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        message.setAuthor(user);

        if (bindingResult.hasErrors()){
            Map<String,String> errorsMap = ControllerUtils.getErrors (bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("message",message);
        }else {

            saveFile(message, file);

            saveDate(message, localDateTime);

            saveUpdate(message);

            saveLocation(message,location);

            saveLocation_name(message,location_name);

            Timestamp lastupdate = new Timestamp(System.currentTimeMillis());
            message.setLastupdate(lastupdate);



            model.addAttribute("message", null);

            messageRepo.save(message);
        }

        Iterable<Message> messages = messageRepo.findAll();

        model.addAttribute("messages", messages);

        return "redirect:/main";
    }


    private void saveDate(@Valid Message message, @RequestParam("localDateTime")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime localDateTime) throws IOException {
        Timestamp ts = Timestamp.valueOf(localDateTime);//new Timestamp(localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli());
        message.setDate(ts);
    }

    private void saveUpdate(@Valid Message message) throws IOException {
        Timestamp lastupdate = new Timestamp(System.currentTimeMillis());
        message.setLastupdate(lastupdate);
    }

    private void saveLocation(@Valid Message message, @RequestParam("location") String location) throws IOException {
        String[] coord = location.split(",");
        Point point= new Point(Double.parseDouble(coord[0]), Double.parseDouble(coord[1])) {
        };
        message.setLatitude(point);
        message.setLongitude(point);
    }

    private void saveLocation_name(@Valid Message message, @RequestParam("location_name") String name) throws IOException {

        message.setLocation(name);
    }




    private void saveFile(@Valid Message message, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            message.setFilename(resultFilename);
        }
    }

    @GetMapping("/user-messages/{user}")
    public String userMessges(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            Model model,
            @RequestParam(required = false) Message message,
            @PageableDefault(sort = {"date"}, direction = Sort.Direction.ASC) Pageable pageable
    ){
        Page<Message> page = messageService.messageListForUser(pageable, currentUser, user);

        //LocalDateTime localDateTime=message.getDate().toLocalDateTime();


        model.addAttribute("userChannel", user);
        model.addAttribute("subscriptionsCount", user.getSubscriptions().size());
        model.addAttribute("subscribersCount", user.getSubscribers().size());
        model.addAttribute("isSubscriber", user.getSubscribers().contains(currentUser));
        model.addAttribute("message", message);
        //model.addAttribute("ldt", localDateTime);
        model.addAttribute("isCurrentUser", currentUser.equals(user));
        model.addAttribute("page", page);
        model.addAttribute("url", "/user-messages/" + user);

        return "userMessages";
    }

    @PostMapping("/user-messages/{user}")
    public String updateMessage(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
            @RequestParam("id") Message message,
            @RequestParam("name") String name,
            @RequestParam("text") String text,
            @RequestParam("tag") String tag,
            @RequestParam("file") MultipartFile file,
            @RequestParam("localDateTime")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime localDateTime,
            @RequestParam("location") String location,
            @RequestParam("location_name") String location_name
    ) throws IOException {
        if(message.getAuthor().equals(currentUser)){
            if(!StringUtils.isEmpty(tag)){
                message.setTag(tag);
            }

            if(!StringUtils.isEmpty(name)){
                message.setName(name);
            }

            if(!StringUtils.isEmpty(text)){
                message.setText(text);
            }

            saveFile(message, file);
            saveDate(message, localDateTime);
            saveUpdate(message);

            if(!StringUtils.isEmpty(location)){
                saveLocation(message,location);
            }

            saveLocation_name(message,location_name);

            messageRepo.save(message);
        }

        return "redirect:/user-messages/"+user;
    }

}
