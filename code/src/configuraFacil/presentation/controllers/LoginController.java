package configuraFacil.presentation.controllers;

import configuraFacil.business.ConfiguraFacil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    private ConfiguraFacil cf;

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
        tfNome.setText("");
        pfPass.setText("");
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
            URL url = getClass().getResource("../views/administrador.fxml");
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            SceneManager sm = new SceneManager(url, window);
            sm.newScene(1, cf);
        }else if(cf.login(username, password, 0) == 2){
            lbAviso.setText("Administrador inexistente.");
        }else
            lbAviso.setText("Password inválida");
    }

    private void loginFabricante(String username, String password, ActionEvent actionEvent) throws IOException {
        if(username.equals("") || password.equals("")){
            lbAviso.setText("Todos os campos devem ser preenchidos!!");

        }else if(cf.login(username, password, 1) == 1){

            URL url = getClass().getResource("../views/fabricante.fxml");
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            SceneManager sm = new SceneManager(url, window);
            sm.newScene(2, cf);
        }else if(cf.login(username, password, 1) == 2){
            lbAviso.setText("Fabricante inexistente.");
        }else
            lbAviso.setText("Password inválida");
    }

    private void loginVendedor(String username, String password, ActionEvent actionEvent) throws IOException {
        if(username.equals("") || password.equals("")){
            lbAviso.setText("Todos os campos devem ser preenchidos!!");

        }else if(cf.login(username, password, 2) == 1){

            URL url = getClass().getResource("../views/vendedor.fxml");
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            SceneManager sm = new SceneManager(url, window);
            sm.newScene(3, cf);
        }else if(cf.login(username, password, 2) == 2){
            lbAviso.setText("Vendedor inexistente.");
        }else
            lbAviso.setText("Password inválida");

    }

    public void handleBtnAutenticarLogin(ActionEvent actionEvent) throws IOException {

        String username = tfNome.getText();
        String password = pfPass.getText();

        if(apAdminLogin.isVisible()){

            loginAdmin(username, password, actionEvent);

        }else if(apFabrLogin.isVisible()){

            loginFabricante(username, password, actionEvent);

        }else if(apVendLogin.isVisible()){

            loginVendedor(username, password, actionEvent);

        }else{
            pfPass.setText("Ola mundo");
        }
    }
}
