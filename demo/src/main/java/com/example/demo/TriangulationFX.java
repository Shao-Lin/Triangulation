package com.example.demo;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class TriangulationFX extends Application {
    public static void main(String[] args) {
        launch(args);


    }

    @Override
    public void start(Stage primaryStage) {
        // Пример многоугольника
        ArrayList<Point2D.Double> polygon = createPolygon();

        // Триангуляция
        ArrayList<Triangle> triangles = triangulate(polygon);


        // Создание JavaFX-приложения
        Pane root = new Pane();

        // Отображение треугольников
        for (Triangle triangle : triangles) {
            drawTriangle(triangle, root);
        }

        // Настройка сцены
        Scene scene = new Scene(root, 800, 800);
        primaryStage.setTitle("Triangulation Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private ArrayList<Point2D.Double> createPolygon() {
        ArrayList<Point2D.Double> polygon = new ArrayList<>();
        polygon.add(new Point2D.Double(100, 100));
        polygon.add(new Point2D.Double(100, 200));
        polygon.add(new Point2D.Double(150, 250));
        polygon.add(new Point2D.Double(200, 200));
        polygon.add(new Point2D.Double(200, 100));
        polygon.add(new Point2D.Double(150, 50));
        return polygon;
    }

    public static ArrayList<Triangle> triangulate(List<Point2D.Double> polygon) {
        ArrayList<Triangle> triangles = new ArrayList<>();
        int indexVertexPolygon = 1; // индекс углов полигона

        for (int i = 2; i < polygon.size(); i++){

            Point2D.Double p1 = polygon.get(0);
            Point2D.Double p2 = polygon.get(indexVertexPolygon);
            Point2D.Double p3 = polygon.get(indexVertexPolygon + 1);

            Triangle triangle = new Triangle(p1, p2, p3);
            triangles.add(triangle);
            indexVertexPolygon++;
        }

        return triangles;
    }


    public static void drawTriangle(Triangle triangle,Pane root){
            Polygon triangleShape = new Polygon();
            triangleShape.getPoints().addAll(
                    triangle.p1.getX(), triangle.p1.getY(),
                    triangle.p2.getX(), triangle.p2.getY(),
                    triangle.p3.getX(), triangle.p3.getY()
            );
            triangleShape.setFill(Color.WHITE);
            triangleShape.setStroke(Color.BLACK);
            root.getChildren().add(triangleShape);

    }

}

