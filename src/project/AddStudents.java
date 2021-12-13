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

public class AddStudents extends Application {
    // Admission Number
    Text idText;
    TextField idTextField;
    // FullName
    Text nameText;
    TextField nameTextField;
    // Date of Birth
    Text dOBText;
    DatePicker dOBTextField;
    // Contact Number
    Text contactText;
    TextField contactTextField;
    // Home Address
    Text addressText;
    TextField addressTextField;
    // Gender
    Text genderText;
    MenuItem maleBtn;
    MenuItem femaleBtn;
    MenuButton genderMenu;
    // Grade
    Text gradeText;
    MenuItem grade1;
    MenuItem grade2;
    MenuItem grade3;
    MenuItem grade4;
    MenuItem grade5;
    MenuItem grade6;
    MenuButton gradeMenu;
    // Reset Button
    Button resetBtn;
    // Registration Button
    Button registerBtn;

    // Error Label
    Label errorLabel;
    // Notification Label
    Label notification;

    // Delay
    PauseTransition delay;
    Alert alert;


    Stage displayPage = new Stage();

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


        // Admission Number
        idText = new Text("Admission Number:");
        idTextField = new TextField();
        idTextField.setPromptText("Admission Number");
        idTextField.setMinWidth(200);

        // Full Name
        nameText = new Text("Full Name:");
        nameTextField = new TextField();
        nameTextField.setPromptText("Full Name");
        nameTextField.setMinWidth(200);

        // Date Of Birth
        dOBText = new Text("Date of Birth:");
        dOBTextField = new DatePicker();
        dOBTextField.setPromptText("YY-MM-DD");
        dOBTextField.setMinWidth(200);

        //Contact Number
        contactText = new Text("Contact Number:");
        contactTextField = new TextField();
        contactTextField.setPromptText("Contact Number");
        contactTextField.setMinWidth(200);

        // Address
        addressText = new Text("Address:");
        addressTextField = new TextField();
        addressTextField.setPromptText("Address");
        addressTextField.setMinWidth(200);

        //Gender
        genderText = new Text("Gender:");
        maleBtn = new MenuItem("Male");
        femaleBtn = new MenuItem("Female");
        genderMenu = new MenuButton("Select Gender", null, maleBtn, femaleBtn);
        maleBtn.setOnAction(e -> genderMenu.setText(maleBtn.getText()));
        femaleBtn.setOnAction(e -> genderMenu.setText(femaleBtn.getText()));

        //Grade
        gradeText = new Text("Grade:");
        grade1 = new MenuItem("Jss1");
        grade2 = new MenuItem("Jss2");
        grade3 = new MenuItem("Jss3");
        grade4 = new MenuItem("SS1");
        grade5 = new MenuItem("SS2");
        grade6 = new MenuItem("SS3");
        gradeMenu = new MenuButton("Select Grade", null, grade1, grade2, grade3, grade4, grade5, grade6);
        grade1.setOnAction(e -> gradeMenu.setText(grade1.getText()));
        grade2.setOnAction(e -> gradeMenu.setText(grade2.getText()));
        grade3.setOnAction(e -> gradeMenu.setText(grade3.getText()));
        grade4.setOnAction(e -> gradeMenu.setText(grade4.getText()));
        grade5.setOnAction(e -> gradeMenu.setText(grade5.getText()));
        grade6.setOnAction(e -> gradeMenu.setText(grade6.getText()));


        // Reset Button
        resetBtn = new Button("Reset");
        resetBtn.setPadding(new Insets(10, 30, 10, 30));

        // Registration Button
        registerBtn = new Button("Register");
        registerBtn.setPadding(new Insets(10, 30, 10, 30));

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
        gridPane.addColumn(0, idText, nameText, dOBText, genderText);
        gridPane.addColumn(1, idTextField, nameTextField, dOBTextField, genderMenu);
        gridPane.addColumn(7, contactText, addressText, gradeText);
        gridPane.addColumn(8, contactTextField, addressTextField, gradeMenu);


        GridPane displayPane = new GridPane();
        displayPane.setPadding(new Insets(25, 0, 0, 300));
        displayPane.setHgap(20);
        displayPane.setVgap(20);
        displayPane.add(errorLabel, 3, 0);
        displayPane.add(notification, 3, 1);
        displayPane.add(resetBtn, 1, 2);
        displayPane.add(registerBtn, 4, 2);

        delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(actionEvent -> {
            try {
                new DisplayStudents().start(displayPage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            stage.close();
        });


        // Validation
        backBtn.setOnAction(actionEvent -> {
            try {
                new DisplayStudents().start(displayPage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            stage.close();
        });
        registerBtn.setOnAction(actionEvent -> {
            boolean idRegex = Pattern.matches("^art-(ss|jss)-2021-[0-9]{4}$", idTextField.getText());
            boolean nameRegex = Pattern.matches("^([A-Za-z]+( )[A-Za-z]\\w{1,20})$", nameTextField.getText());
            boolean contactRegex = Pattern.matches("^([+234])[0-9]{13}$", contactTextField.getText());
            if (idTextField.getText().isEmpty() || nameTextField.getText().isEmpty() || genderMenu.getText().isEmpty()
                || contactTextField.getText().isEmpty() || addressTextField.getText().isEmpty() || gradeMenu.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.NONE, "Enter All Details", ButtonType.OK);
                alert.setTitle("Art College Notification");
                alert.showAndWait();
            } else if (!idRegex) {
                alert = new Alert(Alert.AlertType.NONE, "Admission Number Format Not Supported \n::: art-ss/jss-2021-xxxx",
                        ButtonType.OK);
                alert.setTitle("Art College Notification");
                alert.showAndWait();
            } else if (!nameRegex) {
                alert = new Alert(Alert.AlertType.NONE, "Name Format Not Supported \\n::: FirstName and LastName",
                        ButtonType.OK);
                alert.setTitle("Art College Notification");
                alert.showAndWait();
            } else if(genderMenu.getText().equals("Select Gender")) {
                alert = new Alert(Alert.AlertType.NONE, "Gender Format Not Supported \\n::: Male or Female",
                        ButtonType.OK);
                alert.setTitle("Art College Notification");
                alert.showAndWait();
            } else if(!contactRegex) {
                alert = new Alert(Alert.AlertType.NONE, "Phone Format Not Supported \\n::: +234xxxxxxxxxx",
                        ButtonType.OK);
                alert.setTitle("Art College Notification");
                alert.showAndWait();
            } else if(gradeMenu.getText().equals("Select Grade")) {
                alert = new Alert(Alert.AlertType.NONE, "Grade Format Not Supported \\n::: Jss1-3 to SS1-3",
                        ButtonType.OK);
                alert.setTitle("Art College Notification");
                alert.showAndWait();
            } else {
                dbValidation();
            }
        });
        resetBtn.setOnAction(actionEvent -> clearText());


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

    public void registerStudents() {
        try {
            String sql = "Insert into art_college.students_details " +
                "(AdmissionNumber, FullName, DOB, Gender, Contact, Address, Grade) values (?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, idTextField.getText().toLowerCase(Locale.ROOT));
            preparedStatement.setString(2, nameTextField.getText().toLowerCase(Locale.ROOT));
            preparedStatement.setString(3, dOBTextField.getValue().toString().toLowerCase(Locale.ROOT));
            preparedStatement.setString(4, genderMenu.getText().toLowerCase(Locale.ROOT));
            preparedStatement.setString(5, contactTextField.getText().toLowerCase(Locale.ROOT));
            preparedStatement.setString(6, addressTextField.getText().toLowerCase(Locale.ROOT));
            preparedStatement.setString(7, gradeMenu.getText().toLowerCase(Locale.ROOT));
            preparedStatement.executeUpdate();
            alert = new Alert(Alert.AlertType.NONE, "Successfully Added to Database", ButtonType.OK);
            alert.setTitle("Art College Notification");
            alert.showAndWait();
            clearText();
            delay.play();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dbValidation() {
        try {
            String sql = "select AdmissionNumber from art_college.students_details where AdmissionNumber = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, idTextField.getText().toLowerCase(Locale.ROOT));
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                alert = new Alert(Alert.AlertType.NONE, "User Already Exists", ButtonType.OK);
                alert.setTitle("Art College Notification");
                alert.showAndWait();
                clearText();
            } else {
                alert = new Alert(Alert.AlertType.NONE, "Adding to Database", ButtonType.OK);
                alert.setTitle("Art College Notification");
                alert.showAndWait();
                registerStudents();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public AddStudents() { conn = JDBC.connectDB(); }

    public void clearText() {
        idTextField.clear();                nameTextField.clear();
        contactTextField.clear();           addressTextField.clear();
        dOBTextField.setValue(null);
        genderMenu.setText("Select Gender");            gradeMenu.setText("Select Grade");
        errorLabel.setText("");             notification.setText("");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
