package configuraFacil.presentation.controllers;


import configuraFacil.business.ConfiguraFacil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class SceneManager{

    private FXMLLoader loader;
    protected Scene scene;
    private Stage window;

    public SceneManager(URL url,Stage window) {
        this.loader = new FXMLLoader(url);
        this.window = window;
    }

    public void newScene(int controller, ConfiguraFacil cf) throws IOException {
        Parent root = (Parent) loader.load();

        switch(controller){
            case 1 : 
                AdministradorController ac = loader.getController();
                ac.init(cf);
                break;
            case 2 : 
                FabricanteController fc = loader.getController();
                fc.init(cf);
                break;
            case 3 :
                VendedorController vc = loader.getController();
                vc.init(cf);
                break;
            case 4 : 
                ConfiguracoesController cac = loader.getController();
                cac.init(cf);
                break;
            case 5 :
                ClienteFormController fcc = loader.getController();
                fcc.init(cf);
                break;
            case 6 :
                ConfiguracaoController mc = loader.getController();
                mc.init(cf);
                break;
            case 7 : 
                StockController sac = loader.getController();
                sac.init(cf);
                break;
            case 8 :
                ConfiguracaoOtimaController co = loader.getController();
                co.init(cf);
                break;
            case 9 :
                ConsultarConfiguracaoController ccc = loader.getController();
                ccc.init(cf);
                break;
            case 10 :
                FabricantesController fcs = loader.getController();
                fcs.init(cf);
                break;
            case 11 :
                VendedoresController vcs = loader.getController();
                vcs.init(cf);
                break;
            default :
                break;
        }

        //new scene
        scene = new Scene(root, 600, 400);
        scene.setFill(Color.TRANSPARENT);

        //load window with new scene
        window.setResizable(false);
        window.setScene(scene);
    }

}
