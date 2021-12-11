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

public class UpdateDashboard extends Application {
    // Teacher ID
    Text teacherIDText;
    TextField teacherIDTextField;

    // FullName
    Text nameText;
    TextField nameTextField;

    // Grade
    Text periodText;
    MenuItem period1;
    MenuItem period2;
    MenuItem period3;
    MenuItem period4;
    MenuItem period5;
    MenuItem period6;
    MenuItem period7;
    MenuItem period8;
    MenuButton periodMenu;

    // Class Duration
    Text durationText;
    TextField durationTextField;

    // Section
    Text sectionText;
    MenuItem grade1;
    MenuItem grade2;
    MenuItem grade3;
    MenuItem grade4;
    MenuItem grade5;
    MenuItem grade6;
    MenuButton sectionTextField;

    // Reset Button
    Button searchBtn;

    // Registration Button
    Button registerBtn;

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
        teacherIDText = new Text("Teacher ID:");
        teacherIDTextField = new TextField();
        teacherIDTextField.setPromptText("Teacher ID");
        teacherIDTextField.setMinWidth(200);

        // Full Name
        nameText = new Text("Teacher Name:");
        nameTextField = new TextField();
        nameTextField.setPromptText("Teacher Name");
        nameTextField.setMinWidth(200);


        //Grade
        periodText = new Text("Grade:");
        period1 = new MenuItem("1st Period");
        period2 = new MenuItem("2nd Period");
        period3 = new MenuItem("3rd Period");
        period4 = new MenuItem("4th Period");
        period5 = new MenuItem("5th Period");
        period6 = new MenuItem("6th Period");
        period7 = new MenuItem("7th Period");
        period8 = new MenuItem("8th Period");
        periodMenu = new MenuButton("Select Period",
                null, period1, period2, period3, period4, period5, period6, period7, period8);
        period1.setOnAction(e -> periodMenu.setText(period1.getText()));
        period2.setOnAction(e -> periodMenu.setText(period2.getText()));
        period3.setOnAction(e -> periodMenu.setText(period3.getText()));
        period4.setOnAction(e -> periodMenu.setText(period4.getText()));
        period5.setOnAction(e -> periodMenu.setText(period5.getText()));
        period6.setOnAction(e -> periodMenu.setText(period6.getText()));
        period7.setOnAction(e -> periodMenu.setText(period7.getText()));
        period8.setOnAction(e -> periodMenu.setText(period8.getText()));

        // Class Duration
        durationText = new Text("Duration:");
        durationTextField = new TextField();
        durationTextField.setPromptText("Duration");
        durationTextField.setMinWidth(200);


        //Grade
        sectionText = new Text("Grade:");
        grade1 = new MenuItem("Jss1");
        grade2 = new MenuItem("Jss2");
        grade3 = new MenuItem("Jss3");
        grade4 = new MenuItem("SS1");
        grade5 = new MenuItem("SS2");
        grade6 = new MenuItem("SS3");
        sectionTextField = new MenuButton("Select Grade", null, grade1, grade2, grade3, grade4, grade5, grade6);
        grade1.setOnAction(e -> sectionTextField.setText(grade1.getText()));
        grade2.setOnAction(e -> sectionTextField.setText(grade2.getText()));
        grade3.setOnAction(e -> sectionTextField.setText(grade3.getText()));
        grade4.setOnAction(e -> sectionTextField.setText(grade4.getText()));
        grade5.setOnAction(e -> sectionTextField.setText(grade5.getText()));
        grade6.setOnAction(e -> sectionTextField.setText(grade6.getText()));


        // Reset Button
        searchBtn = new Button("Search");
        searchBtn.setPadding(new Insets(10, 30, 10, 30));

        // Registration Button
        registerBtn = new Button("Update");
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
        gridPane.addColumn(0, teacherIDText, nameText, periodText);
        gridPane.addColumn(1, teacherIDTextField, nameTextField, periodMenu);
        gridPane.addColumn(7, durationText, sectionText);
        gridPane.addColumn(8, durationTextField, sectionTextField);


        GridPane displayPane = new GridPane();
        displayPane.setPadding(new Insets(25, 0, 0, 300));
        displayPane.setHgap(20);
        displayPane.setVgap(20);
        displayPane.add(errorLabel, 3, 0);
        displayPane.add(notification, 3, 1);
        displayPane.add(searchBtn, 1, 2);
        displayPane.add(registerBtn, 4, 2);

        delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(actionEvent -> {
            try {
                new DisplayStudents().start(dashboard);
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
        registerBtn.setOnAction(actionEvent -> {
            boolean idRegex = Pattern.matches("^art-ts-2021-[0-9]{4}$", teacherIDTextField.getText());
            if (teacherIDTextField.getText().isEmpty() || nameTextField.getText().isEmpty() || periodText.getText().isEmpty()
                    || durationText.getText().isEmpty() || sectionTextField.getText().isEmpty()) {
                errorLabel.setText("Enter All Details");
                notification.setText("");
            } else if (!idRegex) {
                errorLabel.setText("Admission Number Format Not Supported \n::: art-ts-2021-xxxx");
                notification.setText("");
            } else if(periodMenu.getText().equals("Select Period")) {
                errorLabel.setText("Grade Format Not Supported \n::: 1st - 8th Period");
                notification.setText("");
            } else {
                errorLabel.setText("");
                dbValidation();
            }
        });
        searchBtn.setOnAction(actionEvent -> {
            if (teacherIDTextField.getText().isEmpty()) {
                errorLabel.setText("Enter All Details");
                notification.setText("");
            } else {
                searchTeachers();
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


    public void searchTeachers() {
        try {
            String sql = "Select TeacherID, TeacherName, Period, Duration, Section from art_college.teacher_dashboard where TeacherID = ? ";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, teacherIDTextField.getText().toLowerCase(Locale.ROOT));
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                notification.setText("Welcome!!! Redirecting");
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String period = resultSet.getString(3);
                String duration = resultSet.getString(4);
                String section = resultSet.getString(5);

                teacherIDTextField.setText(id);                nameTextField.setText(name);
                periodMenu.setText(period);           durationTextField.setText(duration);
                sectionTextField.setText(section);
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
            String sql = "Update art_college.teacher_dashboard set TeacherID = ?, TeacherName = ?, " +
                    "Period = ?, Duration = ?, Section = ? where TeacherID = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, teacherIDTextField.getText().toLowerCase(Locale.ROOT));
            preparedStatement.setString(2, nameTextField.getText().toLowerCase(Locale.ROOT));
            preparedStatement.setString(3, periodMenu.getText().toLowerCase(Locale.ROOT));
            preparedStatement.setString(4, durationTextField.getText().toLowerCase(Locale.ROOT));
            preparedStatement.setString(5, sectionTextField.getText().toLowerCase(Locale.ROOT));
            preparedStatement.setString(6, teacherIDTextField.getText().toLowerCase(Locale.ROOT));
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
            preparedStatement.setString(1, teacherIDTextField.getText().toLowerCase(Locale.ROOT));
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

    public UpdateDashboard() {
        conn = JDBC.connectDB();
    }

    public void clearText() {
        teacherIDTextField.clear();                nameTextField.clear();
        durationTextField.clear();
        sectionTextField.setText("Select Grade");           periodMenu.setText("Select Period");
        errorLabel.setText("");             notification.setText("");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
