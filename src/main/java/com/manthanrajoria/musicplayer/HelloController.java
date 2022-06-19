package com.manthanrajoria.musicplayer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HelloController {

    @FXML
    private TextField locationField;

    @FXML
    private Button loadButton;

    @FXML
    private RadioButton radio1;

    @FXML
    private RadioButton radio2;

    @FXML
    private TextField indexField;

    @FXML
    private Label errorLabel;

    @FXML
    private Button playButton;

    @FXML
    private ListView listViewer;

    static MP3Player mp3Player = new MP3Player("");
    private String path;
    private boolean isPlaying = false;
    Playlist playlist = new Playlist("DS");
    List<String> results = new ArrayList<>();

    public void openFilePicker() {
        try {
            DirectoryChooser dChooser = new DirectoryChooser();
            File file = dChooser.showDialog(null);
            if(file == null){
                throw new Exception("Select a folder.");
            }
            locationField.setText(file.getAbsolutePath());
        }catch (Exception e){
            errorLabel.setText(e.getMessage());
        }
    }

    public int loadMusic() {
        try {
            errorLabel.setText("");
            if (!locationField.getText().equals("")) {
                path = locationField.getText();
                File[] files = new File(path).listFiles();
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith("mp3")) {
                        results.add(file.getName());
                        playlist.getSongs().add(file.getAbsolutePath());
                    }
                }
                int i = 0;
                for (String songName : results) {
                    i++;
                    listViewer.getItems().add(i + "\t\t" + songName);
                }
                loadButton.setDisable(true);
                playButton.setDisable(false);
                radio1.setDisable(false);
                radio2.setDisable(false);
            }
        }catch(Exception e){
            errorLabel.setText("Something went wrong ! Check your address.");
        }
        return 0;
    }

    public int playMusic(ActionEvent actionEvent) {
        errorLabel.setText("");
        if(!radio1.isSelected()  && listViewer.isDisable()){
            errorLabel.setText("Please select a song.");
            return 0;
        }
        try{
            String filename = "";
            if(radio1.isSelected()) {
                while (true) {
                    String input = indexField.getText();
                    if (input.equalsIgnoreCase("stop") || input.equals("")) {
                        errorLabel.setText("Pausing play / No input");
                        mp3Player.close();
                        return 0;
                    }
                    int trackNo = Integer.parseInt(input);
                    if (trackNo > playlist.getSongs().size() || trackNo < 1) {
                        errorLabel.setText("Track out of bounds !");
                    } else {
                        filename = playlist.getSongs().get(trackNo - 1);
                    }
                    break;
                }
            }

            if(radio2.isSelected()){
                if(listViewer.getSelectionModel().getSelectedItem() == null){
                    errorLabel.setText("No song selected.");
                    return 0;
                }
                int indexofTab = listViewer.getSelectionModel().getSelectedItem().toString().lastIndexOf("\t");
                String listname = listViewer.getSelectionModel().getSelectedItem().toString().substring(indexofTab+1);
                File[] files = new File(path).listFiles();
                for (File file : files) {
                    if (file.isFile()) {
                        if(file.getName().equals(listname)){
                            filename = file.getAbsolutePath();
                        }
                    }
                }
            }

            playMusic(filename);
        }catch(Exception e){
            errorLabel.setText("We ran into a problem. Check your file.");
        }
        return 0;
    }

    public int indexplayController(){
        indexField.setDisable(false);
        listViewer.setDisable(true);
        return 0;
    }

    public int listplayController(){
        indexField.setDisable(true);
        listViewer.setDisable(false);
        return 0;
    }

    public int playMusic(String filename){
        if(filename == null || filename.equals("")) {
            errorLabel.setText("Program Ending");
        }else{
            if(isPlaying == false) {
                mp3Player = new MP3Player(filename);
                mp3Player.play();
                isPlaying = true;
            }else{
                mp3Player.close();
                isPlaying = false;
            }
        }
        return  0;
    }

}