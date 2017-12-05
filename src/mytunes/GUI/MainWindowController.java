/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Polygon;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mytunes.BE.Playlist;
import mytunes.BE.Playlists;
import mytunes.BE.Songs;
import mytunes.DAL.ConnectionManager;
import mytunes.DAL.DALManager;

/**
 *
 * @author Jesper
 */
public class MainWindowController implements Initializable
{
public class MainWindowController implements Initializable {

    JFXPanel fxPanel = new JFXPanel();
    private Media media;
    private MediaPlayer player;
    private MediaView mediaView;
    private Model model = new Model();
    private ConnectionManager cm = new ConnectionManager();
    private Playlists playlists = new Playlists();
    private Songs sSong;
    

    @FXML
    private Button btnNewPlaylist;
    @FXML
    private Button btnEditPlaylist;
    @FXML
    private Button btnDeletePlaylist;
    @FXML
    private Button btnUp;
    @FXML
    private Button btnDown;
    @FXML
    private Button btnPlaylistDelete;
    @FXML
    private Button btnNewSong;
    @FXML
    private Button btnEditSong;
    @FXML
    private Button btnDeleteSong;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnAddSong;
    @FXML
    private Button forwButton;
    @FXML
    private Button backButton;
    @FXML
    private Button ppButton;
    @FXML
    private Slider volumeControl;
    @FXML
    private Polygon playPane;
    @FXML
    private Pane pausePane;
    @FXML
    private Button btnLoadSongs;
    @FXML
    private TableView<Playlists> lstPlaylists;
    @FXML
    private TableColumn<Playlists, String> PlaylistView;
    @FXML
    private TableView<Playlist> lstSongsInPlaylist;
    @FXML
    private TableColumn<Playlist, String> titleView;
    //now putting it over doesn't work, sorry jesper
    @FXML
    private TableView<Songs> lstSongs;
    @FXML
    private TableColumn<Songs, String> columnTitle;
    @FXML
    private TableColumn<Songs, String> columnArtist;
    @FXML
    private TableColumn<Songs, String> columnGenre;
    @FXML
    private TableColumn<Songs, String> columnTime;
    @FXML
    private TableColumn<Songs, String> columnFileLocation;
    private Songs songPlaying;
    

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    public void initialize(URL url, ResourceBundle rb) {
        PlaylistView.setCellValueFactory(
            new PropertyValueFactory("name"));
        
                new PropertyValueFactory("name"));

        titleView.setCellValueFactory(
            new PropertyValueFactory("songsTitle"));
                new PropertyValueFactory("songsTitle"));
        //is it not supposed to be title?
        

        columnTitle.setCellValueFactory(
            new PropertyValueFactory("title"));
                new PropertyValueFactory("title"));
        columnArtist.setCellValueFactory(
            new PropertyValueFactory("artist"));
                new PropertyValueFactory("artist"));
        columnGenre.setCellValueFactory(
            new PropertyValueFactory("genre"));
                new PropertyValueFactory("genre"));
        columnTime.setCellValueFactory(
            new PropertyValueFactory("time"));
                new PropertyValueFactory("time"));
        columnFileLocation.setCellValueFactory(
            new PropertyValueFactory("fileLocation"));
        
                new PropertyValueFactory("fileLocation"));

        lstSongs.setItems(model.getSongsList());
        lstPlaylists.setItems(model.getPlaylistsList());
        lstSongsInPlaylist.setItems(model.getSongsInPlaylistList());

        lstPlaylists.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Playlists>()
            { 
                @Override
                public void changed(ObservableValue<? extends Playlists> observable, Playlists oldValue, Playlists newValue)
                {
                    model.loadAllSP(newValue.getId());
                }
        lstPlaylists.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Playlists>() {
            @Override
            public void changed(ObservableValue<? extends Playlists> observable, Playlists oldValue, Playlists newValue) {
                model.loadAllSP(newValue.getId());
            }
        }
        );

    
    }
    
    public String getSongSelected()
    {
     return lstSongsInPlaylist.getSelectionModel().getSelectedItem().getSongsFileLocation();

    private String getSongSelected() {
        return lstSongsInPlaylist.getSelectionModel().getSelectedItem().getSongsFileLocation();
    }
    
    public String getTheSongSelected()
    { 
    
     return lstSongs.getSelectionModel().getSelectedItem().getFileLocation();

    private String getTheSongSelected() {

        return lstSongs.getSelectionModel().getSelectedItem().getFileLocation();
    }
    
    private void volumeControl()
    {   
             
        volumeControl.setValue(player.getVolume()*100);
        

    private void volumeControl() {

        volumeControl.setValue(player.getVolume() * 100);

        volumeControl.setValue(player.getVolume() * 100);
        volumeControl.valueProperty().addListener(new InvalidationListener()
        {
        volumeControl.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable)
            {
            public void invalidated(Observable observable) {
                player.setVolume(volumeControl.getValue() / 100);
            }
        });
    }
       
    public void setPauseButton() {

    private void setPauseButton() {
        player.setAutoPlay(true);
        playPane.setOpacity(0);
        pausePane.setOpacity(1);
    }

    public void setPlayButton() {
    private void setPlayButton() {
        player.pause();
        player.setAutoPlay(false);
        playPane.setOpacity(1);
        pausePane.setOpacity(0);
    }
    

    private void playerMediaPlayer() {

        media = new Media(new File(getSongSelected()).toURI().toString());
        player = new MediaPlayer(media);
        mediaView = new MediaView(player);
    }

    @FXML
    public void clickPlay(ActionEvent event)
    {   
       if (player != null && getSelectedSong() == songPlaying) {
    private void clickPlay(ActionEvent event) {
        if (player != null && getSelectedSong() == songPlaying) {
            if (!player.isAutoPlay()) {

                setPauseButton();
            } else {

                setPlayButton();
            }
        } else {
            if (player != null) {
                player.stop();
                
            }

            }
            playerMediaPlayer();
            volumeControl();
            setPauseButton();
        }
        songPlaying = getSelectedSong();
    }

    @FXML
    private void clickNewPlaylist(ActionEvent event) throws IOException {
        Stage newWindow = new Stage();

        newWindow.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("NewPlaylist.fxml"));

        Parent root = fxLoader.load();

        NewPlaylistController controller = fxLoader.getController();
        controller.setParentWindowController(this);

        Scene scene = new Scene(root);
        newWindow.setTitle("Add a Playlist");
        newWindow.setScene(scene);
        newWindow.showAndWait();
    }

    //can we please rename it to getSelectedPlaylist?
    public Playlists getSelected() {
        return lstPlaylists.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void clickEditPlaylist(ActionEvent event) throws IOException {
        Stage newWindow = new Stage();

        newWindow.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("NewPlaylist.fxml"));

        Parent root = fxLoader.load();

        NewPlaylistController controller = fxLoader.getController();
        controller.setParentWindowController(this);

        Scene scene = new Scene(root);

        newWindow.setTitle("Edit Playlist");
        newWindow.setScene(scene);
        newWindow.showAndWait();
        
                Playlists playlists

        Playlists playlists
                = lstPlaylists.getSelectionModel().getSelectedItem();
        

        model.editPlaylists(playlists);

    }

    @FXML
    private void clickDeletePlaylist(ActionEvent event) throws IOException
    {
    private void clickDeletePlaylist(ActionEvent event) throws IOException {
        Stage newWindow = new Stage();

        newWindow.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("DeletePlaylistWindow.fxml"));

        Parent root = fxLoader.load();

        DeletePlaylistWindowController controller = fxLoader.getController();
        controller.setParentWindowController(this);

        Scene scene = new Scene(root);
        newWindow.setTitle("Delete");
        newWindow.setScene(scene);
        newWindow.showAndWait();
    }

    private void moveSong(int moveIndex)
    {
    private void moveSong(int moveIndex) {
        /*
        int selectedSongId = lstSongsInPlaylist.getSelectionModel().getSelectedItem().getId();
        int selectedSongIndex = lstSongsInPlaylist.getSelectionModel().getSelectedIndex();
        
        lstSongsInPlaylist.getSelectionModel().select(selectedSongIndex + moveIndex);
        
        int selectedSongNewId = lstSongsInPlaylist.getSelectionModel().getSelectedItem().getId();
         */

        Playlists selectedPlaylist = lstPlaylists.getSelectionModel().getSelectedItem();
        model.loadAllSP(selectedPlaylist.getId());
    }

    @FXML
    private void clickUp(ActionEvent event) {
        moveSong(-1);
    }

    @FXML
    private void clickDown(ActionEvent event) {
        moveSong(+1);
    }

    @FXML
    private void clickPlaylistDelete(ActionEvent event) throws IOException {
        Stage newWindow = new Stage();

        newWindow.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("DeleteSongInPlaylistWindow.fxml"));

        Parent root = fxLoader.load();

        DeleteSongsInPlaylistWindowController controller = fxLoader.getController();
        controller.setParentWindowController(this);

        Scene scene = new Scene(root);
        newWindow.setTitle("Delete");
        newWindow.setScene(scene);
        newWindow.showAndWait();
    }

    @FXML
    private void clickNewSong(ActionEvent event) throws IOException {
        Stage newWindow = new Stage();

        newWindow.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("AddWindow.fxml"));

        Parent root = fxLoader.load();

        AddWindowController controller = fxLoader.getController();
        controller.setParentWindowController(this, null);

        Scene scene = new Scene(root);
        

        newWindow.setTitle("Add a Song");
        newWindow.setScene(scene);
        newWindow.showAndWait(); 
       
        newWindow.showAndWait();

    }
    
    public Songs getSelectedSong()
    {

    public Songs getSelectedSong() {
        return lstSongs.getSelectionModel().getSelectedItem();
    }
    
    public Playlists getSelectedPlaylist()
    {

    public Playlists getSelectedPlaylist() {
        return lstPlaylists.getSelectionModel().getSelectedItem();
    }
    
    public Playlist getSelectedSongInPlaylist()
    {

    public Playlist getSelectedSongInPlaylist() {
        return lstSongsInPlaylist.getSelectionModel().getSelectedItem();
    }
      

    @FXML
    private void clickEditSong(ActionEvent event) throws IOException
    {
    private void clickEditSong(ActionEvent event) throws IOException {
        getSelectedSong();
        Stage newWindow = new Stage();

        newWindow.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("AddWindow.fxml"));

        Parent root = fxLoader.load();

        AddWindowController controller = fxLoader.getController();
        

        controller.setParentWindowController(this, getSelectedSong());

        Scene scene = new Scene(root);
        newWindow.setTitle("Edit Song");
        newWindow.setScene(scene);
        newWindow.showAndWait();

    }
    

    @FXML
    private void clickDeleteSong(ActionEvent event) throws IOException
    {
    private void clickDeleteSong(ActionEvent event) throws IOException {
        Stage newWindow = new Stage();

        newWindow.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("DeleteWindow.fxml"));

        Parent root = fxLoader.load();

        DeleteWindowController controller = fxLoader.getController();
        controller.setParentWindowController(this);

        Scene scene = new Scene(root);
        newWindow.setTitle("Delete");
        newWindow.setScene(scene);
        newWindow.showAndWait();
    }

    //evt get text method 
    @FXML
    private void clickSearch(ActionEvent event)
    {
        model.searchTitle(txtSearch.getText(),txtSearch.getText());
        System.out.println("Searching for song or artist");
    private void clickSearch(ActionEvent event) {
        model.search(txtSearch.getText());

        System.out.println("Searching for song");
>>>>>>> d8e0158b3f0a69f247abd93ad7ddb65ff1d9da06
    }
     

    @FXML
    private void clickAddSong(ActionEvent event)
    {
    private void clickAddSong(ActionEvent event) {
        System.out.println("Adding song to playlist.");
    

        Playlists selectedPlaylists = lstPlaylists.getSelectionModel().getSelectedItem();
        Songs selectedSongs = lstSongs.getSelectionModel().getSelectedItem();
        
        try (Connection con = cm.getConnection())
        {

        try (Connection con = cm.getConnection()) {
            String sql = "INSERT INTO Playlist "
                    + "(Playlists_id, Songs_title, Songs_artist, Songs_genre, Songs_time, Songs_fileLocation) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

    
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, selectedPlaylists.getId());
            pstmt.setString(2, selectedSongs.getTitle());
            pstmt.setString(3, selectedSongs.getArtist());
            pstmt.setString(4, selectedSongs.getGenre());
            pstmt.setString(5, selectedSongs.getTime());
            pstmt.setString(6, selectedSongs.getFileLocation());

            int affected = pstmt.executeUpdate();
            if (affected < 1)
            {
            if (affected < 1) {
                throw new SQLException("Song could not be added");
            }


            pstmt.executeUpdate();

        } catch (SQLException ex)
        {
        } catch (SQLException ex) {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
    

    @FXML
    private void clickForw(ActionEvent event)
    {
    private void clickForw(ActionEvent event) {
    }

    @FXML
    private void clickBack(ActionEvent event)
    {
    private void clickBack(ActionEvent event) {
    }

    @FXML
    private void clickLoadSDB(ActionEvent event) {
        model.loadAll();
    }

    @FXML
    private void clickLoadPDB(ActionEvent event) {
        model.loadAllP();
    }

}
