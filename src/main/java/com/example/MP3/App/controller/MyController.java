package com.example.MP3.App.controller;


import com.example.MP3.App.service.MP3PlayerWithJavaZoom;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {



    private final MP3PlayerWithJavaZoom mp3Player;

    public MyController(MP3PlayerWithJavaZoom mp3Player) {
        this.mp3Player = mp3Player;
    }



    @GetMapping("/")
    public String call(){
        return "MyInterface";
    }


    @GetMapping("playFile")
    public String playFile() throws InterruptedException {
        mp3Player.play("resume");
        return "MyInterface";
    }



    @GetMapping("pauseFile")
    public String pauseFile(){
        mp3Player.pause();
        return "MyInterface";
    }



    @GetMapping("nextFile")
    public String nextFile(){
        mp3Player.play("next");
        return "MyInterface";
    }



    @GetMapping("previousFile")
    public String previousFile(){
        mp3Player.play("previous");
        return "MyInterface";
    }



}
