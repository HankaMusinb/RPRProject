package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ArtikliManager;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

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

    public TableView quotesTable;
    public TextField search;

}
