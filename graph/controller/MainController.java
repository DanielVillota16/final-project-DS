package controller;

import java.util.ArrayList;
import java.util.List;

import graphs.Edge;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    
    @FXML
    private Label answerDark;
    
    @FXML
    private Label answerMice;
    
    private Program programMice;
    
    private Program programDark;
    
    private int pageMice;
    
    private int pageDark;

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
    void ClearMice(ActionEvent event) {
    	pageMice = 0;
    	programMice = new Program();
    	paneMice.getChildren().clear();
    }
    
    @FXML
    void ClearDark(ActionEvent event) {
    	pageDark = 0;
    	programDark = new Program();
    	paneDark.getChildren().clear();
    }

    @FXML
    void solveDark(ActionEvent event) {
    	try {
    	int cont = 0;
    	String[] text = textAreaDark.getText().split("\n");
    	String[] line = text[cont++].split(" ");
    	programDark = new Program();
    	int A = Integer.parseInt(line[0]);
    	int B = Integer.parseInt(line[1]);
    	while(A!=0 && B!=0) {
    		programDark.addGraph(A);
    		for(int i = 0; i < B; i++) {
    			line = text[i+1].split(" ");
    			int X = Integer.parseInt(line[0]);
    			int Y = Integer.parseInt(line[1]);
    			int Z = Integer.parseInt(line[2]);
    			programDark.addEdge(X, Y, Z, 0);
    			cont++;
    		}
    		programDark.solveDark();
    		line = text[cont++].split(" ");
        	A = Integer.parseInt(line[0]);
        	B = Integer.parseInt(line[1]);
    	}
    	printGraph(paneDark, programDark, pageDark, answerDark);
    	}catch(Exception e) {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Entrada no valida, verifique la entrada de acuerdo al fromato definido");
			alert.show();
    	}
    }

    @FXML
    void solveMice(ActionEvent event) {
    	try {
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
        		programMice.addEdge(A, B, W, 1);
        		M--;
        		cont++;
        	}
        	programMice.solveMice();
        	cont++;
        	C--;
        	System.out.println(N+" "+E+" "+T+" "+M);
    	}
    	printGraph(paneMice, programMice, pageMice, answerMice);
    	}catch(Exception e) {
    		e.printStackTrace();
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Entrada no valida, verifique la entrada de acuerdo al fromato definido");
			alert.show();
    	}
    	
    	
    }
    
    @FXML
    void afterDark(ActionEvent event) {
    	if(pageDark+1 < programDark.getGraphs().size()) {
    		pageDark++;
    		printGraph(paneDark, programDark, pageDark, answerDark);
    	}
    }

    @FXML
    void afterMice(ActionEvent event) {
    	if(pageMice+1 < programMice.getGraphs().size()) {
    		pageMice++;
    		printGraph(paneMice, programMice, pageMice, answerMice);
    	}
    }

    @FXML
    void beforeDark(ActionEvent event) {
    	if(pageDark-1 > -1) {
    		pageDark--;
    		printGraph(paneDark, programDark, pageDark, answerDark);
    	}
    }

    @FXML
    void beforeMice(ActionEvent event) {
    	if(pageMice-1 > -1) {
    		pageMice--;
    		printGraph(paneMice, programMice, pageMice, answerMice);
    	}
    }
    
    private void printGraph(Pane pane, Program program, int g, Label answer) {
    	
    	pane.getChildren().clear();
    	List<Circle> list1 = new ArrayList<Circle>();
    	List<Label> list2 = new ArrayList<Label>();
    	int v = program.getGraphs().get(g).getAdjMatrix().getVertices().size();
       	for (int i = 0; i < v; i++) {
       		
       		double[] pos = getPos(program, g, i);
       		double posX = pos[0];
       		double posY = pos[1];
       		boolean t = pane.contains(posX, posY);
       		System.out.println(i+" "+posX+" "+posY);
			while(!t) {
				program.getGraphs().get(g).getAdjList().getVertices().get(i).getValue().generatePos();
				pos = getPos(program, g, i);
	       		posX = pos[0];
	       		posY = pos[1];
	       		t = pane.contains(posX, posY);
			}
			int name = program.getGraphs().get(g).getAdjList().getVertices().get(i).getValue().getName();
			Circle node = new Circle();
			node.setCenterX(posX);
			node.setCenterY(posY);
			node.setRadius(20);
			int type = program.getGraphs().get(g).getAdjList().getVertices().get(i).getValue().getType();
			if(type == Node.EXIT) {
				node.setFill(Color.GREENYELLOW);
			}else if(type == Node.LEAVE){
				node.setFill(Color.YELLOW);
			}else {
				node.setFill(Color.LIGHTBLUE);
			}
			
			node.setId(name+"");
			Label text = new Label(name+"");
			text.setLayoutX(posX-3);
			text.setLayoutY(posY-3);
			list1.add(node);
			list2.add(text);
		}
       	
       	List<Edge<Node>> edges = program.getGraphs().get(g).getAdjList().getEdges();
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
       	answer.setText(program.getGraphs().get(g).getAnswer()+"");
       	pane.getChildren().addAll(list1);
		pane.getChildren().addAll(list2);
		pane.getChildren().add(answer);
    }
    
    public double[] getPos(Program program, int p, int i) {
    	double[] arr = new double[2];
    	arr[0] = program.getGraphs().get(p).getAdjList().getVertices().get(i).getValue().getX();
    	arr[1] = program.getGraphs().get(p).getAdjList().getVertices().get(i).getValue().getY();
    	return arr;
    }
    
    @FXML
	void initialize() {
		paneMice.setStyle("-fx-background-color: lightgray;");
	    paneMice.setMaxHeight(550);
	    paneMice.setMaxWidth(800);
	    paneDark.setStyle("-fx-background-color: lightgray;");
	    paneDark.setMaxHeight(550);
	    paneDark.setMaxWidth(800);
	    pageMice = 0;
	    pageDark= 0;
	}

}
