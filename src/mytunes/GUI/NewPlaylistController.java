/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mytunes.BE.Playlists;

/**
 * FXML Controller class
 *
 * @author Anni
 */
public class NewPlaylistController implements Initializable
{

    @FXML
    private TextField txtName;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;

    private MainWindowController parent;
    private Model model = new Model();
    private Playlists editPlaylist;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    /**
     * Saves or updates a playlistname depending on if a Playlist is selected.
     * @param event 
     */
    @FXML
    private void clickSave(ActionEvent event)
    {
        if (editPlaylist == null) {
        Playlists p = new Playlists();
        p.setId(-1);
        p.setName(txtName.getText());

        model.addP(p);
       
        } else {
            editPlaylist.setName(txtName.getText());
            model.editPlaylists(editPlaylist);
        }
        
         Stage window = (Stage) btnSave.getScene().getWindow();
        window.close();
    }

    /**
     * Cancels creating new playlist and closes the window.
     * @param event 
     */
    @FXML
    private void clickCancel(ActionEvent event)
    {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    /**
     * Sets the parent to MainWindowController and decides if txtfields should be filled out in 
     * order to edit the name of the selected playlist
     * @param parent
     * @param getSelectedPlaylist 
     */
    public void setParentWindowController(MainWindowController parent, Playlists getSelectedPlaylist)
    {
        this.parent = parent;
        this.editPlaylist = getSelectedPlaylist;
        
        if (getSelectedPlaylist != null) {
        txtName.setText(parent.getSelectedPlaylist().getName());
        }
    }

}
