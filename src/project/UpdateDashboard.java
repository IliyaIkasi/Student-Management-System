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
    MenuItem tenMin;
    MenuItem twentyMin;
    MenuItem thirtyMin;
    MenuItem fortyMin;
    MenuItem fiftyMin;
    MenuItem oneHour;
    MenuButton durationMenu;

    // Section
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
    Button registerBtn;

    // Delay
    PauseTransition delay;
    Alert alert;


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
        tenMin = new MenuItem("10Mins");
        twentyMin = new MenuItem("20Mins");
        thirtyMin = new MenuItem("30Mins");
        fortyMin = new MenuItem("40Mins");
        fiftyMin = new MenuItem("50Mins");
        oneHour = new MenuItem("1hour");
        durationMenu = new MenuButton("Select Duration", null, tenMin, twentyMin, thirtyMin, fortyMin, fiftyMin, oneHour);
        tenMin.setOnAction(actionEvent -> durationMenu.setText(tenMin.getText()));
        twentyMin.setOnAction(actionEvent -> durationMenu.setText(twentyMin.getText()));
        thirtyMin.setOnAction(actionEvent -> durationMenu.setText(thirtyMin.getText()));
        fortyMin.setOnAction(actionEvent -> durationMenu.setText(fortyMin.getText()));
        fiftyMin.setOnAction(actionEvent -> durationMenu.setText(fiftyMin.getText()));
        oneHour.setOnAction(actionEvent -> durationMenu.setText(oneHour.getText()));

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
        registerBtn = new Button("Update");
        registerBtn.setPadding(new Insets(10, 30, 10, 30));


        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(20);
        gridPane.setPadding(new Insets(50, 0, 25, 200));
        gridPane.addColumn(0, teacherIDText, periodText);
        gridPane.addColumn(1, teacherIDTextField, periodMenu);
        gridPane.addColumn(7, nameText, durationText, gradeText);
        gridPane.addColumn(8, nameTextField, durationMenu, gradeMenu);


        GridPane displayPane = new GridPane();
        displayPane.setPadding(new Insets(25, 0, 0, 300));
        displayPane.setHgap(20);
        displayPane.setVgap(20);
        displayPane.add(searchBtn, 1, 2);
        displayPane.add(registerBtn, 4, 2);

        delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(actionEvent -> {
            try {
                new Dashboard().start(dashboard);
            } catch (Exception e) {
                e.printStackTrace();
            }
            stage.close();
        });


        // Validation
        backBtn.setOnAction(actionEvent -> {
            try {
                new Dashboard().start(dashboard);
            } catch (Exception e) {
                e.printStackTrace();
            }
            stage.close();
        });
        registerBtn.setOnAction(actionEvent -> {
            boolean idRegex = Pattern.matches("^art-ts-2021-[0-9]{4}$", teacherIDTextField.getText());
            boolean nameRegex = Pattern.matches("^([A-Za-z]+( )[A-Za-z]\\w{1,20})$", nameTextField.getText());
            if (teacherIDTextField.getText().isEmpty() || nameTextField.getText().isEmpty() || periodText.getText().isEmpty()
                    || durationText.getText().isEmpty() || gradeMenu.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.NONE, "Enter All Details", ButtonType.OK);
                alert.setTitle("Art College Notification");
                alert.showAndWait();
            } else if (!idRegex) {
                alert = new Alert(Alert.AlertType.NONE, "Teacher ID Format Not Supported \n::: art-ts-2021-xxxx",
                        ButtonType.OK);
                alert.setTitle("Art College Notification");
                alert.showAndWait();
            } else if (!nameRegex) {
                alert = new Alert(Alert.AlertType.NONE, "Name Format Not Supported \n::: FirstName and LastName",
                        ButtonType.OK);
                alert.setTitle("Art College Notification");
                alert.showAndWait();
            } else if(periodMenu.getText().equals("Select Period")) {
                alert = new Alert(Alert.AlertType.NONE, "Period Format Not Supported \n::: 1st - 8th Period",
                        ButtonType.OK);
                alert.setTitle("Art College Notification");
                alert.showAndWait();
            } else if (durationMenu.getText().equals("Select Duration")) {
                alert = new Alert(Alert.AlertType.NONE, "Duration Format Not Supported \n::: 10mins - 1hour",
                        ButtonType.OK);
                alert.setTitle("Art College Notification");
                alert.showAndWait();
            } else if (gradeMenu.getText().equals("Select Grade")) {
                alert = new Alert(Alert.AlertType.NONE, "Grade Format Not Supported \n::: Jss1 - Jss3 / Ss1 - Ss3",
                        ButtonType.OK);
                alert.setTitle("Art College Notification");
                alert.showAndWait();
            } else {
                dbValidation();
            }
        });
        searchBtn.setOnAction(actionEvent -> {
            if (teacherIDTextField.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.NONE, "Enter ID Number", ButtonType.OK);
                alert.setTitle("Art College Notification");
                alert.showAndWait();
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
            String sql = "Select TeacherID, TeacherName, Period, Duration, Section from art_college.teacher_dashboard " +
                    "where TeacherID = ? ";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, teacherIDTextField.getText().toLowerCase(Locale.ROOT));
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                alert = new Alert(Alert.AlertType.NONE, "Searching in Progress!!!", ButtonType.OK);
                alert.setTitle("Art College Notification");
                alert.showAndWait();
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String period = resultSet.getString(3);
                String duration = resultSet.getString(4);
                String section = resultSet.getString(5);

                teacherIDTextField.setText(id);                nameTextField.setText(name);
                periodMenu.setText(period);           durationMenu.setText(duration);
                gradeMenu.setText(section);
            } else {
                Alert alert = new Alert(Alert.AlertType.NONE, "User Doesn't Exist", ButtonType.OK);
                alert.setTitle("Art College Notification");
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
            preparedStatement.setString(4, durationMenu.getText().toLowerCase(Locale.ROOT));
            preparedStatement.setString(5, gradeMenu.getText().toLowerCase(Locale.ROOT));
            preparedStatement.setString(6, teacherIDTextField.getText().toLowerCase(Locale.ROOT));
            preparedStatement.executeUpdate();
            clearText();
            alert = new Alert(Alert.AlertType.NONE, "Updated!!! \nRedirecting", ButtonType.OK);
            alert.setTitle("Art College Notification");
            alert.showAndWait();
            delay.play();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dbValidation() {
        try {
            String sql = "select TeacherID from art_college.teacher_dashboard where TeacherID = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, teacherIDTextField.getText().toLowerCase(Locale.ROOT));
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()) {
                alert = new Alert(Alert.AlertType.NONE, "User Already Exists", ButtonType.OK);
                alert.setTitle("Art College Notification");
                alert.showAndWait();
//                clearText();
            } else {
                alert = new Alert(Alert.AlertType.NONE, "Updating to Teachers Database!!! \nPlease Wait!!!",
                        ButtonType.OK);
                alert.setTitle("Art College Notification");
                alert.showAndWait();
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
        durationMenu.setText("Select Duration");
        gradeMenu.setText("Select Grade");           periodMenu.setText("Select Period");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
