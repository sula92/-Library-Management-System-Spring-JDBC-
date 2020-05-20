package com.sula;

import com.sula.dao.MemberDAO;
import com.sula.dao.daoimpl.MemberDAOImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.sula.resources.AppConfig;

import java.io.IOException;
import java.net.URL;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AppInitializer extends Application {

    public static AnnotationConfigApplicationContext ctx;



    public static void main(String[] args) {
        launch(args);
/*
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();*/


    }



    @Override
    public void start(Stage primaryStage) throws IOException {

        try {

            ctx = new AnnotationConfigApplicationContext();
            ctx.register(AppConfig.class);
            ctx.refresh();

            // Let's setup the root logger



            Parent root = FXMLLoader.load(this.getClass().getResource("/com/sula/view/Main.fxml"));
            Scene mainScene = new Scene(root);
            primaryStage.setScene(mainScene);
            primaryStage.setTitle("Book");
            primaryStage.centerOnScreen();
            // primaryStage.setFullScreen(true);
            primaryStage.show();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact SULA").show();

        }

    }
}
