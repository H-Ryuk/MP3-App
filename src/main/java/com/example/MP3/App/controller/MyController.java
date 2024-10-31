package com.example.MP3.App.controller;


import com.example.MP3.App.service.MP3PlayerWithJavaZoom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {


    private static final Logger log = LoggerFactory.getLogger(MyController.class);
    private final MP3PlayerWithJavaZoom mp3Player;

    public MyController(MP3PlayerWithJavaZoom mp3Player) {
        this.mp3Player = mp3Player;
    }



    @GetMapping("/")
    public String call(Model model){
        model.addAttribute("filesList", mp3Player.filesList);
        return "MyInterface";
    }


    @GetMapping("playFile")
    public String playFile() throws InterruptedException {
        mp3Player.play("resume");
        return "redirect:/";
    }



    @GetMapping("pauseFile")
    public String pauseFile(){
        mp3Player.pause();
        return "redirect:/";
    }



    @GetMapping("nextFile")
    public String nextFile(){
        mp3Player.play("next");
        return "redirect:/";
    }



    @GetMapping("previousFile")
    public String previousFile(){
        mp3Player.play("previous");
        return "redirect:/";
    }


    @GetMapping("/playingFromList")
    public String playFromList(@RequestParam("filename") String filename){
        System.out.println(filename);
        log.info("ddddddddddd : " + filename);
        mp3Player.playingFromList(filename);
        return "redirect:/";
    }

}
