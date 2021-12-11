package project;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;


public class SsCourses extends Application {
    // Admission Number
    Text idText;
    TextField idTextField;
    Label errorLabel;

    // Core Courses
    Text coreStream;
    CheckBox course1;
    String selected1;
    CheckBox course2;
    String selected2;
    CheckBox course3;
    String selected3;
    CheckBox course4;
    String selected4;
    CheckBox course5;
    String selected5;

    // Science Courses
    Text sciencesStream;
    CheckBox course6;
    String selected6;
    CheckBox course7;
    String selected7;
    CheckBox course8;
    String selected8;
    CheckBox course9;
    String selected9;
    CheckBox course10;
    String selected10;

    // Technical Courses
    Text technicalStream;
    CheckBox course11;
    String selected11;
    CheckBox course12;
    String selected12;
    CheckBox course13;
    String selected13;

    // Commercial Courses
    Text commerceStream;
    CheckBox course14;
    String selected14;
    CheckBox course15;
    String selected15;
    CheckBox course16;
    String selected16;
    CheckBox course17;
    String selected17;
    CheckBox course18;
    String selected18;

    // Art Courses
    Text artStream;
    CheckBox course19;
    String selected19;
    CheckBox course20;
    String selected20;
    CheckBox course21;
    String selected21;
    CheckBox course22;
    String selected22;
    CheckBox course23;
    String selected23;
    CheckBox course24;
    String selected24;

    ObservableList<String> coursesList = FXCollections.observableArrayList();



    Connection conn;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

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


        // Student Admission Number and GridPane
        idText = new Text("Admission Number:");
        idTextField = new TextField();
        idTextField.setPromptText("Admission Number");
        idTextField.setPrefWidth(200);
        errorLabel = new Label();
        errorLabel.setPrefWidth(200);
        errorLabel.setTextFill(Color.TOMATO);


        GridPane textPane = new GridPane();
        textPane.setPadding(new Insets(20, 0, 0, 300));
        textPane.setVgap(10);
        textPane.setHgap(10);
        textPane.addRow(0, idText, idTextField, errorLabel);


        // Courses and GridPane
        coreStream = new Text("Core Subjects:");
        course1 = new CheckBox("English");
        course2 = new CheckBox("Mathematics");
        course3 = new CheckBox("Civic Education");
        course4 = new CheckBox("Computer Science");
        course5 = new CheckBox("Christian Religious Studies");

        sciencesStream = new Text("Basic Science/Maths Stream:");
        course6 = new CheckBox("Biology");
        course7 = new CheckBox("Chemistry");
        course8 = new CheckBox("Further Mathematics");
        course9 = new CheckBox("Health and Physical Education");
        course10 = new CheckBox("Physics");

        technicalStream = new Text("Technical and Agricultural Stream:");
        course11 = new CheckBox("Agricultural Science");
        course12 = new CheckBox("Food and Nutrition");
        course13 = new CheckBox("Technical Drawing");

        commerceStream = new Text("Commercial Stream:");
        course14 = new CheckBox("Book Keeping");
        course15 = new CheckBox("Commerce");
        course16 = new CheckBox("Data Processing");
        course17 = new CheckBox("Financial Accounting");
        course18 = new CheckBox("Office Practice");

        artStream = new Text("Liberal Arts and Social Science:");
        course19 = new CheckBox("Economics");
        course20 = new CheckBox("Fine Art");
        course21 = new CheckBox("French");
        course22 = new CheckBox("Geography");
        course23 = new CheckBox("Government");
        course24 = new CheckBox("Literature in English");


        GridPane coursePane = new GridPane();
        coursePane.setPadding(new Insets(30, 30, 0, 30));
        coursePane.setVgap(15);
        coursePane.setHgap(20);
        coursePane.addColumn(0, coreStream, course1, course2, course3, course4, course5);
        coursePane.addColumn(1, sciencesStream, course6, course7, course8, course9, course10);
        coursePane.addColumn(2, technicalStream, course11, course12, course13);
        coursePane.addColumn(3, commerceStream, course14,course15, course16, course17, course18);
        coursePane.addColumn(4, artStream, course19, course20, course21, course22, course23, course24);

        // Submit Image and Button
        Image submitImage = new Image("project\\images\\submit.png");
        ImageView submitView = new ImageView(submitImage);
        submitView.setFitWidth(70);
        submitView.setFitHeight(40);
        Button submitBtn = new Button();
        submitBtn.setGraphic(submitView);

        GridPane btnPane = new GridPane();
        btnPane.setPadding(new Insets(5, 0, 0, 400));
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
            if (idTextField.getText().isEmpty()) errorLabel.setText("AdminID cannot bt empty");
            else AddCourses();
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

    public String getSelected() {
        // Core Courses
        course1.setOnAction(actionEvent -> coursesList.add(course1.getText()));
        course2.setOnAction(actionEvent -> coursesList.add(course2.getText()));
        course3.setOnAction(actionEvent -> coursesList.add(course3.getText()));
        course4.setOnAction(actionEvent -> coursesList.add(course4.getText()));
        course5.setOnAction(actionEvent -> coursesList.add(course5.getText()));
//        selected1 = course1.getText();
//        if (course1.isSelected()) System.out.println(selected1);
//        selected2 = course2.getText();
//        if (course2.isSelected()) System.out.println(selected2);
//        selected3 = course3.getText();
//        if (course3.isSelected()) System.out.println(selected3);
//        selected4 = course4.getText();
//        if (course4.isSelected()) System.out.println(selected4);
//        selected5 = course5.getText();
//        if (course5.isSelected())System.out.println(selected5);

        // Science Courses
        selected6 = course6.getText();
        if (course6.isSelected()) System.out.println(selected6);
        selected7 = course7.getText();
        if (course7.isSelected()) System.out.println(selected7);
        selected8 = course8.getText();
        if (course8.isSelected()) System.out.println(selected8);
        selected9 = course9.getText();
        if (course9.isSelected()) System.out.println(selected9);
        selected10 = course10.getText();
        if (course10.isSelected()) System.out.println(selected10);

        // Technical Courses
        selected11 = course11.getText();
        if (course11.isSelected()) System.out.println(selected11);
        selected12 = course12.getText();
        if (course12.isSelected()) System.out.println(selected12);
        selected13 = course13.getText();
        if (course13.isSelected()) System.out.println(selected13);

        // Commercial Courses
        selected14 = course14.getText();
        if (course14.isSelected()) System.out.println(selected14);
        selected15 = course15.getText();
        if (course15.isSelected()) System.out.println(selected15);
        selected16 = course16.getText();
        if (course16.isSelected()) System.out.println(selected16);
        selected17 = course17.getText();
        if (course17.isSelected()) System.out.println(selected17);
        selected18 = course18.getText();
        if (course18.isSelected()) System.out.println(selected18);

        // Art Courses
        selected19 = course19.getText();
        if (course19.isSelected()) System.out.println(selected19);
        selected20 = course20.getText();
        if (course20.isSelected()) System.out.println(selected20);
        selected21 = course21.getText();
        if (course21.isSelected()) System.out.println(selected21);
        selected22 = course22.getText();
        if (course22.isSelected()) System.out.println( selected22);
        selected23 = course3.getText();
        if (course23.isSelected()) System.out.println(selected23);
        selected24 = course24.getText();
        if (course24.isSelected()) System.out.println(selected24);
        return null;
    }

    public void AddCourses() {
        try {
            String sql = "Insert into art_college.student_details (Courses) values '?' where AdmissionNumber = '?'";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, coursesList.toString());
            preparedStatement.setString(2, idTextField.getText().toLowerCase(Locale.ROOT));
            preparedStatement.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "User Already Exists", ButtonType.OK);
            alert.setTitle("User Already Exists");
            alert.showAndWait();
            new DisplayStudents();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public SsCourses() {
        conn = JDBC.connectDB();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
