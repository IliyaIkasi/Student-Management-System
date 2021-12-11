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


public class RegisterForm extends Application {
    // Text
    Label label;
    Text name;
    Text adminID;
    Text phnNumber;
    Text email;
    Text password;
    Text confirmPassword;
    Button submitBtn;
    Hyperlink loginLink;

    // Input Field
    TextField nameTxtField;
    TextField adminIDTxtField;
    TextField phnNumberTxtField;
    TextField emailTxtField;
    PasswordField passwordTxtField;
    PasswordField confirmPasswordTxtField;
    Label emptyField;
    Separator sp;
    Label notification;

    PauseTransition delay;

    // SQL Connectors
    Connection conn;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    @Override
    public void start(Stage stage) {
        // Text Field
        label = new Label("Registration Form");
        name = new Text("Full Name:");
        adminID = new Text("Administrator ID:");
        phnNumber = new Text("Phone Number:");
        email = new Text("Email:");
        password = new Text("Password:");
        confirmPassword = new Text("Confirm Password:");
        Image submitImage = new Image("project\\images\\signup.png");
        ImageView submitView = new ImageView(submitImage);
        submitView.setFitWidth(70);
        submitView.setFitHeight(40);
        submitBtn = new Button();
        submitBtn.setGraphic(submitView);
        submitBtn.setFocusTraversable(false);
        loginLink = new Hyperlink();
        loginLink.setText("Already have an account?");
        loginLink.setFocusTraversable(false);


        // Input Field
        // FullName
        nameTxtField = new TextField();
        nameTxtField.setPrefSize(300, 30);
        nameTxtField.setPromptText("Full Name");
        nameTxtField.setFocusTraversable(false);

        // AdministratorID
        adminIDTxtField = new TextField();
        adminIDTxtField.setPrefSize(300, 30);
        adminIDTxtField.setPromptText("reg-art-2021-xxxx");
        adminIDTxtField.setFocusTraversable(false);

        // Phone Number
        phnNumberTxtField = new TextField();
        phnNumberTxtField.setPrefSize(300, 30);
        phnNumberTxtField.setPromptText("+234xxxxxxxx");
        phnNumberTxtField.setFocusTraversable(false);

        // Email Address
        emailTxtField = new TextField();
        emailTxtField.setPrefSize(300, 30);
        emailTxtField.setPromptText("email@email.com");
        emailTxtField.setFocusTraversable(false);

        // Password
        passwordTxtField = new PasswordField();
        passwordTxtField.setPrefSize(300, 30);
        passwordTxtField.setPromptText("e.g abcd1234");
        passwordTxtField.setFocusTraversable(false);

        // Confirm Password
        confirmPasswordTxtField = new PasswordField();
        confirmPasswordTxtField.setPrefSize(300, 30);
        confirmPasswordTxtField.setPromptText("Confirm Password");
        confirmPasswordTxtField.setFocusTraversable(false);

        // Error Message
        emptyField = new Label();
        emptyField.setPrefSize(400, 30);
        emptyField.setTextFill(Color.TOMATO);
        emptyField.setStyle("-fx-font: bold 13px 'TIMES NEW ROMAN';");

        // Line Separator
        sp = new Separator();

        // Notification Message
        notification = new Label();
        notification.setPrefSize(200, 30);
        notification.setTextFill(Color.GREEN);


        // Image and sizing
        Image image = new Image("project\\images\\art.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(300);
        imageView.setFitHeight(523);


        Image logoImage = new Image("project\\images\\logo.png");
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitWidth(50);
        logoView.setFitHeight(50);
        Image schoolImage = new Image("project\\images\\school.png");
        ImageView schoolView = new ImageView(schoolImage);
        schoolView.setFitWidth(50);
        schoolView.setFitHeight(50);

        BorderPane logoPane = new BorderPane();
        logoPane.setStyle("-fx-background-color: LIGHTSKYBLUE;");
        logoPane.setPrefHeight(100);
        logoPane.setPadding(new Insets(20));
        label.setStyle("-fx-font: bold 15px 'sans-serif';");
        label.setUnderline(true);

        logoPane.setLeft(logoView);
        logoPane.setCenter(label);
        logoPane.setRight(schoolView);



        // GridPane
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(0, 0, 0, 50));
        gridPane.setVgap(15);
        gridPane.setHgap(15);

        // Adding Label, Texts, TextFields and buttons to the grid
        gridPane.add(name, 0, 1);
        gridPane.add(nameTxtField, 1, 1);

        gridPane.add(adminID, 0, 2);
        gridPane.add(adminIDTxtField, 1, 2);

        gridPane.add(phnNumber, 0, 3);
        gridPane.add(phnNumberTxtField, 1, 3);

        gridPane.add(email, 0, 4);
        gridPane.add(emailTxtField, 1, 4);

        gridPane.add(password, 0, 5);
        gridPane.add(passwordTxtField, 1, 5);

        gridPane.add(confirmPassword, 0, 6);
        gridPane.add(confirmPasswordTxtField, 1, 6);

        gridPane.add(emptyField, 1, 7);
        gridPane.add(sp, 0, 8, 3, 1);
        gridPane.add(submitBtn, 0, 9);
        gridPane.add(loginLink, 1, 9);
        gridPane.add(notification, 2, 9);

        // Adding GridPane and LogoPane to SignupPane
        BorderPane signupPane = new BorderPane();
        signupPane.setTop(logoPane);
        signupPane.setCenter(gridPane);


        // Adding SignupPane and Image to the BorderPane
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(signupPane);
        borderPane.setLeft(imageView);


        delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(actionEvent -> {
            Stage loginPage = new Stage();
            try {
                new Login().start(loginPage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            stage.close();
        });


        submitBtn.setOnAction(actionEvent -> {
            boolean nameRegex = Pattern.matches("^([A-Za-z]+( )[A-Za-z]\\w{1,20})$", nameTxtField.getText());
            boolean idRegex = Pattern.matches("^reg-art-2021-[0-9]{4}$", adminIDTxtField.getText());
            boolean phnNumberRegex = Pattern.matches("^([+234])[0-9]{13}$", phnNumberTxtField.getText());
            boolean emailRegex =
                    Pattern.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$",
                            emailTxtField.getText());
            boolean passwordRegex = Pattern.matches ("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", passwordTxtField.getText());


            if (nameTxtField.getText().isEmpty() || adminIDTxtField.getText().isEmpty() || phnNumberTxtField.getText().isEmpty()) {
                emptyField.setText("Enter All Details");
                notification.setText("");
            } else if (!nameRegex) {
                emptyField.setText("Name Format Not Supported ::: FirstName and LastName");
                notification.setText("");
            } else if (!idRegex){
                emptyField.setText("ID Format Not Supported ::: reg-art-2021-xxxx");
                notification.setText("");
            } else if (!phnNumberRegex) {
                emptyField.setText("Phone Format Not Supported ::: +234xxxxxxxxxx");
                notification.setText("");
            } else if (!emailRegex){
                emptyField.setText("Email Format Not Supported ::: email@email.com");
                notification.setText("");
            } else if (!passwordRegex) {
                emptyField.setText("Password Format Not Supported \n8 characters combination of letters and numbers required");
                notification.setText("");
            } else if (!confirmPasswordTxtField.getText().equals(passwordTxtField.getText())) {
                emptyField.setText("Password Mismatch");
                notification.setText("");
            } else {
                emptyField.setText("");
                dbValidation();
            }
        });
        loginLink.setOnAction(actionEvent -> {
            Stage loginPage = new Stage();
            try {
                new Login().start(loginPage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            stage.close();
        });


        //Setting the Stage
        stage.setTitle("Student Management System");
        stage.setWidth(1000);
        stage.setHeight(560);
        stage.setResizable(false);

        VBox root = new VBox();

        root.setStyle("-fx-font: italic 15px 'TIMES NEW ROMAN'; -fx-background-color: BEIGE;");
        root.getChildren().addAll(borderPane);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public RegisterForm() {
        conn = JDBC.connectDB();
    }

    public void signUp() {
        try {
            String sql = "Insert into art_college.admin_details (FullName, AdminID, PhoneNumber, Email, Password) values (?, ?, ?, ?, ?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, nameTxtField.getText().toLowerCase(Locale.ROOT));
            preparedStatement.setString(2, adminIDTxtField.getText().toLowerCase(Locale.ROOT));
            preparedStatement.setString(3, phnNumberTxtField.getText().toLowerCase(Locale.ROOT));
            preparedStatement.setString(4, emailTxtField.getText().toLowerCase(Locale.ROOT));
            preparedStatement.setString(5, passwordTxtField.getText().toLowerCase(Locale.ROOT));
            preparedStatement.executeUpdate();
            clearText();
            delay.play();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void dbValidation() {
        //noinspection StringOperationCanBeSimplified
        String adminID = adminIDTxtField.getText().toString();
        String sql = "Select AdminID from art_college.admin_details where AdminID = ?";
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, adminID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "User Already Exists", ButtonType.OK);
                alert.setTitle("User Already Exists");
                alert.showAndWait();
                Stage loginPage = new Stage();
                new Login().start(loginPage);
            } else {
                notification.setText("Welcome!!! Redirecting");
                signUp();
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void clearText() {
        nameTxtField.clear();
        adminIDTxtField.clear();
        phnNumberTxtField.clear();
        emailTxtField.clear();
        passwordTxtField.clear();
        confirmPasswordTxtField.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
