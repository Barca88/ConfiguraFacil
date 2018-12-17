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

public class FabricanteController  {


    ConfiguraFacil cf;

    public void init(ConfiguraFacil cfo) {
        cf = cfo;
    }

    public void handleBtnSair(ActionEvent actionEvent) throws IOException {
        //new presentation
        URL url = getClass().getResource("../views/login.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Parent configValScene = (Parent) loader.load();

        //new scene
        Scene scene = new Scene(configValScene , 300, 275);
        scene.setFill(Color.TRANSPARENT);

        //load window with new scene
        Stage primaryWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        primaryWindow.setScene(scene);
        primaryWindow.show();
    }

    public void handleBtnConfiguracoes(ActionEvent actionEvent) throws IOException {
        //new presentation
        URL url = getClass().getResource("../views/configuracoesFabricantes.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Parent configValScene = (Parent) loader.load();

        //init with model fabricante controller
        ConfiguracoesFabricanteController cvc = loader.getController();
        cvc.init(cf);

        //new scene
        Scene scene = new Scene(configValScene , 300, 275);
        scene.setFill(Color.TRANSPARENT);

        //load window with new scene
        Stage primaryWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        primaryWindow.setScene(scene);
        primaryWindow.show();
    }

    public void handleBtnStock(ActionEvent actionEvent) throws IOException {
        //new presentation
        URL url = getClass().getResource("../views/stockFabrica.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Parent configValScene = (Parent) loader.load();

        //init with model fabricante controller
        StockFabricaController sfc = loader.getController();
        sfc.init(cf);

        //new scene
        Scene scene = new Scene(configValScene , 300, 275);
        scene.setFill(Color.TRANSPARENT);

        //load window with new scene
        Stage primaryWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        primaryWindow.setScene(scene);
        primaryWindow.show();
    }
}
