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

import java.sql.*;
import java.util.Locale;
import java.util.regex.Pattern;


public class Login extends Application {
    Connection conn;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    Label label;
    Text username;
    TextField adminIDTxtField;
    Text Password;
    PasswordField passwordTxtField;
    Label errorLabel;
    Label notification;
    Separator sp;
    Button loginBtn;
    Hyperlink loginLink;


    PauseTransition delay;


    Stage homePage = new Stage();
    @Override
    public void start(Stage stage) throws Exception{
        // Back Button
        Image arrowImage = new Image("project\\images\\logo.png");
        ImageView logoView = new ImageView(arrowImage);
        logoView.setFitWidth(50);
        logoView.setFitHeight(50);
        Image schoolImage = new Image("project\\images\\school.png");
        ImageView schoolView = new ImageView(schoolImage);
        schoolView.setFitWidth(50);
        schoolView.setFitHeight(50);

        Image loginImage = new Image("project\\images\\login.png");
        ImageView loginView = new ImageView(loginImage);
        loginView.setFitWidth(70);
        loginView.setFitHeight(40);

        Image submitImage = new Image("project\\images\\signup.png");
        ImageView submitView = new ImageView(submitImage);
        submitView.setFitWidth(70);
        submitView.setFitHeight(40);


        // Label and Text
        label = new Label("Login Form");
        username = new Text("AdminID :");
        Password = new Text("Password:");
        // UserId TextField
        adminIDTxtField = new TextField();
        adminIDTxtField.setPrefSize(200, 30);
        adminIDTxtField.setPromptText("reg-art-2021-xxxx");
        adminIDTxtField.setFocusTraversable(false);

        // Password TextField
        passwordTxtField = new PasswordField();
        passwordTxtField.setPrefSize(200, 30);
        passwordTxtField.setPromptText("e.g abcd1234");
        passwordTxtField.setFocusTraversable(false);
        // Error Label, Separator and Notification Label
        errorLabel = new Label();
        errorLabel.setPrefWidth(400);
        errorLabel.setTextFill(Color.TOMATO);

        sp = new Separator();

        notification = new Label();
        notification.setPrefWidth(200);
        notification.setTextFill(Color.GREEN);
        sp = new Separator();
        // Login and Signup Button
        loginBtn = new Button();
        loginBtn.setGraphic(loginView);
        loginBtn.setFocusTraversable(false);
        loginLink = new Hyperlink();
        loginLink.setText("Not Registered? Signup");
        loginLink.setFocusTraversable(false);


        BorderPane logoPane = new BorderPane();
        logoPane.setPrefHeight(100);
        logoPane.setPadding(new Insets(20));
        logoPane.setStyle("-fx-background-color: LIGHTSKYBLUE;");
        label.setStyle("-fx-font-family: bold italic 15px ;");
        label.setUnderline(true);

        logoPane.setLeft(logoView);
        logoPane.setCenter(label);
        logoPane.setRight(schoolView);


        // GridPane and Styling
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(50, 10, 10, 90));
        gridPane.setVgap(15);
        gridPane.setHgap(15);


        gridPane.add(username, 0, 2);
        gridPane.add(adminIDTxtField, 1, 2);
        gridPane.add(Password, 0, 3);
        gridPane.add(passwordTxtField, 1, 3);
        gridPane.add(errorLabel, 1, 4);
        gridPane.add(sp, 0, 5, 3, 1);
        gridPane.add(loginBtn, 0, 8);
        gridPane.add(loginLink, 1, 8);
        gridPane.add(notification, 2, 8);


        BorderPane loginPane = new BorderPane();
        loginPane.setTop(logoPane);
        loginPane.setCenter(gridPane);


        delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(actionEvent -> {
            try {
                new HomePage().start(homePage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            stage.close();
        });


                    // Validation
        //Login Button
        loginBtn.setOnAction(e -> {
            boolean idRegex = Pattern.matches("^reg-art-2021-[0-9]{4}$", adminIDTxtField.getText());
            boolean passwordRegex = Pattern.matches ("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", passwordTxtField.getText());
            try {
                if (username.getText().isEmpty() || Password.getText().isEmpty()) {
                    errorLabel.setText("Please Complete All Requirements");
                    notification.setText("");
                } else if (!idRegex) {
                    errorLabel.setText("ID Format Not Supported ::: reg-art-2021-xxxx");
                    notification.setText("");
                } else if (!passwordRegex) {
                    errorLabel.setText("Password Format Not Supported \n8 characters combination of letters and numbers required");
                    notification.setText("");
                } else {
                    errorLabel.setText("");
                    login();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
                System.out.println("Login Button Error...");
            }
        });
        loginLink.setOnAction(e -> {
            Stage RGForm = new Stage();
            new RegisterForm().start(RGForm);
            stage.close();
        });


        Image image = new Image("project\\images\\art.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(300);
        imageView.setFitHeight(500);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(loginPane);
        borderPane.setLeft(imageView);

        stage.setTitle("Student Management");
        stage.setWidth(1000);
        stage.setHeight(500);
        stage.setResizable(false);

        VBox root = new VBox();
        root.setStyle("-fx-font: italic 15px 'TIMES NEW ROMAN'; -fx-background-color: BEIGE;");
        root.getChildren().addAll(borderPane);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public Login() {
        conn = JDBC.connectDB();
    }

    private void login() {
        //noinspection StringOperationCanBeSimplified
        String userId = adminIDTxtField.getText().toString();
        //noinspection StringOperationCanBeSimplified
        String userPassword = passwordTxtField.getText().toString();

        //Query
        try {
            String sql = "select adminID and Password from art_college.admin_details where adminID = ? and Password = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, userId.toLowerCase(Locale.ROOT));
            preparedStatement.setString(2, userPassword.toLowerCase(Locale.ROOT));
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                errorLabel.setText("Invalid Username or Password");
            } else {
                notification.setText("Login Successful...Redirecting");
                delay.play();
            }
            clearText();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    private void clearText() {
        adminIDTxtField.clear();
        passwordTxtField.clear();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
