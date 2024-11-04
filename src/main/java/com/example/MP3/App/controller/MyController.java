package com.example.MP3.App.controller;


import com.example.MP3.App.service.MP3PlayerWithJavaZoom;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.print.Pageable;

@Controller
public class MyController {


    private final MP3PlayerWithJavaZoom mp3Player;

    public MyController(MP3PlayerWithJavaZoom mp3Player) {
        this.mp3Player = mp3Player;
    }



    @GetMapping("/")
    public String call(Model model){
        mp3Player.settingFiles();
        model.addAttribute("filesList", mp3Player.getFilesList());
        model.addAttribute("playingFileName", model.getAttribute("playingFileName"));
        return "MyInterface";
    }



    @GetMapping("playFile")
    public String playFile(RedirectAttributes redirectAttributes) throws InterruptedException {
        String playingFileName = mp3Player.play("resume");
        redirectAttributes.addFlashAttribute("playingFileName", playingFileName);
        return "redirect:/";
    }



    @GetMapping("pauseFile")
    public String pauseFile(@RequestParam("playingFile") String playingFile, RedirectAttributes redirectAttributes){
        mp3Player.pause();
        redirectAttributes.addFlashAttribute("playingFileName", playingFile);
        return "redirect:/";
    }



    @GetMapping("nextFile")
    public String nextFile(RedirectAttributes redirectAttributes){
        String playingFileName = mp3Player.play("next");
        redirectAttributes.addFlashAttribute("playingFileName", playingFileName);
        return "redirect:/";
    }



    @GetMapping("previousFile")
    public String previousFile(RedirectAttributes redirectAttributes){
        String playingFileName = mp3Player.play("previous");
        redirectAttributes.addFlashAttribute("playingFileName", playingFileName);
        return "redirect:/";
    }



    @GetMapping("/playingFromList")
    public String playFromList(@RequestParam("filename") String filename, RedirectAttributes redirectAttributes){
        String playingFileName = mp3Player.playingFromList(filename);
        redirectAttributes.addFlashAttribute("playingFileName", playingFileName);
        return "redirect:/";
    }


}
