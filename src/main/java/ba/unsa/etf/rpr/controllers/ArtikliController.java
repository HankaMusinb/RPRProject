package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ArtikliManager;
import ba.unsa.etf.rpr.controllers.components.DoubleButtonCellFactory;
import ba.unsa.etf.rpr.domain.Artikli;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.util.Date;

/**
 * Controller for managing Articles Entity
 *
 * @author Hanka Musinbegovic
 */
public class ArtikliController {
    //managers
    private final ArtikliManager artikliManager = new ArtikliManager();

    //helper
    @FXML
    public BorderPane ArtikliScreen;

    //components

    public TableView ArtikliTable;

    public TextField search;

    public TableColumn<Artikli, String> idArtikli;

    public TableColumn<Artikli, String> artikliColumn;

    public TableColumn<Artikli, Date> createdColumn;

    public TableColumn<Artikli, Integer> actionColumn;

    public void initialize(){
        idArtikli.setCellValueFactory(new PropertyValueFactory<Artikli, String>("id"));
        artikliColumn.setCellValueFactory(new PropertyValueFactory<Artikli,String>("artikal"));
        createdColumn.setCellValueFactory(new PropertyValueFactory<Artikli,Date>("created"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<Artikli,Integer>("id"));


        //actionColumn.setCellFactory(new DoubleButtonCellFactory<>());
        actionColumn.setCellFactory(new DoubleButtonCellFactory(editEvent -> {
            int quoteId = Integer.parseInt(((Button)editEvent.getSource()).getUserData().toString());
          //  editArtikliScene(idArtikli);
        }, (deleteEvent -> {
            int quoteId = Integer.parseInt(((Button)deleteEvent.getSource()).getUserData().toString());
           // deleteArtikli(idArtikli);
        })));
    }

}
