/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package LiteSnacks;

import LiteSnacks.UI.Products;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application{

    Products productScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Vending machine");

        //********
        //LoginScene loginScene = new LoginScene(1200,600,primaryStage);

        this.productScene = new Products(640,480,primaryStage);
        //CashPayment scene = new CashPayment(640,480,primaryStage);
        productScene.setScene();
        //loginScene.setScene();
        //********

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}