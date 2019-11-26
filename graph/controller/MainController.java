package controller;

import java.util.ArrayList;
import java.util.List;

import graphs.Edge;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import model.Node;
import model.Program;


public class MainController {
	
    @FXML
    private TextArea textAreaMice;

    @FXML
    private Pane paneMice;

    @FXML
    private TextArea textAreaDark;

    @FXML
    private Pane paneDark;
    
    private Program programMice;
    
    private Program programDark;

    @FXML
    void dragDark(MouseEvent event) {

    }

    @FXML
    void dragMice(MouseEvent event) {
    	
    }

    @FXML
    void readDark(ActionEvent event) {

    }

    @FXML
    void readMice(ActionEvent event) {

    }

    @FXML
    void solveDark(ActionEvent event) {
    	
    	
    	
    }

    @FXML
    void solveMice(ActionEvent event) {
    	
    	
    	String[] text = textAreaMice.getText().split("\n");
    	int C = Integer.parseInt(text[0]);//cantidad de grafos
    	programMice = new Program(C);
    	int cont = 2;
    	while(C>0) {
    		
    		int N = Integer.parseInt(text[cont++]);//numero de celdas
        	int E = Integer.parseInt(text[cont++]);//celda de salida
        	int T = Integer.parseInt(text[cont++]);//tiempo
        	int M = Integer.parseInt(text[cont++]);//cantidad de aristas
        	programMice.addGraph(N, E, T);
        	
        	while(M>0) {
        		String[] edge = text[cont].split(" ");
        		int A = Integer.parseInt(edge[0]);
        		int B = Integer.parseInt(edge[1]);
        		int W = Integer.parseInt(edge[2]);
        		programMice.addEdge(A, B, W);
        		M--;
        		cont++;
        	}
        	int success = programMice.solveMice(C);
        	printGraph(paneMice, programMice, C, N);
        	cont++;
        	C--;
        	System.out.println(N+" "+E+" "+T+" "+M);
    	}
    	
    	
    }
    
    private void printGraph(Pane pane, Program program, int C, int v) {
    	
    	pane.getChildren().clear();
    	int p = program.getGraphs().size()-C;
    	List<Circle> list1 = new ArrayList<Circle>();
    	List<Label> list2 = new ArrayList<Label>();
       	for (int i = 0; i < v; i++) {
       		
       		double[] pos = getPos(p, i);
       		double posX = pos[0];
       		double posY = pos[1];
       		boolean t = pane.contains(posX, posY);
       		System.out.println(i+" "+posX+" "+posY);
			while(!t) {
				pos = getPos(p, i);
	       		posX = pos[0];
	       		posY = pos[1];
	       		t = pane.contains(posX, posY);
			}
			int name = program.getGraphs().get(p).getAdjList().getVertices().get(i).getValue().getName();
			Circle node = new Circle();
			node.setCenterX(posX);
			node.setCenterY(posY);
			node.setRadius(10);
			int type = program.getGraphs().get(p).getAdjList().getVertices().get(i).getValue().getType();
			if(type == Node.EXIT) {
				node.setFill(Color.GREENYELLOW);
			}else if(type == Node.LEAVE){
				node.setFill(Color.YELLOW);
			}else {
				node.setFill(Color.LIGHTBLUE);
			}
			
			node.setId(name+"");
			Label text = new Label(name+"");
			text.setLayoutX(posX);
			text.setLayoutY(posY);
			list1.add(node);
			list2.add(text);
		}
       	
       	List<Edge<Node>> edges = program.getGraphs().get(p).getAdjList().getEdges();
       	for(int i = 0; i < edges.size(); i++) {
       		double startX = edges.get(i).getSource().getValue().getX();
       		double startY = edges.get(i).getSource().getValue().getY();
       		double endX = edges.get(i).getDestination().getValue().getX();
       		double endY = edges.get(i).getDestination().getValue().getY();
       		double weights = edges.get(i).getWeight();
       		Line line = new Line();
       		line.setStartX(startX);
       		line.setStartY(startY);
       		line.setEndX(endX);
       		line.setEndY(endY);
       		Label text = new Label(weights+"");
       		text.setLayoutX(Math.abs((startX+endX)/2));
       		text.setLayoutY(Math.abs((startY+endY)/2));
       		pane.getChildren().add(line);
       		pane.getChildren().add(text);
       	}
       	pane.getChildren().addAll(list1);
		pane.getChildren().addAll(list2);
    }
    
    public double[] getPos(int p, int i) {
    	double[] arr = new double[2];
    	programMice.getGraphs().get(p).getAdjList().getVertices().get(i).getValue().generatePos();
    	arr[0] = programMice.getGraphs().get(p).getAdjList().getVertices().get(i).getValue().getX();
    	arr[1] = programMice.getGraphs().get(p).getAdjList().getVertices().get(i).getValue().getY();
    	return arr;
    }
    
    @FXML
	void initialize() {
		paneMice.setStyle("-fx-background-color: gray;");
	    paneMice.setMaxHeight(550);
	    paneMice.setMaxWidth(800);
	    paneDark.setStyle("-fx-background-color: gray;");
	    paneDark.setMaxHeight(550);
	    paneDark.setMaxWidth(800);
	}

}
