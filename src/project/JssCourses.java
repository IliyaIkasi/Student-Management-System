package project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JssCourses extends Application {
    Stage coursesPage = new Stage();

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

        //Back Button
        Button backBtn = new Button();
        backBtn.setGraphic(arrowView);

        BorderPane logoPane = new BorderPane();
        logoPane.setStyle("-fx-background-color: LIGHTSKYBLUE;");
        logoPane.setLeft(backBtn);
        logoPane.setCenter(logoView);
        logoPane.setRight(schoolView);
        BorderPane.setMargin(backBtn, new Insets(20, 0, 30, 50));
        BorderPane.setMargin(schoolView, new Insets(20, 50, 30, 0));


        // Student Admission Number
        Text idText = new Text("Admission Number:");
        TextField idTextField = new TextField();
        idTextField.setPromptText("Admission Number");
        idTextField.setPrefWidth(200);


        GridPane textPane = new GridPane();
        textPane.setPadding(new Insets(20, 0, 0, 300));
        textPane.setVgap(10);
        textPane.setHgap(10);
        textPane.addRow(0, idText, idTextField);


        // Courses and GridPane
        CheckBox course1 = new CheckBox("Agricultural Science");
        CheckBox course2 = new CheckBox("Basic Science");
        CheckBox course3 = new CheckBox("Basic Technology");
        CheckBox course4 = new CheckBox("Business Studies");
        CheckBox course5 = new CheckBox("Christian Religious Studies");
        CheckBox course6 = new CheckBox("Civic Education");
        CheckBox course7 = new CheckBox("Computer Studies");
        CheckBox course8 = new CheckBox("English Language");
        CheckBox course9 = new CheckBox("Fine Arts/ Creative Arts");
        CheckBox course10 = new CheckBox("French");
        CheckBox course11 = new CheckBox("Hausa");
        CheckBox course12 = new CheckBox("Home Economics");
        CheckBox course13 = new CheckBox("Mathematics");
        CheckBox course14 = new CheckBox("Music");
        CheckBox course15 = new CheckBox("Physical and Health Education");
        CheckBox course16 = new CheckBox("Social Studies");


        GridPane coursePane = new GridPane();
        coursePane.setPadding(new Insets(30, 0, 0, 100));
        coursePane.setVgap(15);
        coursePane.setHgap(20);
        coursePane.addColumn(0, course1, course2, course3, course4);
        coursePane.addColumn(1, course5, course6, course7, course8);
        coursePane.addColumn(2, course9, course10, course11, course12);
        coursePane.addColumn(3, course13, course14, course15, course16);


        Image submitImage = new Image("project\\images\\submit.png");
        ImageView submitView = new ImageView(submitImage);
        submitView.setFitWidth(70);
        submitView.setFitHeight(40);
        Button submitBtn = new Button();
        submitBtn.setGraphic(submitView);

        GridPane btnPane = new GridPane();
        btnPane.setPadding(new Insets(50, 0, 0, 400));
        btnPane.getChildren().addAll(submitBtn);


        BorderPane borderPane = new BorderPane();
        borderPane.setTop(textPane);
        borderPane.setCenter(coursePane);
        borderPane.setBottom(btnPane);


        // Validation
        backBtn.setOnAction(actionEvent -> {
            new Courses().start(coursesPage);
            stage.close();
        });
        submitBtn.setOnAction(actionEvent -> {
            new Courses().start(coursesPage);
            stage.close();
        });

        stage.setTitle("Student Management System");
        stage.setWidth(1000);
        stage.setHeight(500);

        VBox root = new VBox();

        root.getChildren().addAll(logoPane, borderPane);
        root.setStyle("-fx-font: italic 15px 'TIMES NEW ROMAN'; -fx-background-color: BEIGE;");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
