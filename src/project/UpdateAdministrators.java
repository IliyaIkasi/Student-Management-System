package project;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.regex.Pattern;

public class UpdateAdministrators extends Application {
    // Teacher ID
    Text fullNameText;
    TextField fullNameTextField;

    // FullName
    Text adminIDText;
    TextField adminIDTextField;

    // Class Duration
    Text phoneNumberText;
    TextField phnNumTextField;

    // Class Duration
    Text emailText;
    TextField emailTextField;

    // Class Duration
    Text passwordText;
    TextField passwordTextField;

    // Reset Button
    Button searchBtn;

    // Registration Button
    Button updateBtn;

    // Error Label
    Label errorLabel;
    // Notification Label
    Label notification;

    // Delay
    PauseTransition delay;


    Stage dashboard = new Stage();

    Connection conn;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    @Override
    public void start(Stage stage) {
        // Logo Images and back Button
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

        //BackButton
        Button backBtn = new Button();
        backBtn.setGraphic(arrowView);

        // Setting BorderPane for LogoImages
        BorderPane logoPane = new BorderPane();
        logoPane.setStyle("-fx-background-color: lightskyblue;");
        logoPane.setLeft(backBtn);
        logoPane.setCenter(logoView);
        logoPane.setRight(schoolView);
        BorderPane.setMargin(backBtn, new Insets(20, 0, 30, 50));
        BorderPane.setMargin(schoolView, new Insets(20, 50, 30, 0));


        // Teacher ID
        fullNameText = new Text("Full Name:");
        fullNameTextField = new TextField();
        fullNameTextField.setPromptText("e.g FirstName LastName");
        fullNameTextField.setMinWidth(200);

        // Full Name
        adminIDText = new Text("AdminID:");
        adminIDTextField = new TextField();
        adminIDTextField.setPromptText("e.g reg-art-2021-xxxx");
        adminIDTextField.setMinWidth(200);

        // Class Duration
        phoneNumberText = new Text("Duration:");
        phnNumTextField = new TextField();
        phnNumTextField.setPromptText("e.g +234xxxxxxxxx");
        phnNumTextField.setMinWidth(200);

        emailText = new Text("Email");
        emailTextField = new TextField();
        emailTextField.setPromptText("e.g email@email.com");
        emailTextField.setMinWidth(200);

        passwordText = new Text("Password");
        passwordTextField = new TextField("");
        passwordTextField.setPromptText("e.g abcd1234");
        passwordTextField.setMinWidth(200);


        // Reset Button
        searchBtn = new Button("Search");
        searchBtn.setPadding(new Insets(10, 30, 10, 30));

        // Registration Button
        updateBtn = new Button("Update");
        updateBtn.setPadding(new Insets(10, 30, 10, 30));

        //Error Label
        errorLabel = new Label();
        errorLabel.setPrefWidth(300);
        errorLabel.setTextFill(Color.TOMATO);
        errorLabel.setStyle("-fx-font: bold 13px 'TIMES NEW ROMAN';");
        errorLabel.setPadding(new Insets(0, 0, 0, 50));

        //Notification Label
        notification = new Label();
        notification.setPrefWidth(300);
        notification.setTextFill(Color.GREEN);
        notification.setStyle("-fx-font: bold 13px 'TIMES NEW ROMAN';");
        notification.setPadding(new Insets(0, 0, 0, 50));


        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(20);
        gridPane.setPadding(new Insets(50, 0, 25, 200));
        gridPane.addColumn(0, adminIDText,fullNameText);
        gridPane.addColumn(1, adminIDTextField, fullNameTextField);
        gridPane.addColumn(7, phoneNumberText, emailText);
        gridPane.addColumn(8, phnNumTextField, emailTextField);


        GridPane displayPane = new GridPane();
        displayPane.setPadding(new Insets(25, 0, 0, 300));
        displayPane.setHgap(20);
        displayPane.setVgap(20);
        displayPane.add(errorLabel, 3, 0);
        displayPane.add(notification, 3, 1);
        displayPane.add(searchBtn, 1, 2);
        displayPane.add(updateBtn, 4, 2);

        delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(actionEvent -> {
            try {
                new ShowAdministrators().start(dashboard);
            } catch (Exception e) {
                e.printStackTrace();
            }
            stage.close();
        });


        // Validation
        backBtn.setOnAction(actionEvent -> {
            try {
                new ShowAdministrators().start(dashboard);
            } catch (Exception e) {
                e.printStackTrace();
            }
            stage.close();
        });
        updateBtn.setOnAction(actionEvent -> {
            boolean idRegex = Pattern.matches("^reg-art-2021-[0-9]{4}$", adminIDTextField.getText());
            if (fullNameTextField.getText().isEmpty() || adminIDTextField.getText().isEmpty() || phnNumTextField.getText().isEmpty()
                    || emailTextField.getText().isEmpty()) {
                errorLabel.setText("Enter All Details");
                notification.setText("");
            } else if (!idRegex) {
                errorLabel.setText("Admission Number Format Not Supported \n::: art-ts-2021-xxxx");
                notification.setText("");
            }else {
                errorLabel.setText("");
                dbValidation();
            }
        });
        searchBtn.setOnAction(actionEvent -> {
            if (adminIDTextField.getText().isEmpty()) {
                errorLabel.setText("Enter ID Details");
                notification.setText("");
            } else {
                searchTeachers();
            }
        });


        stage.setTitle("Students Management System");
        stage.setWidth(1230);
        stage.setResizable(false);
        stage.setHeight(720);

        VBox root = new VBox();
        root.setStyle("-fx-font: italic 15px 'TIMES NEW ROMAN'; -fx-background-color: BEIGE;");
        root.getChildren().addAll(logoPane, gridPane, displayPane);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void searchTeachers() {
        try {
            String sql = "Select FullName, AdminID, PhoneNumber, Email from art_college.admin_details where AdminID = ? ";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, adminIDTextField.getText().toLowerCase(Locale.ROOT));
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                notification.setText("Welcome!!! Redirecting");
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String phnNumber = resultSet.getString(3);
                String email = resultSet.getString(4);
//                String password = resultSet.getString(5);

                fullNameTextField.setText(id);                adminIDTextField.setText(name);
                phnNumTextField.setText(phnNumber);           emailTextField.setText(email);
//                passwordTextField.setText(password);
                errorLabel.setText("");             notification.setText("");
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "User Doesn't Exist", ButtonType.OK);
                alert.setTitle("User Doesn't Exist");
                alert.showAndWait();
                clearText();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void updateTeacher() {
        try {
            String sql = "Update art_college.admin_details set FullName = ?, AdminID = ?, " +
                    "PhoneNumber = ?, Email = ? where AdminID = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, fullNameTextField.getText().toLowerCase(Locale.ROOT));
            preparedStatement.setString(2, adminIDTextField.getText().toLowerCase(Locale.ROOT));
            preparedStatement.setString(3, phnNumTextField.getText().toLowerCase(Locale.ROOT));
            preparedStatement.setString(4, emailTextField.getText().toLowerCase(Locale.ROOT));
//            preparedStatement.setString(5, passwordTextField.getText().toLowerCase(Locale.ROOT));
            preparedStatement.setString(5, adminIDTextField.getText().toLowerCase(Locale.ROOT));
            preparedStatement.executeUpdate();
            clearText();
            notification.setText("Welcome!!! Redirecting");
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(actionEvent -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Updated!!! \nRedirecting", ButtonType.OK);
                alert.setTitle("Updated Database");
                alert.showAndWait();
//                this.delay.play();
            });

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dbValidation() {
        try {
            String sql = "select AdmissionNumber from art_college.students_details where AdmissionNumber = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, adminIDTextField.getText().toLowerCase(Locale.ROOT));
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "User Already Exists", ButtonType.OK);
                alert.setTitle("User Already Exists");
                alert.showAndWait();
                new DisplayStudents().start(dashboard);
            } else {
                updateTeacher();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public UpdateAdministrators() {
        conn = JDBC.connectDB();
    }

    public void clearText() {
        fullNameTextField.clear();          adminIDTextField.clear();
        phnNumTextField.clear();            emailTextField.clear();
        passwordTextField.clear();
        errorLabel.setText("");             notification.setText("");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
