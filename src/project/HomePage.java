package project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;


public class HomePage extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // School Image and GridPane
        Image javaLogo = new Image("project\\images\\logo.png");
        ImageView javaLogoView = new ImageView(javaLogo);
        javaLogoView.setFitWidth(50);
        javaLogoView.setFitHeight(50);
        Image logoImage = new Image("project\\images\\art.jpg");
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitWidth(200);
        logoView.setFitHeight(100);
        Image schoolImage = new Image("project\\images\\school.png");
        ImageView schoolView = new ImageView(schoolImage);
        schoolView.setFitWidth(50);
        schoolView.setFitHeight(50);

        BorderPane logoPane = new BorderPane();
        logoPane.setStyle("-fx-background-color: lightskyblue;");
        logoPane.setLeft(javaLogoView);
        logoPane.setCenter(logoView);
        logoPane.setRight(schoolView);
        BorderPane.setMargin(javaLogoView, new Insets(20, 0, 30, 50));
        BorderPane.setMargin(schoolView, new Insets(20, 50, 30, 0));


        // Button Images
        Image image = new Image("project\\images\\home.png");
        ImageView dashView = new ImageView(image);
        dashView.setFitWidth(50);
        dashView.setFitHeight(50);

        Image image1 = new Image("project\\images\\student.png");
        ImageView studentsView = new ImageView(image1);
        studentsView.setFitWidth(50);
        studentsView.setFitHeight(50);

        Image image2 = new Image("project\\images\\classroom.png");
        ImageView courseView = new ImageView(image2);
        courseView.setFitWidth(50);
        courseView.setFitHeight(50);

        Image image3 = new Image("project\\images\\update.png");
        ImageView updateView = new ImageView(image3);
        updateView.setFitWidth(50);
        updateView.setFitHeight(50);

        Image image4 = new Image("project\\images\\settings.png");
        ImageView settingsView = new ImageView(image4);
        settingsView.setFitWidth(50);
        settingsView.setFitHeight(50);

        Image image5 = new Image("project\\images\\off.png");
        ImageView closeView = new ImageView(image5);
        closeView.setFitHeight(50);
        closeView.setFitWidth(50);


        // Buttons
        Button dashboardBtn = new Button("\nDashboard", dashView);
        dashboardBtn.setContentDisplay(ContentDisplay.TOP);
        dashboardBtn.setPadding(new Insets(50));
        dashboardBtn.setFocusTraversable(false);

        Button studentsBtn = new Button("\nStudents", studentsView);
        studentsBtn.setContentDisplay(ContentDisplay.TOP);
        studentsBtn.setPadding(new Insets(50));
        studentsBtn.setFocusTraversable(false);

        Button coursesBtn = new Button("\nCourses", courseView);
        coursesBtn.setContentDisplay(ContentDisplay.TOP);
        coursesBtn.setPadding(new Insets(50));
        coursesBtn.setFocusTraversable(false);

        Button updateBtn = new Button("\nUpdate", updateView);
        updateBtn.setContentDisplay(ContentDisplay.TOP);
        updateBtn.setPadding(new Insets(50, 63.5, 50, 63.5));
        updateBtn.setFocusTraversable(false);

        Button showAdminBtn = new Button("\nAdmin", settingsView);
        showAdminBtn.setContentDisplay(ContentDisplay.TOP);
        showAdminBtn.setPadding(new Insets(50, 58, 50, 58));
        showAdminBtn.setFocusTraversable(false);

        Button closeBtn = new Button("\nClose", closeView);
        closeBtn.setContentDisplay(ContentDisplay.TOP);
        closeBtn.setPadding(new Insets(50, 53.5, 50, 53.5));
        closeBtn.setFocusTraversable(false);


        // Adding Buttons to GridPane
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-font-family: bold;");
        gridPane.setPadding(new Insets(50, 0, 0, 300));
        gridPane.setHgap(20);
        gridPane.setVgap(20);

        gridPane.addRow(0, dashboardBtn, studentsBtn, coursesBtn);
        gridPane.addRow(1, updateBtn, showAdminBtn, closeBtn);



        // Adding Action Event Listeners
        dashboardBtn.setOnAction(actionEvent -> {
            Stage dashboardPage = new Stage();
            new ShowAdministrators().start(dashboardPage);
            stage.close();
        });
        studentsBtn.setOnAction(ActionEvent -> {
            Stage studentsPage = new Stage();
            new DisplayStudents().start(studentsPage);
            stage.close();
        });
        coursesBtn.setOnAction(actionEvent -> {
            Stage coursesPage = new Stage();
            new Courses().start(coursesPage);
            stage.close();
        });
        updateBtn.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No DeleteStudents Available", ButtonType.OK);
            alert.setTitle("DeleteStudents");
            alert.show();
        });
        // Settings Button
        showAdminBtn.setOnAction(actionEvent -> {
            Stage showAdminPage = new Stage();
            new ShowAdministrators().start(showAdminPage);
            stage.close();
        });
        closeBtn.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you wish to exit?");

            ButtonType yesBtn = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noBtn = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(yesBtn, noBtn);
            Optional result = alert.showAndWait();
            if (result.get() == yesBtn){
                stage.close();
            }
        });


        stage.setTitle("Student Management");
        stage.setWidth(1230);
        stage.setResizable(false);
        stage.setHeight(720);

        VBox root = new VBox();

        root.setStyle("-fx-font: italic 15px 'TIMES NEW ROMAN'; -fx-background-color: BEIGE;");
        root.getChildren().addAll(logoPane, gridPane);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
