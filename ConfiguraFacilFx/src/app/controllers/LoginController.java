package app.controllers;

import Facade.ConfiguraFacil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    ConfiguraFacil cf;

    @FXML
    private Button btnBack;

    @FXML
    private AnchorPane apLogin;

    @FXML
    private AnchorPane apVendLogin;

    @FXML
    private AnchorPane apFabrLogin;

    @FXML
    private AnchorPane apAdminLogin;

    @FXML
    private Label lbAviso;

    @FXML
    private PasswordField pfPass;

    @FXML
    private TextField tfNome;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnBack.setVisible(false);
        cf = new ConfiguraFacil();
    }

    @FXML
    public void handleBtnVendedorAction(ActionEvent event) {
        btnBack.setVisible(true);
        apAdminLogin.setVisible(false);
        apFabrLogin.setVisible(false);
        apVendLogin.setVisible(true);
        apLogin.setVisible(true);
    }

    public void handleBtnAdministradorAction(ActionEvent actionEvent) {
        btnBack.setVisible(true);
        apAdminLogin.setTranslateX(400);
        apAdminLogin.setVisible(true);
        apFabrLogin.setVisible(false);
        apVendLogin.setVisible(false);
        apLogin.setVisible(true);
    }

    public void handleBtnFabricanteAction(ActionEvent actionEvent) {
        btnBack.setVisible(true);
        apFabrLogin.setTranslateX(200);
        apAdminLogin.setVisible(false);
        apFabrLogin.setVisible(true);
        apVendLogin.setVisible(false);
        apLogin.setVisible(true);
    }

    public void handleBtnBackLogin(ActionEvent actionEvent) {
        lbAviso.setText("");
        btnBack.setVisible(false);
        apAdminLogin.setTranslateX(0);
        apAdminLogin.setVisible(true);

        apFabrLogin.setTranslateX(0);
        apFabrLogin.setVisible(true);

        apVendLogin.setVisible(true);
        apLogin.setVisible(false);
    }

    private void loginAdmin(String username, String password, ActionEvent actionEvent) throws IOException {
        if(username.equals("") || password.equals("")){
            lbAviso.setText("Todos os campos devem ser preenchidos!!");

        }else if(cf.login(username, password, 0) == 1){

            //new presentation
            URL url = getClass().getResource("../views/administrador.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            Parent fabrScene = (Parent) loader.load();

            //init with model fabricante controller
            AdministradorController ac = loader.getController();
            ac.init(cf);

            //new scene
            Scene scene = new Scene(fabrScene , 300, 275);
            scene.setFill(Color.TRANSPARENT);

            //load window with new scene
            Stage primaryWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            primaryWindow.setScene(scene);
        }else if(cf.login(username, password, 0) == 2){
            lbAviso.setText("Administrador inexistente.");
        }else
            lbAviso.setText("Password inválida");
    }

    private void loginFabricante(String username, String password, ActionEvent actionEvent) throws IOException {
        if(username.equals("") || password.equals("")){
            lbAviso.setText("Todos os campos devem ser preenchidos!!");

        }else if(cf.login(username, password, 1) == 1){

            //new presentation
            URL url = getClass().getResource("../views/fabricante.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            Parent fabrScene = (Parent) loader.load();

            //init with model fabricante controller
            FabricanteController fc = loader.getController();
            fc.init(cf);

            //new scene
            Scene scene = new Scene(fabrScene , 300, 275);
            scene.setFill(Color.TRANSPARENT);

            //load window with new scene
            Stage primaryWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            primaryWindow.setScene(scene);
        }else if(cf.login(username, password, 1) == 2){
            lbAviso.setText("Fabricante inexistente.");
        }else
            lbAviso.setText("Password inválida");
    }

    private void loginVendedor(String username, String password, ActionEvent actionEvent) throws IOException {
        if(username.equals("") || password.equals("")){
            lbAviso.setText("Todos os campos devem ser preenchidos!!");

        }else if(cf.login(username, password, 2) == 1){

            //new presentation
            URL url = getClass().getResource("../views/vendedor.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            Parent fabrScene = (Parent) loader.load();

            //init with model fabricante controller
            VendedorController vc = loader.getController();
            vc.init(cf);

            //new scene
            Scene scene = new Scene(fabrScene , 300, 275);
            scene.setFill(Color.TRANSPARENT);

            //load window with new scene
            Stage primaryWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            primaryWindow.setScene(scene);
        }else if(cf.login(username, password, 1) == 2){
            lbAviso.setText("Vendedor inexistente.");
        }else
            lbAviso.setText("Password inválida");

    }

    public void handleBtnAutenticarLogin(ActionEvent actionEvent) throws IOException {


        String username;
        String password;

        if(apAdminLogin.isVisible()){
            username =  tfNome.getText();
            password = pfPass.getText();

            loginAdmin(username, password, actionEvent);


        }else if(apFabrLogin.isVisible()){
            username = tfNome.getText();
            password = pfPass.getText();

            loginFabricante(username, password, actionEvent);

        }else if(apVendLogin.isVisible()){
            username =  tfNome.getText();
            password = pfPass.getText();

            loginVendedor(username, password, actionEvent);

        }else{
            pfPass.setText("Ola mundo");
        }
    }
}
