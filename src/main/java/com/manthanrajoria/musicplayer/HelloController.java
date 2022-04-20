package com.manthanrajoria.musicplayer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HelloController {
    @FXML
    private Label l1;

    @FXML
    private TextField t1;

    @FXML
    private TextField locationSelector;

    @FXML
    private Button btn1;

    @FXML
    private ListView listViewer;

    @FXML
    private RadioButton radio1;

    @FXML
    private RadioButton radio2;

    @FXML
    private Button btn2;

    @FXML
    private Label logo;

    static MP3Player mp3Player = new MP3Player("");
    private String path = "C:\\Users\\GameDemons\\IdeaProjects\\MusicPlayer\\src\\main\\java\\com\\manthanrajoria\\musicplayer\\Songs";
    private boolean isPlaying = false;
    Playlist playlist = new Playlist("DS");
    List<String> results = new ArrayList<>();


    public int loadMusic() {
        if (!locationSelector.getText().equals("")) {
            path = locationSelector.getText();
            File[] files = new File(path).listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    results.add(file.getName());
                    playlist.getSongs().add(file.getAbsolutePath());
                }
            }
            int i = 0;
            for (String a : results) {
                i++;
                listViewer.getItems().add(i + "\t\t" + a);
            }
//        for(String a : playlist.getSongs()){
//            System.out.println(a);
//        }
            btn2.setDisable(true);
            btn1.setDisable(false);
            radio1.setDisable(false);
            radio2.setDisable(false);
        }
        return 0;
    }

    public int playMusic(ActionEvent actionEvent) {

        try{
/*
            for(String a : results){
                System.out.println(a + "\n");
            }
*/
//            playlist.getSongs().add("src/main/java/com/manthanrajoria/musicplayer/Songs/Demon Slayer Main Theme.mp3");
//            playlist.getSongs().add("src/main/java/com/manthanrajoria/musicplayer/Songs/Kamado Tanjirou no Uta.mp3");
            String filename = "";
            String s = "";
            if(radio1.isSelected()) {
                while (true) {
                    String input = t1.getText();
                    if (input.equalsIgnoreCase("stop") || input.equals("")) {
                        mp3Player.close();
                        break;
                    }
                    int trackNo = Integer.parseInt(input);
                    if (trackNo > playlist.getSongs().size() || trackNo < 1) {
                        System.out.println("s");
                        l1.setText("Track out of bounds !");
                    } else {
                        filename = playlist.getSongs().get(trackNo - 1);
                    }
                    break;
                }
            }else if(radio2.isSelected()){
                String listname = listViewer.getSelectionModel().getSelectedItem().toString();
                File[] files = new File(path).listFiles();
                for (File file : files) {
                    if (file.isFile()) {
                        if(file.getName().equals(listname)){
                            filename = file.getAbsolutePath();
                        }
                    }
                }
            }

            if(filename == null || filename.equals("")) {
                l1.setText("Program Ending");
            }else{
                if(isPlaying == false) {
                    mp3Player = new MP3Player(filename);
                    mp3Player.play();
                    isPlaying = true;
                }else{
                    mp3Player.close();
                    isPlaying = false;
                }

                s = t1.getText();

                if (s.equalsIgnoreCase("stop")) {
                    mp3Player.close();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public int indexPlay(){
        t1.setDisable(false);
        listViewer.setDisable(true);
        return 0;
    }

    public int listPlay(){
        t1.setDisable(true);
        listViewer.setDisable(false);
        return 0;
    }
}