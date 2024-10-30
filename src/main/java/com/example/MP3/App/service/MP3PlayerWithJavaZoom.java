package com.example.MP3.App.service;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class MP3PlayerWithJavaZoom {

    private BasicPlayer player;
    private String filePath;
    private boolean isPaused;
    private int n;


    List<String> filesList =
            List.of("C:\\Users\\ADmiN\\Music\\Quraan\\سورة إبراهيم - عبدالرحمن السديس(MP3_320K).mp3",
                    "C:\\Users\\ADmiN\\Music\\Quraan\\سورة آل عمران - عبدالرحمن السديس(MP3_320K).mp3",
                    "C:\\Users\\ADmiN\\Music\\Quraan\\سورة الأحقاف - عبدالرحمن السديس(MP3_320K).mp3",
                    "C:/Users/ADmiN/Music/Quraan/1سورة الكافرون - عبدالرحمن السديس.mp3");




    public MP3PlayerWithJavaZoom() {
        this.filePath = filesList.getFirst();
        this.player = initBasicPlayer();
        this.isPaused = false;
    }


    private BasicPlayer initBasicPlayer(){
        if(this.player == null){
            return new BasicPlayer();
        }
        return player;
    }



    // Method to play the MP3 file
    public void play(String play) {
        try {
            if (isPaused) {
                player.resume();
                isPaused = false;
            } else {
                if(play != null) player.stop();
                assert play != null;
                if(play.equalsIgnoreCase("next") && n < filesList.size()) n++;
                if(play.equalsIgnoreCase("previous") && n > 0 || n == filesList.size()) n--;


                player.open(new File(filesList.get(n)));
                player.play();
                System.out.println("Playing: " + filesList.get(n));

            }
        } catch (BasicPlayerException e) {
            System.out.println("Error playing the file: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // Method to pause playback
    public void pause() {
        try {
            if (!isPaused) {
                player.pause();
                isPaused = true;
                System.out.println("Playback paused");
            }else {
                player.resume();
                isPaused = false;
            }
        } catch (BasicPlayerException e) {
            System.out.println("Error pausing the file: " + e.getMessage());
            e.printStackTrace();
        }
    }



    // Method to stop playback
    public void stop() {
        try {
            player.stop();
            isPaused = false;
            System.out.println("Playback stopped");
        } catch (BasicPlayerException e) {
            System.out.println("Error stopping the file: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // Optional: Method to set volume (range from 0.0 to 1.0)
    public void setVolume(double volume) {
        try {
            player.setGain(volume);
            System.out.println("Volume set to " + volume);
        } catch (BasicPlayerException e) {
            System.out.println("Error setting volume: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
