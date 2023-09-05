package com.example.lr1_kg;

import com.example.lr1_kg.models.Coordinates;
import com.example.lr1_kg.models.Lines;
import com.example.lr1_kg.models.MatrixTransforn;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.util.Duration;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Controller {
    @FXML
    private Canvas canvas;
    @FXML
    private RadioButton radioButtonX;
    @FXML
    private RadioButton radioButtonY;
    @FXML
    private RadioButton radioButtonZ;

    //для провреки нажатия кнопки замедления
    private boolean isPressed=false;

    //список линйи для объекта
    private List<Lines> linesList = new ArrayList<>();

    private GraphicsContext graphicsContext;
    private MatrixTransforn matrixTransforn;

    private ToggleGroup group = new ToggleGroup();

    private Timeline timeline;

    //угол
    private double corner =0;
    //коефициент для замедления
    private double koef =1;

    public Controller() throws IOException {
        addLinesFromFile();
        matrixTransforn=new MatrixTransforn();
    }

    //получение координат из файла
    private  void addLinesFromFile() throws IOException {
        FileReader fr= new FileReader("C:\\Users\\ASUS\\Desktop\\lr1_kg\\src\\main\\java\\com\\example\\lr1_kg\\coordinates_lines.txt");
        Scanner scan = new Scanner(fr);
        while (scan.hasNextLine()) {
           String line = scan.nextLine();
           String[] point = line.split(" ");
           String[] coordinateStart = point[0].split(",");
           String[]coordinateEnd = point[1].split(",");
           Coordinates start = new Coordinates(Double.parseDouble(coordinateStart[0]), Double.parseDouble(coordinateStart[1])
                   ,Double.parseDouble(coordinateStart[2]));
           Coordinates end = new Coordinates(Double.parseDouble(coordinateEnd[0]),Double.parseDouble(coordinateEnd[1]),
                   Double.parseDouble(coordinateEnd[2]));
           Lines lines = new Lines(start,end);
           linesList.add(lines);

        }

        fr.close();
    }


    @FXML
    public void initialize()
    {
        graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.translate(canvas.getWidth()/2,canvas.getHeight()/2);
        drawAxexXYZ();
        drawShapes();
        radioButtonX.setToggleGroup(group);
        radioButtonY.setToggleGroup(group);
        radioButtonZ.setToggleGroup(group);
        //drawAxexXYZ(graphicsContext);
    }


    public void clearCanvas() {

        graphicsContext.clearRect(-canvas.getWidth() / 2,
                -canvas.getHeight() / 2,
                canvas.getWidth(),
                canvas.getHeight());
    }



    private void drawCanvas()
    {
        clearCanvas();
        drawAxexXYZ();
        drawShapes();
    }


    @FXML
    protected void btnTransformXpoz(ActionEvent actionEvent) throws URISyntaxException {
        for(Lines line: linesList)
        {

            line.getStart().multiply(matrixTransforn.transform(10,0,0 ));
            line.getEnd().multiply(matrixTransforn.transform(10,0,0));
        }

    drawCanvas();
    }
    @FXML
    protected void btnTransformXneg(ActionEvent actionEvent) throws URISyntaxException {
        for(Lines line: linesList)
        {

            line.getStart().multiply(matrixTransforn.transform(-10,0,0));
            line.getStart().multiply(matrixTransforn.transform(-10,0,0));
        }

        drawCanvas();
    }
    @FXML
    protected void btnTransformYpoz(ActionEvent actionEvent) throws URISyntaxException {
        for(Lines line: linesList)
        {

            line.getStart().multiply(matrixTransforn.transform(0,10,0));
            line.getEnd().multiply(matrixTransforn.transform(0,10,0));
        }

        drawCanvas();
    }
    @FXML
    protected void btnTransformYneg(ActionEvent actionEvent) throws URISyntaxException {
        for(Lines line: linesList)
        {

            line.getStart().multiply(matrixTransforn.transform(0,-10,0));
            line.getEnd().multiply(matrixTransforn.transform(0,-10,0));
        }

        drawCanvas();
    }
    @FXML
    protected void btnTransformZpoz(ActionEvent actionEvent) throws URISyntaxException {
        for(Lines line: linesList)
        {

            line.getStart().multiply(matrixTransforn.transform(0,0,10));
            line.getEnd().multiply(matrixTransforn.transform(0,0,10));
        }

        drawCanvas();
    }
    @FXML
    protected void btnTransformZneg(ActionEvent actionEvent) throws URISyntaxException {
        for(Lines line: linesList)
        {

            line.getStart().multiply(matrixTransforn.transform(0,0,-10));
            line.getEnd().multiply(matrixTransforn.transform(0,0,-10));
        }

        drawCanvas();
    }
    @FXML
    protected void btnDilationXincr(ActionEvent actionEvent) throws URISyntaxException {
        for(Lines line: linesList)
        {

            line.getStart().multiply(matrixTransforn.dilation(1.5,1,1));
            line.getEnd().multiply(matrixTransforn.dilation(1.5,1,1));
        }

        drawCanvas();
    }
    @FXML
    protected void btnDilationXdecr(ActionEvent actionEvent) throws URISyntaxException {
        for(Lines line: linesList)
        {

            line.getStart().multiply(matrixTransforn.dilation(0.6,1,1));
            line.getEnd().multiply(matrixTransforn.dilation(0.6,1,1));
        }

        drawCanvas();
    }
    @FXML
    protected void btnDilationYincr(ActionEvent actionEvent) throws URISyntaxException {
        for(Lines line: linesList)
        {

            line.getStart().multiply(matrixTransforn.dilation(1,1.5,1));
            line.getEnd().multiply(matrixTransforn.dilation(1,1.5,1));
        }

        drawCanvas();
    }
    @FXML
    protected void btnDilationYdecr(ActionEvent actionEvent) throws URISyntaxException {
        for(Lines line: linesList)
        {

            line.getStart().multiply(matrixTransforn.dilation(1,0.6,1));
            line.getEnd().multiply(matrixTransforn.dilation(1,0.6,1));
        }

        drawCanvas();
    }
    @FXML
    protected void btnDilationZincr(ActionEvent actionEvent) throws URISyntaxException {
        for(Lines line: linesList)
        {

            line.getStart().multiply(matrixTransforn.dilation(1,1,1.5));
            line.getEnd().multiply(matrixTransforn.dilation(1,1,1.5));
        }

        drawCanvas();
    }
    @FXML
    protected void btnDilationZdeccr(ActionEvent actionEvent) throws URISyntaxException {
        for(Lines line: linesList)
        {

            line.getStart().multiply(matrixTransforn.dilation(1,1,0.6));
            line.getEnd().multiply(matrixTransforn.dilation(1,1,0.6));
        }

        drawCanvas();
    }
    @FXML
    protected void btnRotationXpoz(ActionEvent actionEvent) throws URISyntaxException {
        for(Lines line: linesList)
        {

            line.getStart().multiply(matrixTransforn.rotationX(Math.toRadians(10)));
            line.getEnd().multiply(matrixTransforn.rotationX(Math.toRadians(10)));
        }

        drawCanvas();
    }
    @FXML
    protected void btnRotationXneg(ActionEvent actionEvent) throws URISyntaxException {
        for(Lines line: linesList)
        {

            line.getStart().multiply(matrixTransforn.rotationX(Math.toRadians(-10)));
            line.getEnd().multiply(matrixTransforn.rotationX(Math.toRadians(-10)));
        }

        drawCanvas();
    }

    @FXML
    protected void btnRotationYnpoz(ActionEvent actionEvent) throws URISyntaxException {
        for(Lines line: linesList)
        {

            line.getStart().multiply(matrixTransforn.rotationY(Math.toRadians(10)));
            line.getEnd().multiply(matrixTransforn.rotationY(Math.toRadians(10)));
        }

        drawCanvas();
    }
    @FXML
    protected void btnRotationYneg(ActionEvent actionEvent) throws URISyntaxException {
        for(Lines line: linesList)
        {

            line.getStart().multiply(matrixTransforn.rotationY(Math.toRadians(-10)));
            line.getEnd().multiply(matrixTransforn.rotationY(Math.toRadians(-10)));
        }

        drawCanvas();
    }
    @FXML
    protected void btnRotationZpoz(ActionEvent actionEvent) throws URISyntaxException {
        for(Lines line: linesList)
        {

            line.getStart().multiply(matrixTransforn.rotationZ(Math.toRadians(10)));
            line.getEnd().multiply(matrixTransforn.rotationZ(Math.toRadians(10)));
        }

        drawCanvas();
    }
    @FXML
    protected void btnRotationZneg(ActionEvent actionEvent) throws URISyntaxException {
        for(Lines line: linesList)
        {

            line.getStart().multiply(matrixTransforn.rotationZ(Math.toRadians(-10)));
            line.getEnd().multiply(matrixTransforn.rotationZ(Math.toRadians(-10)));
        }

        drawCanvas();
    }
    @FXML
    protected void btnStartAnimation(ActionEvent actionEvent) throws URISyntaxException{
        animationStart();
    }
    @FXML
    protected void setBtnSlowed(ActionEvent actionEvent) throws URISyntaxException{
       isPressed=true;
    }

    @FXML
    protected void btnStopAnimation(ActionEvent actionEvent) throws URISyntaxException{
        radioButtonX.setSelected(false);
        radioButtonY.setSelected(false);
        radioButtonZ.setSelected(false);

        timeline.stop();
    }
    private void drawShapes()
    {
        graphicsContext.setStroke(Color.GREEN);
        graphicsContext.setLineWidth(2);

        for(Lines line : linesList)
        {
            graphicsContext.strokeLine(line.getStart().getX()-line.getStart().getZ()*0.5*Math.cos(Math.PI/4),
                    -line.getStart().getY()+line.getStart().getZ()*0.5*Math.sin(Math.PI/4),
                    line.getEnd().getX()-line.getEnd().getZ()*0.5*Math.cos(Math.PI/4),
                    -line.getEnd().getY()+line.getEnd().getZ()*0.5*Math.sin(Math.PI/4));
        }
    }
    private void drawAxexXYZ()
    {
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.setLineWidth(1);

        graphicsContext.strokeLine(0,0,350,0);//x
        graphicsContext.strokeLine(0,0,0,-350);//y
        graphicsContext.strokeLine(0,0,-350,350);//z

    }
    private void archimedeanSpiral(double coefx, double coefy, double coefz )
    {


        for (Lines lines:linesList) {
            lines.getStart().multiply(matrixTransforn.transform(
                    coefx,
                    coefy,
                    coefz
                    ));
            lines.getEnd().multiply(matrixTransforn.transform(
                    coefx,
                    coefy,
                    coefz
            ));
        }

    }

    private void animationStart()
    {

        timeline = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

             if(  radioButtonX.isSelected()==true){

                 archimedeanSpiral(0,corner*0.5*Math.sin(corner), corner*0.5*Math.cos(corner));
                }


                if(  radioButtonY.isSelected()==true) {
                    archimedeanSpiral(corner*0.5*Math.cos(corner), 0, corner*0.5*Math.sin(corner));
                }

                if(  radioButtonZ.isSelected()==true){

                   archimedeanSpiral(corner*0.5*Math.cos(corner), corner*0.5*Math.sin(corner) ,0);
                    }

                    corner+=0.1;
                    drawCanvas();

                timeline.setRate(0.5/koef);
                koef*=1.009;
                if(isPressed==true)
                {
                    koef*=5;
                    isPressed=false;
                }

               }

        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private  void animationStartVariant2()
    {
        timeline = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(  radioButtonX.isSelected()==true) {

                    for(Lines line: linesList)
                    {

                        line.getStart().multiply(matrixTransforn.rotationX(Math.toRadians(10)));
                        line.getEnd().multiply(matrixTransforn.rotationX(Math.toRadians(10)));
                    }
                   for(Lines line: linesList) {

                        line.getStart().multiply(matrixTransforn.transform(1, 0, 0));
                        line.getEnd().multiply(matrixTransforn.transform(1, 0, 0));
                    }


                    }

                drawCanvas();
                }


        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        }
}