package project;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DisplayStudents extends Application {
    TableView<ObservableList> table;

    Connection conn;
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


        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(1000, 550);
        gridPane.setPadding(new Insets(10, 20, 0, 20));
        gridPane.setHgap(10);


        table = new TableView<>();
        table.setPrefWidth(1000);
        this.refreshStudents();


        // Add Button
        Button addBtn = new Button("Add");
        addBtn.setPrefSize(100, 50);
        addBtn.setStyle("-fx-background-color: green");
        //Update Button
        Button updateBtn = new Button("Update");
        updateBtn.setPrefSize(100, 50);
        updateBtn.setStyle("-fx-background-color: YELLOW");
        // Delete Button
        Button deleteBtn = new Button("Delete");
        deleteBtn.setPrefSize(100, 50);
        deleteBtn.setStyle("-fx-background-color: RED");


        // Add, Update, Delete, Refresh Buttons
        GridPane buttonsPane = new GridPane();
        buttonsPane.setStyle("-fx-background-color: black;");
        buttonsPane.setPrefSize(200, 730);
        buttonsPane.setVgap(30);
        buttonsPane.setAlignment(Pos.TOP_CENTER);
        buttonsPane.setPadding(new Insets(100, 0, 0, 0));
        buttonsPane.addRow(1, addBtn);
        buttonsPane.addRow(2, updateBtn);
        buttonsPane.addRow(3, deleteBtn);


        gridPane.addColumn(0, table);
        gridPane.addColumn(2, buttonsPane);


        // Validation
        backBtn.setOnAction(actionEvent -> {
            Stage homePage = new Stage();
            try {
                new HomePage().start(homePage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            stage.close();
        });
        addBtn.setOnAction(e -> {
            Stage addStudents = new Stage();
            new AddStudents().start(addStudents);
            stage.close();
        });
        updateBtn.setOnAction(actionEvent -> {
            Stage updatePage = new Stage();
            new UpdateStudents().start(updatePage);
            stage.close();
        });
        deleteBtn.setOnAction(actionEvent -> {
            Stage deletePage = new Stage();
            new DeleteStudents().start(deletePage);
            stage.close();
        });

        stage.setTitle("Students Management System");
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


    public void refreshStudents() {
        try {
            String sql = "Select * from art_college.students_details";
            ObservableList<ObservableList> data = FXCollections.observableArrayList();
            resultSet = conn.createStatement().executeQuery(sql);

            for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(resultSet.getMetaData().getColumnName(i + 1));
                final  int j = i;
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                table.getColumns().add(col);
//                System.out.println("Column ["+i+"] ");
            }

            while (resultSet.next()) {
                ObservableList row = FXCollections.observableArrayList();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    row.add(resultSet.getString(i));
                }
                System.out.println("row [1] added " + row);
                data.add(row);
            }
            table.setItems(data);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    public DisplayStudents() {
        conn = JDBC.connectDB();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
