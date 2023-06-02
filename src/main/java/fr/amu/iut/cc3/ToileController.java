package fr.amu.iut.cc3;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.Integer.*;

public class ToileController implements Initializable {

    private static int rayonCercleExterieur = 200;
    private static int angleEnDegre = 60;
    private static int angleDepart = 90;
    private static int noteMaximale = 20;

    @FXML
    TextField comp1;
    @FXML
    TextField comp2;
    @FXML
    TextField comp3;
    @FXML
    TextField comp4;
    @FXML
    TextField comp5;
    @FXML
    TextField comp6;
    @FXML
    Button tracer;
    @FXML
    Button vider;

    @FXML
    Circle c1;
    @FXML
    Circle c2;
    @FXML
    Circle c3;
    @FXML
    Circle c4;
    @FXML
    Circle c5;
    @FXML
    Circle c6;
    @FXML
    Label erreur;
    @FXML
    Pane pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tracer.addEventHandler(MouseEvent.MOUSE_CLICKED, actionEvent -> {
            placePointEtLigne();
        });

        vider.addEventHandler(MouseEvent.MOUSE_CLICKED, actionEvent -> {
            effaceLigne();
        });
    }


    int getXRadarChart(double value, int axe ){
        return (int) (rayonCercleExterieur + Math.cos(Math.toRadians(angleDepart - (axe-1)  * angleEnDegre)) * rayonCercleExterieur
                *  (value / noteMaximale));
    }


    int getYRadarChart(double value, int axe ){
        return (int) (rayonCercleExterieur - Math.sin(Math.toRadians(angleDepart - (axe-1)  * angleEnDegre)) * rayonCercleExterieur
                *  (value / noteMaximale));
    }





    Line ligne12 = new Line(c1.getCenterX(), c1.getCenterY(), c2.getCenterX(), c2.getCenterY());
    Line ligne23 = new Line(c2.getCenterX(), c2.getCenterY(), c3.getCenterX(), c3.getCenterY());
    Line ligne34 = new Line(c3.getCenterX(), c3.getCenterY(), c4.getCenterX(), c4.getCenterY());
    Line ligne45 = new Line(c4.getCenterX(), c4.getCenterY(), c5.getCenterX(), c5.getCenterY());
    Line ligne56 = new Line(c5.getCenterX(), c5.getCenterY(), c6.getCenterX(), c6.getCenterY());
    Line ligne61 = new Line(c6.getCenterX(), c6.getCenterY(), c1.getCenterX(), c1.getCenterY());

    public void placePointEtLigne() {

        int valC1 = parseInt(comp1.getText());
        int valC2 = parseInt(comp2.getText());
        int valC3 = parseInt(comp3.getText());
        int valC4 = parseInt(comp4.getText());
        int valC5 = parseInt(comp5.getText());
        int valC6 = parseInt(comp6.getText());

        if(valC1>noteMaximale||valC2>noteMaximale||valC3>noteMaximale||valC4>noteMaximale||valC5>noteMaximale||valC6>noteMaximale) {
            erreur.setTextFill(Color.color(1, 0, 0));
        }

        c1.setCenterX(getXRadarChart(valC1, 1));
        c1.setCenterY(getYRadarChart(valC1, 1));

        c2.setCenterX(getXRadarChart(valC2, 2));
        c2.setCenterY(getYRadarChart(valC2, 2));

        c3.setCenterX(getXRadarChart(valC3, 3));
        c3.setCenterY(getYRadarChart(valC3, 3));

        c4.setCenterX(getXRadarChart(valC4, 4));
        c4.setCenterY(getYRadarChart(valC4, 4));

        c5.setCenterX(getXRadarChart(valC5, 5));
        c5.setCenterY(getYRadarChart(valC5, 5));

        c6.setCenterX(getXRadarChart(valC6, 6));
        c6.setCenterY(getYRadarChart(valC6, 6));

        pane.getChildren().addAll(ligne12, ligne23, ligne34, ligne45, ligne56, ligne61, c1, c2, c3, c4, c5, c6);
    }


    public void effaceLigne() {
        pane.getChildren().removeAll(ligne12, ligne23, ligne34, ligne45, ligne56, ligne61);
    }
}
