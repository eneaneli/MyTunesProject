/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template song, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.GUI;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import mytunes.BE.Songs;

/**
 * FXML Controller class
 *
 * @author Anni
 */
public class AddWindowController implements Initializable {

    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtArtist;
    @FXML
    private TextField txtGenre;
    @FXML
    private TextField txtTime;
    @FXML
    private Button btnChoose;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    @FXML
    private TextField txtFileLocation;
    private MainWindowController parent;
    private Window stage;
    Model model = new Model();
    private Songs editZong;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Lets you pick a file from your computer.
     * @param event 
     */
    @FXML
    private void clickChoose(ActionEvent event) {
        String absolutePath = null;

        final FileChooser fileChooser = new FileChooser();

        File song = fileChooser.showOpenDialog(stage);
        if (song != null) {
            absolutePath = song.getAbsolutePath();
        }

        txtFileLocation.setText(absolutePath);
    }

    /**
     * Cancels new song and closes the window.
     * @param event 
     */
    @FXML
    private void clickCancel(ActionEvent event) {
        Stage window = (Stage) btnCancel.getScene().getWindow();
        window.close();
    }

    /**
     * Either saves or updates a song, depending on if a song is selected or not.
     * @param event 
     */
    @FXML
    private void clickSave(ActionEvent event) {
        if (editZong == null) {
            Songs s = new Songs();
            s.setTitle(txtTitle.getText());
            s.setArtist(txtArtist.getText());
            s.setGenre(txtGenre.getText());
            s.setTime(txtTime.getText());
            s.setFileLocation(txtFileLocation.getText());
            
            model.add(s);

        } else {
            editZong.setTitle(txtTitle.getText());
            editZong.setArtist(txtArtist.getText());
            editZong.setGenre(txtGenre.getText());
            editZong.setTime(txtTime.getText());
            editZong.setFileLocation(txtFileLocation.getText());
            
            model.editSongs(editZong);
        }

        Stage window = (Stage) btnSave.getScene().getWindow();
        window.close();
    }

    /**
     * Sets the parent to MainWindowController, also choses if txtfields should be filled out
     * in order to edit a song.
     * @param parent
     * @param getSelectedSong 
     */
    public void setParentWindowController(MainWindowController parent, Songs getSelectedSong) {
        this.parent = parent;
        this.editZong = getSelectedSong;

        if (getSelectedSong != null) {
            txtTitle.setText(parent.getSelectedSong().getTitle());
            txtArtist.setText(parent.getSelectedSong().getArtist());
            txtGenre.setText(parent.getSelectedSong().getGenre());
            txtTime.setText(parent.getSelectedSong().getTime());
            txtFileLocation.setText(parent.getSelectedSong().getFileLocation());
        }
    }

}
