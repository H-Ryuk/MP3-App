package com.example.MP3.App.service;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class MP3PlayerWithJavaZoom {

    private BasicPlayer player;
    private String filePath;
    private boolean isPaused;
    private int count;


    @Value("${myFilesPath}")
    private String myFilesPath;


    private List<String> filesList = new ArrayList<>();

    public List<String> getFilesList() {
        return filesList;
    }

    public void setFilesList(List<String> filesList) {
        this.filesList = filesList;
    }

    public MP3PlayerWithJavaZoom() {
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
    public String play(String play) {
        String playingFileName = "";
        
        try {
            if (isPaused && !play.equalsIgnoreCase("next") && !play.equalsIgnoreCase("previous")) {
                player.resume();
                isPaused = false;
            } else {
                if (play != null) player.stop();
                assert play != null;
                if (play.equalsIgnoreCase("next") && count < filesList.size()) count++;
                if (play.equalsIgnoreCase("previous") && count > 0 || count == filesList.size()) count--;


                player.open(new File(filesList.get(count)));
                player.play();
                playingFileName = filesList.get(count).substring(28);
                System.out.println("Playing: " + playingFileName);

            }
        } catch (BasicPlayerException e) {
            System.out.println("Error playing the file: " + e.getMessage());
            e.printStackTrace();
        }
        return playingFileName;
    }


    // Method to play the MP3 file
    public String playingFromList(String path) {
        String playingFileName = "";
        try {
            player.open(new File(path));
            player.play();
            isPaused = false;
            playingFileName = path.substring(28);
            System.out.println("Playing: " + playingFileName);

        } catch (BasicPlayerException e) {
            System.out.println("Error playing the file: " + e.getMessage());
            e.printStackTrace();
        }
        return playingFileName;
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



    public void settingFiles(){
        File directory = new File(myFilesPath);

        // List all files with .mp3 extension
        File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".mp3"));
        if (files != null) {
            for (File file : files) {
                filesList.add(file.getAbsolutePath());
            }
        }
    }




}
