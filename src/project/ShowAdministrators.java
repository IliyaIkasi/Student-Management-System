package project;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowAdministrators extends Application {

    TableView<ObservableList> table;

    Connection conn;
    ResultSet resultSet;

    @Override
    public void start(Stage stage) {
        Image arrowImage = new Image("project\\images\\right_arrow.png");
        ImageView arrowView = new ImageView(arrowImage);
        arrowView.setFitWidth(50);
        arrowView.setFitHeight(50);
        Image logoImage = new Image("project\\images\\logo.png");
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitWidth(50);
        logoView.setFitHeight(50);
        Image schoolImage = new Image("project\\images\\school.png");
        ImageView schoolView = new ImageView(schoolImage);
        schoolView.setFitWidth(50);
        schoolView.setFitHeight(50);

        // BackBtn
        Button backBtn = new Button();
        backBtn.setGraphic(arrowView);

        BorderPane logoPane = new BorderPane();
        logoPane.setStyle("-fx-background-color: lightskyblue;");
        logoPane.setPadding(new Insets(20, 20, 30, 20));
        logoPane.setLeft(backBtn);
        logoPane.setCenter(logoView);
        logoPane.setRight(schoolView);
        BorderPane.setMargin(backBtn, new Insets(0, 0, 0, 50));
        BorderPane.setMargin(schoolView, new Insets(0, 50, 0, 0));



        // Table Column
        table = new TableView<>();
        table.setPrefSize(1000, 550);
        refreshAdmin();

        //Buttons
//        Button addBtn = new Button("Add");
//        addBtn.setPrefSize(100, 50);
//        addBtn.setStyle("-fx-background-color: green");
        Button updateBtn = new Button("Update");
        updateBtn.setPrefSize(100, 50);
        updateBtn.setStyle("-fx-background-color: yellow;");
        Button deleteBtn = new Button("Delete");
        deleteBtn.setPrefSize(100, 50);
        deleteBtn.setStyle("-fx-background-color: red;");
//        Button refreshBtn = new Button("Refresh");
//        refreshBtn.setPrefSize(100, 50);
//        refreshBtn.setStyle("-fx-background-color: grey;");


        GridPane buttonsPane = new GridPane();
        buttonsPane.setPrefSize(300, 550);
        buttonsPane.setStyle("-fx-background-color: black;");
        buttonsPane.setAlignment(Pos.TOP_CENTER);
        buttonsPane.setPadding(new Insets(100, 0, 0, 0));
        buttonsPane.setVgap(30);
//        buttonsPane.addRow(0, addBtn);
        buttonsPane.addRow(1, updateBtn);
        buttonsPane.addRow(2, deleteBtn);
//        buttonsPane.addRow(3, refreshBtn);

        // InfoPane
        GridPane infoPane = new GridPane();
        infoPane.setPadding(new Insets(0, 10, 10, 10));
        infoPane.setPrefWidth(1230);
        infoPane.setPrefHeight(560);
        infoPane.setHgap(10);
        infoPane.addColumn(0, table);
        infoPane.addColumn(2, buttonsPane);


        //Validation
        backBtn.setOnAction(actionEvent -> {
            Stage homePage = new Stage();
            try {
                new HomePage().start(homePage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            stage.close();
        });
        updateBtn.setOnAction(actionEvent -> {
            Stage updatePage = new Stage();
            new UpdateAdministrators().start(updatePage);
            stage.close();
        });
        deleteBtn.setOnAction(actionEvent -> {
            Stage deletePage = new Stage();
            new DeleteAdministrators().start(deletePage);
            stage.close();
        });

        stage.setTitle("Student Management System");
        stage.setWidth(1230);
        stage.setHeight(720);
        stage.setResizable(false);

        VBox root = new VBox();

        root.setStyle("-fx-font: italic 15px 'TIMES NEW ROMAN'; -fx-background-color: BEIGE;");
        root.getChildren().addAll(logoPane, infoPane);
        root.setSpacing(10);
        stage.setScene(new Scene(root));
        stage.show();
    }

    ObservableList data;


    public void refreshAdmin() {
        try {
            String sql = "Select * from art_college.admin_details";
            data = FXCollections.observableArrayList();
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
                System.out.println("Column ["+i+"] ");
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

    public ShowAdministrators() {
        conn = JDBC.connectDB();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
