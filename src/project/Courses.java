package project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Courses extends Application {

    @Override
    public void start(Stage stage) {
        Image arrowImage = new Image("project\\images\\right_arrow.png");
        ImageView arrowView = new ImageView(arrowImage);
        arrowView.setFitWidth(50);
        arrowView.setFitHeight(50);
        Image logoImage = new Image("project\\images\\art.jpg");
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitWidth(200);
        logoView.setFitHeight(100);
        Image schoolImage = new Image("project\\images\\school.png");
        ImageView schoolView = new ImageView(schoolImage);
        schoolView.setFitWidth(50);
        schoolView.setFitHeight(50);

        // Back Button
        Button backBtn = new Button();
        backBtn.setGraphic(arrowView);

        BorderPane logoPane = new BorderPane();
        logoPane.setStyle("-fx-background-color: lightskyblue;");
        logoPane.setLeft(backBtn);
        logoPane.setCenter(logoView);
        logoPane.setRight(schoolView);
        BorderPane.setMargin(backBtn, new Insets(20, 0, 30, 50));
        BorderPane.setMargin(schoolView, new Insets(20, 50, 30, 0));

        // Images for grades
        Image image = new Image("project\\images\\grade.png");
        ImageView jss1View = new ImageView(image);
        jss1View.setFitWidth(50);
        jss1View.setFitHeight(50);
        Image image1 = new Image("project\\images\\grade.png");
        ImageView jss2View = new ImageView(image1);
        jss2View.setFitWidth(50);
        jss2View.setFitHeight(50);
        Image image2 = new Image("project\\images\\grade.png");
        ImageView jss3View = new ImageView(image2);
        jss3View.setFitWidth(50);
        jss3View.setFitHeight(50);
        Image image3 = new Image("project\\images\\grade.png");
        ImageView ss1View = new ImageView(image3);
        ss1View.setFitWidth(50);
        ss1View.setFitHeight(50);
        Image image4 = new Image("project\\images\\grade.png");
        ImageView ss2View = new ImageView(image4);
        ss2View.setFitWidth(50);
        ss2View.setFitHeight(50);
        Image image5 = new Image("project\\images\\grade.png");
        ImageView ss3View = new ImageView(image5);
        ss3View.setFitWidth(50);
        ss3View.setFitHeight(50);

        // Courses per Grade
        Text jssText = new Text("Junior Senior Secondary");
        jssText.setUnderline(true);
        jssText.setStyle("-fx-font-family: bold 15px;");

        Button jss1Btn = new Button("\nJss1", jss1View);
        jss1Btn.setContentDisplay(ContentDisplay.TOP);
        jss1Btn.setPadding(new Insets(50, 60, 50, 60));
        jss1Btn.setStyle("-fx-background-color: #c21460;");
        jss1Btn.setTextFill(Color.WHITE);

        Button jss2Btn = new Button("\nJss2", jss2View);
        jss2Btn.setContentDisplay(ContentDisplay.TOP);
        jss2Btn.setPadding(new Insets(50, 60, 50, 60));
        jss2Btn.setStyle("-fx-background-color: #4424d6;");
        jss2Btn.setTextFill(Color.WHITE);

        Button jss3Btn = new Button("\nJss3", jss3View);
        jss3Btn.setContentDisplay(ContentDisplay.TOP);
        jss3Btn.setPadding(new Insets(50, 60, 50, 60));
        jss3Btn.setStyle("-fx-background-color: #347c98;");
        jss3Btn.setTextFill(Color.WHITE);

        Text ssText = new Text("Senior Secondary School");
        ssText.setUnderline(true);
        Button ss1Btn = new Button("\nSS1", ss1View);
        ss1Btn.setContentDisplay(ContentDisplay.TOP);
        ss1Btn.setPadding(new Insets(50, 60, 50, 60));
        ss1Btn.setStyle("-fx-background-color: #b2d732;");
        ss1Btn.setTextFill(Color.WHITE);

        Button ss2Btn = new Button("\nSS2", ss2View);
        ss2Btn.setContentDisplay(ContentDisplay.TOP);
        ss2Btn.setPadding(new Insets(50, 60, 50, 60));
        ss2Btn.setStyle("-fx-background-color: #fccc1a;");
        ss2Btn.setTextFill(Color.WHITE);

        Button ss3Btn = new Button("\nSS3", ss3View);
        ss3Btn.setContentDisplay(ContentDisplay.TOP);
        ss3Btn.setPadding(new Insets(50, 60, 50, 60));
        ss3Btn.setStyle("-fx-background-color: #fc600a;");
        ss3Btn.setTextFill(Color.WHITE);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(50, 0, 0, 300));
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.addColumn(1, jssText);
        gridPane.addRow(1, jss1Btn, jss2Btn, jss3Btn);
        gridPane.addColumn(1, ssText);
        gridPane.addRow(3, ss1Btn, ss2Btn, ss3Btn);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(logoPane);
        borderPane.setCenter(gridPane);


        // Validation
        backBtn.setOnAction(ActionEvent -> {
            Stage homePage = new Stage();
            try {
                new HomePage().start(homePage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            stage.close();
        });

        jss1Btn.setOnAction(actionEvent -> {
            Stage jss1Page = new Stage();
            new JssCourses().start(jss1Page);
            stage.close();
        });
        jss2Btn.setOnAction(actionEvent -> {
            Stage jssPage = new Stage();
            new JssCourses().start(jssPage);
            stage.close();
        });
        jss3Btn.setOnAction(actionEvent -> {
            Stage jssPage = new Stage();
            new JssCourses().start(jssPage);
            stage.close();
        });
        ss1Btn.setOnAction(actionEvent -> {
            Stage ss1Page = new Stage();
            new SsCourses().start(ss1Page);
            stage.close();
        });
        ss2Btn.setOnAction(actionEvent -> {
            Stage ssPage = new Stage();
            new SsCourses().start(ssPage);
            stage.close();
        });
        ss3Btn.setOnAction(actionEvent -> {
            Stage ssPage = new Stage();
            new SsCourses().start(ssPage);
            stage.close();
        });


        stage.setTitle("Student Management");
        stage.setWidth(1230);
        stage.setResizable(false);
        stage.setHeight(720);

        VBox root = new VBox();

        root.getChildren().addAll(borderPane);
        root.setStyle("-fx-font: italic 15px 'TIMES NEW ROMAN'; -fx-background-color: BEIGE;");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
