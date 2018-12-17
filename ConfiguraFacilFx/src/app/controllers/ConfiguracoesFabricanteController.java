package app.controllers;

import Facade.ConfiguraFacil;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ConfiguracoesFabricanteController {

    ConfiguraFacil cf;

    public void init(ConfiguraFacil cfo) {
        cf = cfo;
    }

    public void handleBtnBack(ActionEvent actionEvent) throws IOException {
        //new presentation
        URL url = getClass().getResource("../views/fabricante.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Parent configValScene = (Parent) loader.load();

        //init with model fabricante controller
        FabricanteController fc = loader.getController();
        fc.init(cf);

        //new scene
        Scene scene = new Scene(configValScene , 300, 275);
        scene.setFill(Color.TRANSPARENT);

        //load window with new scene
        Stage primaryWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        primaryWindow.setScene(scene);
        primaryWindow.show();
    }
}
