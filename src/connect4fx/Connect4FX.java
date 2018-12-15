/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4fx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author esteb
 */
public class Connect4FX extends Application {
    
    Stage window;
    Button nuevoJuego, volverInicio, continuarJuego, salirInicio, salirJuego, reiniciarJuego, intentarJugador1, intentarJugador2;
    Scene inicio, juego;
    GridPane connect4 = new GridPane();
    boolean buenIntento1, buenIntento2;
    public static int indiceIntento1, indiceIntento2;
    
    public static BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
    //Declaracion de salida
    public static PrintStream imprimir = System.out;
    
    public static void main(String[] args)throws IOException {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("4 EN LINEA");
        window.setOnCloseRequest(e -> {
            e.consume();
            cerrarPrograma();
                    });
        
        HBox tituloInicio = new HBox();
        
        Label titulo4 = new Label("4 ");
        titulo4.setStyle("-fx-font-size:65px; -fx-font-weight:bolder; -fx-text-fill: #07507F;");
        titulo4.setPadding(new Insets(0, 0, 0, 13));
        
        Label tituloJuego = new Label("EN LINEA");
        tituloJuego.setStyle("-fx-font-size:50px; -fx-font-weight:bold; -fx-text-fill: #07507F;");
        tituloJuego.setPadding(new Insets(15, 0, 0, 0));
        
        tituloInicio.getChildren().addAll(titulo4, tituloJuego);
        
        BorderPane layout1 = new BorderPane();
        layout1.setStyle("-fx-background-color: #2980b9;");
        
        nuevoJuego = new Button();
        nuevoJuego.setText("Nuevo Juego");
        nuevoJuego.setOnAction(e -> {
            window.setScene(juego);
            generarTablero();
            GUIRutinas.reiniciarMatriz();
        });
        nuevoJuego.setMaxWidth(Double.MAX_VALUE);
        
        continuarJuego = new Button();
        continuarJuego.setText("Continuar Juego");
        continuarJuego.setOnAction(e -> window.setScene(juego));
        continuarJuego.setMaxWidth(Double.MAX_VALUE);
        
        salirInicio = new Button();
        salirInicio.setText("Salir");
        salirInicio.setOnAction(e -> cerrarPrograma());
        salirInicio.setMaxWidth(Double.MAX_VALUE);
        
        VBox menuInicio = new VBox(20);
        menuInicio.getChildren().addAll(tituloInicio, nuevoJuego, continuarJuego, salirInicio);
        
        HBox menuTop = new HBox();
        VBox menuRight = new VBox();
        VBox menuLeft = new VBox();
        HBox menuBottom = new HBox();
        
        
        menuTop.setMinHeight(100.0);
        menuBottom.setMinHeight(90.0);
        menuLeft.setMinWidth(230.0);
        menuRight.setMinWidth(230.0);
        
        layout1.setCenter(menuInicio);
        layout1.setTop(menuTop);
        layout1.setLeft(menuLeft);
        layout1.setRight(menuRight);
        layout1.setBottom(menuBottom);

        VBox ladoIzquierdo = new VBox(10);
        
        Label jugador1 = new Label("Jugador 1:");
        jugador1.setStyle("-fx-font-size:20px; -fx-text-fill: #C0A120;");
        
        TextField intento1 = new TextField();
        intento1.setPromptText("Escoger columna 1-7");
        
        intentarJugador1 = new Button();
        intentarJugador1.setText("Jugar!");
        intentarJugador1.setMaxWidth(Double.MAX_VALUE);
        
        Label intentosJugador1 = new Label("Fichas usadas: "+ GUIRutinas.contFichas1);
        intentosJugador1.setStyle("-fx-font-size:15px;  -fx-text-fill: #C0A120;");
        intentosJugador1.setPadding(new Insets(70, 0, 0, 0));
        
        ladoIzquierdo.getChildren().addAll(jugador1, intento1, intentarJugador1, intentosJugador1);
        ladoIzquierdo.setPadding(new Insets(10, 15, 15, 15));
        ladoIzquierdo.setStyle("-fx-background-color: #FFE26C; -fx-margin: 10px");
        
        //Al darle click al boton del primer jugador
        intentarJugador1.setOnAction(e -> {
            //Manda el valor que se encuentra en el input del primer jugador a ValidarIntento
            buenIntento1 = validarIntento(intento1, (intento1.getText()));
            //Si el intento es valido
            if (buenIntento1){
                int[] coordenadas;
                boolean ganador = false;
                indiceIntento1 = Integer.valueOf(intento1.getText());
                //Manda el valor del intento a primerJugador y obtiene 
                //las coordenadas finales de la posicion de la ficha
                coordenadas = primerJugador(indiceIntento1);
                //Prueba si la ficha completa una linea de 4
                ganador = GUIRutinas.ganoJuego(coordenadas);
                intento1.setText("");
                mostrarFicha(coordenadas);
                //Muestra la cantidad de intentos que ha hecho el jugador
                intentosJugador1.setText("Fichas usadas: "+ GUIRutinas.contFichas1);
                //Si gano mostrar Alerta
                if(ganador){
                    AlertBox.display("JUGADOR 1", "Ha ganado el juego!");
                    generarTablero();
                    GUIRutinas.reiniciarMatriz();
                }
            }
        });
        
        VBox ladoDerecho = new VBox(10);
        
        Label jugador2 = new Label("Jugador 2:");
        jugador2.setStyle("-fx-font-size:20px; -fx-text-fill: #7C0C00;");
        
        TextField intento2 = new TextField();
        intento2.setPromptText("Escoger columna 1-7");
        
        intentarJugador2 = new Button();
        intentarJugador2.setText("Jugar!");
        intentarJugador2.setMaxWidth(Double.MAX_VALUE);
        
        Label intentosJugador2 = new Label("Fichas usadas: "+ GUIRutinas.contFichas2);
        intentosJugador2.setStyle("-fx-font-size:15px; -fx-text-fill: #7C0C00;");
        intentosJugador2.setPadding(new Insets(70, 0, 0, 0));
        
        ladoDerecho.getChildren().addAll(jugador2, intento2, intentarJugador2, intentosJugador2);
        ladoDerecho.setPadding(new Insets(10, 15, 15, 15));
        ladoDerecho.setStyle("-fx-background-color: #C92918; -fx-margin: 10px");
        
        //Al darle click al boton del segundo jugador
        intentarJugador2.setOnAction(e -> {
            //Manda el valor que se encuentra en el input del segundo jugador a ValidarIntento
            buenIntento2 = validarIntento(intento2, (intento2.getText()));
            //Si el intento es valido
            if (buenIntento2){
                int[] coordenadas;
                boolean ganador = false;
                indiceIntento2 = Integer.valueOf(intento2.getText());
                //Manda el valor del intento a segundoJugador y obtiene 
                //las coordenadas finales de la posicion de la ficha
                coordenadas = segundoJugador(indiceIntento2);
                //Prueba si la ficha completa una linea de 4
                ganador = GUIRutinas.ganoJuego(coordenadas);
                intento2.setText("");
                mostrarFicha(coordenadas);
                //Muestra la cantidad de intentos que ha hecho el jugador
                intentosJugador2.setText("Fichas usadas: "+ GUIRutinas.contFichas2);
                //Si gano mostrar Alerta
                if(ganador){
                    AlertBox.display("JUGADOR 2", "Ha ganado el juego!");
                    generarTablero();
                    GUIRutinas.reiniciarMatriz();
                }
            }
        });
        
        
        volverInicio = new Button();
        volverInicio.setText("Inicio");
        volverInicio.setOnAction(e -> {
            window.setScene(inicio);
            intento1.setText("");
            intento2.setText("");
        });
        volverInicio.setMaxSize(80.0, Double.MAX_VALUE);
        
        salirJuego = new Button();
        salirJuego.setText("Salir");
        salirJuego.setOnAction(e -> cerrarPrograma());
        salirJuego.setMaxSize(80.0, Double.MAX_VALUE);
        
        reiniciarJuego = new Button();
        reiniciarJuego.setText("Reiniciar");
        reiniciarJuego.setOnAction(e -> {
            generarTablero();
            GUIRutinas.reiniciarMatriz();
        });
        reiniciarJuego.setMaxSize(80.0, Double.MAX_VALUE);
        
        Label juegoLabel = new Label("4 EN LINEA");
        juegoLabel.setStyle("-fx-font-size:30px; -fx-font-weight:bold; -fx-text-fill: #07507F;");
        juegoLabel.setPadding(new Insets(0, 0, 0, 85));
        
        HBox juegoMenu = new HBox(20);
        juegoMenu.setPadding(new Insets(20, 10, 10, 10));
        juegoMenu.getChildren().addAll(volverInicio, reiniciarJuego, salirJuego, juegoLabel);
        
        BorderPane layout2 = new BorderPane();
        layout2.setTop(juegoMenu);
        layout2.setLeft(ladoIzquierdo);
        layout2.setRight(ladoDerecho);
        
        BorderPane.setMargin(ladoIzquierdo,new Insets(5, 5, 5, 5));
        BorderPane.setMargin(ladoDerecho,new Insets(5, 5, 5, 5));
        
        VBox tableroPrincipal = new VBox(5);
        Label numColumnas = new Label("  1     2     3     4     5     6     7");
        numColumnas.setStyle("-fx-font-size:29px; -fx-font-weight:bold; -fx-text-fill: #07507F;");
        tableroPrincipal.getChildren().addAll(numColumnas, connect4);
        tableroPrincipal.setStyle("-fx-background-color: #2980b9;");
        generarTablero();
                
        layout2.setCenter(tableroPrincipal);
        BorderPane.setMargin(connect4,new Insets(5, 0, 5, 0));
        
        inicio = new Scene(layout1, 770, 465);
        juego = new Scene(layout2, 770, 465);
        window.setScene(inicio);
        window.show();
        
    }
    
    public void generarTablero(){
        int height = 6;
        int width = 7;  
        connect4.getChildren().clear();
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){    
                //Se crea un String y se le asigna mostrarFicha
                //Enviandole a "x" y "y" como fila y columna
                
                // Create a new Circle in each Iteration
                Circle circle = new Circle(x, y, 25);
                //Se hace un if para saber el contenido del String 
                //Y con eso se deterina el tipo de setFill
                
                circle.setFill(Color.WHITE);
                GridPane.setMargin(circle, new Insets(3, 3, 3, 3));

                // Iterate the Index using the loops
                connect4.setRowIndex(circle,y);
                connect4.setColumnIndex(circle,x);    
                connect4.getChildren().add(circle);
            }
        }
        
    }
    
    public void mostrarFicha(int[] pcoordenadas){
        int x = pcoordenadas[0];
        int y = pcoordenadas[1];
        int jugador = pcoordenadas[2];
        
        if(jugador == 1){
            //Prueba para agregar circulo amarillo
            Circle circle = new Circle(x, y, 25);
            circle.setFill(Color.YELLOW);
            GridPane.setMargin(circle, new Insets(3, 3, 3, 3));
            connect4.setRowIndex(circle, x);
            connect4.setColumnIndex(circle, y);    
            connect4.getChildren().add(circle);
        }else{
            //Prueba para agregar circulo rojo
            Circle circle = new Circle(x, y, 25);
            circle.setFill(Color.RED);
            GridPane.setMargin(circle, new Insets(3, 3, 3, 3));
            connect4.setRowIndex(circle, x);
            connect4.setColumnIndex(circle, y);    
            connect4.getChildren().add(circle);    
        }
    }
    
    private void cerrarPrograma(){
        boolean salir = ConfirmBox.display("Salir", "Seguro que desea salir?");
            if(salir){
            window.close();
            }    
    }
    private boolean validarIntento(TextField input, String message){
        boolean buenIntento;
        try{
            int intento = Integer.parseInt(message);
            if (intento < 1 || intento > 7){
                input.setText("Error: intento inválido");
                buenIntento = false;
            }else{
                buenIntento = true;
            }
        }catch(NumberFormatException e){   
            input.setText("Error: intento inválido");
            buenIntento = false;           
        }
        return buenIntento;
    }

    public static int[] primerJugador(int pindice){
        int contFichas;
        int[] coordenadas;
        //Recibe la cantidad de intentos que ha hecho el jugador
        contFichas = GUIRutinas.fichasJugador(1);
        imprimir.println("JUGADOR1 : Digite la columna en la que desea soltar una ficha amarilla, ha usado "+ contFichas +" fichas.");
        //Manda el intento del usuario y obtiene las coordenadas finales de este
        coordenadas = GUIRutinas.intentarPrimerJugador((pindice - 1));
        //Muestra la matriz
        mostrarMatriz(GUIRutinas.matrizConnect4);  
        return coordenadas;
    }

    public static int[] segundoJugador(int pindice){
        int contFichas;
        int[] coordenadas;
        //Recibe la cantidad de intentos que ha hecho el jugador
        contFichas = GUIRutinas.fichasJugador(2);
        imprimir.println("JUGADOR2 : Digite la columna en la que desea soltar una ficha roja, ha usado "+ contFichas +" fichas.");
        //Manda el intento del usuario y obtiene las coordenadas finales de este
        coordenadas = GUIRutinas.intentarSegundoJugador((pindice - 1));
        //Muestra la matriz
        mostrarMatriz(GUIRutinas.matrizConnect4);  
        return coordenadas;
    }

    public static void mostrarMatriz(char[][] pmatriz) {
        imprimir.println("Las fichas se encuentran de esta manera");
        imprimir.println("1 2 3 4 5 6 7 ");
        for (int x = 0; x < 6; x++) {
            for (int i = 0; i < 6; i++) {
                imprimir.print(pmatriz[x][i] + " ");
            }
            imprimir.println(pmatriz[x][6]);
        }
    }
}

