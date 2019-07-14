package com.stormnet.figuresfx.controller;

import com.stormnet.figuresfx.drawutils.Drawer;
import com.stormnet.figuresfx.figures.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;


public class MainScreenViewController implements Initializable {
    final static Logger logger = Logger.getLogger(MainScreenViewController.class);
    List<Figure> figures = new ArrayList<>();
    private Random random;

    @FXML
    private Canvas canvas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        random = new Random(System.currentTimeMillis());
    }

    @FXML
    private void onMouseClicked(MouseEvent mouseEvent) throws Exception {
        addFigure(createFigure(mouseEvent.getX(), mouseEvent.getY()));
        repaint();
    }

    private void repaint() {

        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        Drawer<Figure> drawer = new Drawer<>(figures);
        drawer.draw(canvas.getGraphicsContext2D());
    }

    private Figure createFigure(double x, double y) throws Exception {
        Figure figure=null;

        switch (random.nextInt(5)) {
            case Figure.FIGURE_TYPE_CIRCLE:
                figure = new Circle(x, y, random.nextInt(9), Color.BROWN, random.nextInt(50));
                break;
            case Figure.FIGURE_TYPE_RECTANGLE:
                figure = new Rectangle(x, y, random.nextInt(7), Color.BLACK, random.nextInt(100), random.nextInt(100));
                break;
            case Figure.FIGURE_TYPE_TRIANGLE:
                figure = new Triangle(x, y, random.nextInt(4), Color.BLUE, random.nextInt(100));
                break;
            case Figure.FIGURE_TYPE_ELLIPSE:
                figure = new Ellipse(x, y, random.nextInt(5), Color.LIGHTPINK, random.nextInt(100), random.nextInt(100));
                break;
            default:
                logger.error(new FigureExcaption("Незвестный тип фигуры"));
               // throw new FigureExcaption("Незвестный тип фигуры");

        }
        return figure;
    }

    private void addFigure(Figure figure) {

        figures.add(figure);
    }
}
