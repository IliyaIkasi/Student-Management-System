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
import java.time.LocalDate;
import java.util.Locale;
import java.util.regex.Pattern;

public class DeleteStudents extends Application {
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
    // Home Address
    Text courseText;
    TextField courseTextField;

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
    Button searchBtn;
    // Registration Button
    Button deleteBtn;

    // Error Label
    Label errorLabel;
    // Notification Label
    Label notification;

    // Delay
    PauseTransition delay;


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


        // Address
        courseText = new Text("Address:");
        courseTextField = new TextField();
        courseTextField.setPromptText("Address");
        courseTextField.setMinWidth(200);

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
        searchBtn = new Button("Search");
        searchBtn.setPadding(new Insets(10, 30, 10, 30));

        // Registration Button
        deleteBtn = new Button("Delete");
        deleteBtn.setPadding(new Insets(10, 30, 10, 30));

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
        gridPane.addColumn(7, contactText, addressText, gradeText, courseText);
        gridPane.addColumn(8, contactTextField, addressTextField, gradeMenu, courseTextField);


        GridPane displayPane = new GridPane();
        displayPane.setPadding(new Insets(25, 0, 0, 300));
        displayPane.setHgap(20);
        displayPane.setVgap(20);
        displayPane.add(errorLabel, 3, 0);
        displayPane.add(notification, 3, 1);
        displayPane.add(searchBtn, 1, 2);
        displayPane.add(deleteBtn, 4, 2);

        delay = new PauseTransition(Duration.seconds(5));
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
                e.getCause();
            }
            stage.close();
        });
        deleteBtn.setOnAction(actionEvent -> {
            boolean idRegex = Pattern.matches("^art-(ss|jss)-2021-[0-9]{4}$", idTextField.getText());
            boolean nameRegex = Pattern.matches("^([A-Za-z]+( )[A-Za-z]\\w{1,20})$", nameTextField.getText());
            boolean contactRegex = Pattern.matches("^([+234])[0-9]{13}$", contactTextField.getText());
            if (idTextField.getText().isEmpty() || nameTextField.getText().isEmpty() || genderMenu.getText().isEmpty()
                    || contactTextField.getText().isEmpty() || addressTextField.getText().isEmpty() || gradeMenu.getText().isEmpty()) {
                errorLabel.setText("Enter All Details");
                notification.setText("");
            } else if (!idRegex) {
                errorLabel.setText("Admission Number Format Not Supported \n::: art-ss/jss-2021-xxxx");
                notification.setText("");
            } else if (!nameRegex) {
                errorLabel.setText("Name Format Not Supported \n::: FirstName and LastName");
                notification.setText("");
            } else if(genderMenu.getText().equals("Select Gender")) {
                errorLabel.setText("Gender Format Not Supported \n::: Male or Female");
                notification.setText("");
            } else if(!contactRegex) {
                errorLabel.setText("Phone Format Not Supported \n::: +234xxxxxxxxxx");
                notification.setText("");
            } else if(gradeMenu.getText().equals("Select Grade")) {
                errorLabel.setText("Grade Format Not Supported \n::: Jss1-3 to SS1-3");
            } else {
                errorLabel.setText("");
                dbValidation();
            }
        });
        searchBtn.setOnAction(actionEvent -> {
            if (idTextField.getText().isEmpty()) {
                errorLabel.setText("Enter All Details");
                notification.setText("");
            } else {
                searchStudents();
            }
        });


        stage.setTitle("Students Management");
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


    public void searchStudents() {
        try {
            String sql = "Select AdmissionNumber, FullName, DOB, Gender, Contact, Address, Grade, Courses from " +
                    "art_college.students_details where AdmissionNumber = ? ";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, idTextField.getText().toLowerCase(Locale.ROOT));
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                notification.setText("Welcome!!! Redirecting");
//                new DisplayStudents().start(displayPage);
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String dob = resultSet.getString(3);
                String gender = resultSet.getString(4);
                String contact = resultSet.getString(5);
                String address = resultSet.getString(6);
                String grade = resultSet.getString(7);
                String course = resultSet.getString(8);

                idTextField.setText(id);                nameTextField.setText(name);
                contactTextField.setText(contact);           addressTextField.setText(address);
                String s = String.valueOf(dob);             dOBTextField.setValue(LocalDate.parse(s));
                genderMenu.setText(gender);            gradeMenu.setText(grade);  courseTextField.setText(course);
                errorLabel.setText("");             notification.setText("");
                System.out.println(s);
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


    public void deleteStudents() {
        try {
            String sql = "Delete from art_college.students_details where AdmissionNumber = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, idTextField.getText().toLowerCase(Locale.ROOT));
            preparedStatement.executeUpdate();
            clearText();
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(actionEvent -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted!!! \nRedirecting", ButtonType.OK);
                alert.setTitle("Deleted from Database");
                alert.showAndWait();
                this.delay.play();
            });

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
            if(!resultSet.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "User Doesn't Exist", ButtonType.OK);
                alert.setTitle("User Doesn't Exist");
                alert.showAndWait();
                clearText();
            } else {
                notification.setText("Updating!!! \nPlease Wait!!!");
                deleteStudents();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void clearText() {
        idTextField.clear();                nameTextField.clear();
        contactTextField.clear();           addressTextField.clear();
        dOBTextField.setValue(null);     courseTextField.clear();
        genderMenu.setText("Select Gender");            gradeMenu.setText("Select Grade");
        errorLabel.setText("");             notification.setText("");
    }

    public DeleteStudents() { conn = JDBC.connectDB(); }

    public static void main(String[] args) {
        launch(args);
    }
}
