package com.example.minesweeper;

import java.util.Random;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.input.*;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
public class Minesweeper extends Application {
    /**
     * Metodo que crea la interfaz y 
     * contiene los elementos de la interfaz
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        /**
         * Se establece el tipo de panel que cargara los elementos de la interfaz
         */
        StackPane layout = new StackPane();
        /**
         * Se establece el tamaño de la ventana 
         * Se agrega el titulo de la ventan
         * Se otorga visibilidad a la ventana
         */
        Scene scene = new Scene(layout, 500, 510);
        String colorFondo = "-fx-background-color: #008000;";
        layout.setStyle(colorFondo);
        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(scene);
        primaryStage.show();
        /**
         * Se crea la etiqueta que indicara las banderas
         * o posibles minas
         */
        Label PosibleMines = new Label();
        PosibleMines.setText("Posible Mines");
        PosibleMines.setTranslateX(-45);
        PosibleMines.setTranslateY(190);
        PosibleMines.setAlignment(Pos.CENTER);
        PosibleMines.setStyle("-fx-background-color: #DADAD7;");
        PosibleMines.setFont(Font.font("Impact"));
        PosibleMines.setMaxSize(90, 30);
        /**
         * Se crea la etiqueta que contenera las banderas
         * o posibles minas
         */
        Tablero.MinesCount = new Label();
        Tablero.MinesCount.setText("0");
        Tablero.MinesCount.setAlignment(Pos.CENTER);
        Tablero.MinesCount.setStyle("-fx-background-color: #DADAD7;");
        Tablero.MinesCount.setFont(Font.font("Impact"));
        Tablero.MinesCount.setMaxSize(90, 30);
        Tablero.MinesCount.setTranslateX(-45);
        Tablero.MinesCount.setTranslateY(210);
        /**
         * Se crea la etiqueta que indicara
         * el tiempo transcurrido
         */
        Label labeltiempo = new Label();
        labeltiempo.setText("Time");
        labeltiempo.setTranslateX(190);
        labeltiempo.setTranslateY(190);
        labeltiempo.setStyle("-fx-background-color: #DADAD7;");
        labeltiempo.setMaxSize(50, 30);
        labeltiempo.setFont(Font.font("Impact"));
        labeltiempo.setAlignment(Pos.CENTER);
        /**
         * Se crea la etiqueta que contendra
         * el tiempo transcurrido
         */
        Label Chronometerlbl = new Label();
        Chronometerlbl.setText("");
        Chronometerlbl.setTranslateX(190);
        Chronometerlbl.setTranslateY(210);
        Chronometerlbl.setAlignment(Pos.CENTER);
        Chronometerlbl.setStyle("-fx-background-color: #DADAD7;");
        Chronometerlbl.setFont(Font.font("Impact"));
        Chronometerlbl.setMaxSize(50, 30);

        /**
         * Se crea la etiqueta que indicara
         * el las condiciones de las sugerencias seguras
         */
        Label Advicelbl = new Label();
        Advicelbl.setText("");
        Advicelbl.setStyle("-fx-text-fill: black;");
        Advicelbl.setFont(Font.font("Impact"));
        Advicelbl.setTranslateX(0);
        Advicelbl.setTranslateY(245);
        /**
         * Se crea la etiqueta que indicara
         * el la cantidad de sugerencias seguras
         * disponibles
         */
        Tablero.CounterSuggestions = new Label();
        Tablero.CounterSuggestions.setText("0");
        Tablero.CounterSuggestions.setStyle("-fx-background-color: #DADAD7;");
        Tablero.CounterSuggestions.setFont(Font.font("Impact"));
        Tablero.CounterSuggestions.setTranslateX(155);
        Tablero.CounterSuggestions.setTranslateY(185);
        /**
         * Se crea un Cronometro que llevara la
         * cuenta de el tiempo transcurrido en el juego.
         * El limite de este sera de 59:59
         */
        Timer Chronometer = new Timer();
        TimerTask Time = new TimerTask() {
            /**
             * El metodo TimerTask ayudara a llevar la
             * cuenta del tiempo transcurrido
             */
            @Override
            public void run() {
                if (Tablero.countersec == 59) { // Limite de 59 sec = 1 min
                    Tablero.countermin++;// Se agrega un min
                    Tablero.countersec = -1;// El contador se reinicia
                }
                if (Tablero.Finish == false) {
                    if (Tablero.countermin==0) {
                        if (Tablero.countersec < 9) { // Si ha transcurrido menos de 9 seg
                            Platform.runLater(() -> Chronometerlbl.setText("00:0" + Integer.toString(Tablero.countersec))); // Se coloca el 9 en la etiqueta respectiva
                            Tablero.countersec++; // Se agrega un digito al contador
                        }
                        else { // Si ha transcurrido 10 seg
                            Platform.runLater(() -> Chronometerlbl.setText("00:" + Integer.toString(Tablero.countersec))); // Se coloca el 10 en la etiqueta respectiva
                            Tablero.countersec++; //Se agrega un digito al contador
                        }
                    }
                    else if (Tablero.countermin < 9) { // Si ha transcurrido  menos de 9 Min
                        if (Tablero.countersec < 9) { // Si ha transcurrido menos de 9 Min
                            Platform.runLater(() -> Chronometerlbl.setText("0" + Integer.toString(Tablero.countermin) + ":0" + Integer.toString(Tablero.countersec))); // Se coloca el 9 en la etiqueta respectiva
                            Tablero.countersec++; // Se agrega un digito al contador
                        }
                        else { // Si ha transcurrido 10 sec
                            Platform.runLater(() -> Chronometerlbl.setText("0" + Integer.toString(Tablero.countermin) + ":" + Integer.toString(Tablero.countersec))); // Se coloca el 10 en la etiqueta respectiva
                            Tablero.countersec++; //Se agrega un digito al contador
                        }
                    }
                    else { // Si ha transcurrido 10 seg
                        if (Tablero.countersec < 9) { // Si ha transcurrido menos de 9 seg
                            Platform.runLater(() -> Chronometerlbl.setText(Integer.toString(Tablero.countermin) + ":0" + Integer.toString(Tablero.countersec))); // Se coloca el 9 en la etiqueta respectiva
                            Tablero.countersec++; //Se agrega un digito al contador
                        }
                        else { // Si ha transcurrido 10 seg
                            Platform.runLater(() -> Chronometerlbl.setText(Integer.toString(Tablero.countermin) + ":" + Integer.toString(Tablero.countersec))); // Se coloca el 10 en la etiqueta respectiva
                            Tablero.countersec++; //Se agrega un digito al contador
                        }
                    }
                }
            }
        };
        Chronometer.scheduleAtFixedRate(Time, 0, 1000l); //El delay es de cero para una mayor precision en la cuenta de tiempo

        // Boton para iniciar control arduino
        /**
         * Se crea el boton que Reinicia de manera manual
         * el tablero, a su vez indicara el estado del jugador
         */
        Tablero.Resetbtn = new Button();
        Tablero.Resetbtn.setText("Alive");
        Tablero.Resetbtn.setTranslateX(200);
        Tablero.Resetbtn.setTranslateY(-220);
        Tablero.Resetbtn.setMaxSize(90, 50);
        Tablero.Resetbtn.setFont(Font.font("Impact"));
        Tablero.Resetbtn.setOnAction(e -> {
        /**
         * Al presionar el boton este hara
         * la llamada al metodo Restart
         */
        Restart();
    });
    /**
     * Se crea el boton para indicar el
     * inicio de la computadora en modo dummy
     */
        Button Dummybtn = new Button();
        Dummybtn.setText("DUMMY");
        Dummybtn.setStyle("-fx-border-color: #ff6600;-fx-border-width: 3");
        Dummybtn.setFont(Font.font("Impact"));
        Dummybtn.setTranslateX(-170);
        Dummybtn.setTranslateY(180);
        Dummybtn.setMaxSize(125, 10);
        Dummybtn.setOnAction(e -> {

        /**
         * Al presionar el boton este
         * inicializara en modo dummy
         */
        Computer.DummyInit = true;
        Computer.DummyMode();
    });
    /**
     * Se crea el boton para indicar el
     * inicio de la computadora en modo avanzado
     */
    Button Advancedbtn = new Button();
        Advancedbtn.setText("ADVANCED");
        Advancedbtn.setStyle("-fx-border-color: #000000;-fx-border-width: 3");
        Advancedbtn.setFont(Font.font("Impact"));
        Advancedbtn.setTranslateX(-170);
        Advancedbtn.setTranslateY(210);
        Advancedbtn.setMaxSize(125, 10);
        Advancedbtn.setOnAction(e -> {
        /**
         * Al presionar el boton este
         * inicializara en modo avanzado
         */
        Computer.AdvancedInit = true;
        Computer.AdvancedMode();
    });
        Button InitControler = new Button();
        InitControler.setText("Input\nControl");
        InitControler.setStyle("-fx-border-color: #12CA00;-fx-border-width: 3");
        InitControler.setFont(Font.font("Impact"));
        InitControler.setTranslateX(195);
        InitControler.setTranslateY(-50);
        InitControler.setMaxSize(70, 60);
        InitControler.setAlignment(Pos.CENTER);
        InitControler.setOnAction(e -> {});

    /**
     * Se crea el boton que almacenara
     * la pila de sugerencias
     */
    Button Sugestionbtn = new Button();
        Sugestionbtn.setText("Secure\nSugestion");
        Sugestionbtn.setStyle("-fx-border-color: #12CA00;-fx-border-width: 3");
        Sugestionbtn.setFont(Font.font("Impact"));
        Sugestionbtn.setTranslateX(80);
        Sugestionbtn.setTranslateY(190);
        Sugestionbtn.setMaxSize(125, 75);
        Sugestionbtn.setAlignment(Pos.CENTER);
        Sugestionbtn.setOnAction(e -> {

        /**
         * Al presionar el boton este analizara que
         * el juego no haya finalizado, que hayan pasado 5 trunos,
         * esto para que el jugador tenga acceso a la sugerencia
         * cumpliendo los requerimentos
         */
        if (Tablero.Finish == false) { // Se verifica que el juego no haya finalizado
            if (Tablero.CountSuggestions > 0) { // Se verifica que hayan sugerencias disponibles
                Tablero.SetStackSuggestions();
                if (Tablero.StackSuggestions.Size() > 0) { // Se verifica que la pila contenga las sugerencias
                    try {
                        while(Tablero.Tablerovalue[Tablero.StackSuggestions.Peek()[0]][Tablero.StackSuggestions.Peek()[1]].Clean_ == true) {
                            Tablero.StackSuggestions.Pop(); // Se eliminan los espacios que se revelaron
                        }
                        if (Tablero.StackSuggestions.Size() == 0) { // Se verifica que la pila esta vacia
                            Advicelbl.setText("Without Suggestion"); // Se Advierte al jugador
                        }
                        else { // Se verifica que la pila tenga sugerencias
                            Tablero.Tablerobtn[Tablero.StackSuggestions.Peek()[0]][Tablero.StackSuggestions.Peek()[1]].setStyle("-fx-background-color: #00304E;"); // El espacio seguro se tornara navy
                            Tablero.StackSuggestions.Pop(); // Se elimina la celda segura de la pila
                            Tablero.CountSuggestions--; // Se elimina la sugerencia de la pila
                            Tablero.CounterSuggestions.setText(Integer.toString(Tablero.CountSuggestions)); // Se refescan las sugerencias disponibles
                        }
                    }
                    catch (Exception except) { // Se hace la excepcion a la pila, pues esta vacia
                        Advicelbl.setText("Without Suggestion"); // Se advierte al jugador
                    }
                }
                else { // La pila esta vacia
                    Advicelbl.setText("Without Suggestion"); // Se advierte al jugador
                }
            }
            else { // Si no se cumplen los 5 trunos
                Advicelbl.setText("The suggestion it's available with 5 turns"); // Se advierte al jugador
            }
        }

    });
    /**
     * Se crea el tablero y se establece el color gris
     */
    Random staticr = new Random(); // Se crea la constante para dar color al tablero
    int colorbtn = staticr.nextInt(1); // Se establece el color del tablero
    int posx = -224; // Se ubica el primer boton el el eje x
    int posy = -228; // Se ubica el primer boton el el eje y
        for (int i=0; i<=7; i++) {
        for (int j=0; j<=7; j++) {
            int col = j; // Se crea el indice de las columnas
            int fila = i; // Se crea el indice de las filas
            if (j == 7){ // Se establece que la columna poseera 8 botones
                Tablero.Tablerobtn[fila][col] = new Button(); // Se agrega un boton al tablero
                Tablero.Tablerovalue[fila][col] = new Tablero(0, 0, 0, false); // Se establece el objeto Tablero y se agregan sus parametros
                Tablero.Tablerobtn[fila][col].setTranslateX(posx); // Se determina la posicion del boton en x
                Tablero.Tablerobtn[fila][col].setTranslateY(posy); // Se determina la posicion del boton en y
                posx = -224; // Se devuelve la posicion original en el eje x
                posy = posy+50; // Se agregan 50 espacios para la nueva fila
                Tablero.Tablerobtn[fila][col].setMaxSize(50,50); // Se determina el tamaño del boton
                Tablero.colorFijo(fila, col, colorbtn); // Se determina el color del boton
                layout.getChildren().add(Tablero.Tablerobtn[fila][col]); // Se añade el boton al panel
                Tablero.Tablerobtn[fila][col].setOnMouseClicked(e -> { // Se da funcion a la hora de clickear el boton

                    /**
                     * Se verifica que haya una partida en juego,
                     * se da funcion de agregar y quitar banderas,
                     * se verifica el estado del jugador,
                     * se agregan los procesos de la partida.
                     */
                    Advicelbl.setText(""); // Se limpia la etiqueta que indica errores
                    if (Tablero.Finish == false) { // Se verifica que la partida continue
                        if (e.getButton() == MouseButton.PRIMARY) { // Se da funcion al click izquierdo
                            if (Tablero.Tablerovalue[fila][col].Clean_ == false) { // Se verifica que el espacio no este revelado
                                if (Tablero.Tablerovalue[fila][col].flag == 0) { // Se verifica que no haya una bandera en el boton
                                    Tablero.Turns++; // Se agrega un turno al contador
                                    if (Tablero.Turns % 5 == 0) { // Se determina la cantidad de turnos para otrogar sugerencias
                                        Tablero.CountSuggestions++; // Se agrega una sugerencia segura
                                        Tablero.CounterSuggestions.setText(Integer.toString(Tablero.CountSuggestions)); // Se visibilizan la cantidad de sugerencias
                                        Tablero.SetStackSuggestions(); // Se implementa la pila de sugerencias
                                    }
                                    if (Tablero.Mine(Tablero.Tablerovalue[fila][col].Bomb)) { // Se verifica que el epacio posee una mina
                                        Tablero.MineReveal(); // Se revelan todas las minas
                                        Tablero.Finish = true; // Se finaliza la partida
                                        Tablero.Resetbtn.setText("Dead"); // Se actualiza el estado del jugador para denotar el estado de la partida
                                    }
                                    else if (Tablero.Tablerovalue[fila][col].proxybomb == 0){ // Si el valor del espacio es 0
                                        Tablero.RevealEmpty(fila, col); // Se revelan los espacios vacios mas proximos
                                        FlagCounter(); // Se ajusta el contador de banderas
                                        Tablero.MinesCount.setText(Integer.toString(Tablero.FlagCount)); // Se indica la cantidad de banderas
                                        if (Computer.DummyInit == true){ // Se verifica que el enemigo es dummy
                                            Computer.Turn = true; // Se da el turno de la computadora
                                            Computer.DummyMode(); // La coputadora elije su espacio
                                        }
                                        else if (Computer.AdvancedInit == true) { // Se verifica que el enemigo es avanzado
                                            Computer.Turn = true; // Se da el turno de la computadora
                                            Computer.AdvancedMode(); // La coputadora elije su espacio
                                        }
                                    }
                                    else { // Si el valor del espacio es 0 pero no es unamina
                                        Tablero.Tablerovalue[fila][col].Clean_ = true; // Se revelan los espacios vacios mas proximos
                                        Tablero.Tablerobtn[fila][col].setText(Integer.toString(Tablero.Tablerovalue[fila][col].proxybomb)); // Se indican el numero de minas por proximidad
                                        Tablero.Tablerobtn[fila][col].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #C2C2C2;"); // Los espacios son revelados
                                        if (Computer.DummyInit == true){ // Se verifica que el enemigo es dummy
                                            Computer.Turn = true; // Se da el turno de la computadora
                                            Computer.DummyMode(); /// La coputadora elije su espacio
                                        }
                                        else if (Computer.AdvancedInit == true) {// Se verifica que el enemigo es avanzado
                                            Computer.Turn = true; // Se da el turno de la computadora
                                            Computer.AdvancedMode(); // La coputadora elije su espacio
                                        }
                                    }
                                    Tablero.Victory(); // Se verifica la sobrevivencia en la partida
                                    if (Tablero.Survive == true) { // Si sobrevive
                                        Tablero.Resetbtn.setText("Survive"); // Se muestra el texto Survive
                                    }
                                }
                            }
                        }
                        else if (e.getButton() == MouseButton.SECONDARY) { // El boton es presionado con el click derecho
                            if (Tablero.Tablerovalue[fila][col].Clean_ == false) { // Se verifica que el espacio no este revelado
                                if (Tablero.Tablerovalue[fila][col].flag == 0) { // Se verifica de que no tenga banderas
                                    Tablero.Tablerobtn[fila][col].setText("!!!"); // Se simboliza la bandera con !!!
                                    Tablero.Tablerovalue[fila][col].flag = 1; // Se cambia el valor del espacio a 1
                                    Tablero.FlagCount++; // Se agrega una bandera al contador
                                    Tablero.MinesCount.setText(Integer.toString(Tablero.FlagCount)); // Se avisa la cantidad de banderas

                                }
                                else { // La bandera es retirada
                                    Tablero.Tablerobtn[fila][col].setText(""); // Se elimina el !!!
                                    Tablero.Tablerovalue[fila][col].flag = 0; // Se cambia el valor del espacio a 0
                                    Tablero.FlagCount--; // Se elimina una bandera al contador
                                    Tablero.MinesCount.setText(Integer.toString(Tablero.FlagCount)); // Se avisa la cantidad de banderas
                                }
                            }
                        }
                    }
                });
            }
            else {
                Tablero.Tablerobtn[fila][col] = new Button(); // Se crea el boton y se agrega al tablero
                Tablero.Tablerovalue[fila][col] = new Tablero(0, 0, 0, false); // Se crea el tablero y se agregan sus parametros
                Tablero.Tablerobtn[fila][col].setTranslateX(posx);// Se determina la posicion en x
                Tablero.Tablerobtn[fila][col].setTranslateY(posy);// Se determina la posicion en y
                posx = posx+50;// Se agregan 50 espacios para agregar otro boton
                Tablero.Tablerobtn[fila][col].setMaxSize(50,50);// se determina el tamaño del boton
                Tablero.colorFijo(fila, col, colorbtn);// Se da color al boton
                layout.getChildren().add(Tablero.Tablerobtn[fila][col]);//Se agrega el boton al panel
                Tablero.Tablerobtn[fila][col].setOnMouseClicked(e -> {// se da la funcion al boton al ser presionado

                    /**
                     * Se verifica que haya una partida en juego,
                     * se da funcion de agregar y quitar banderas,
                     * se verifica el estado del jugador,
                     * se agregan los procesos de la partida.
                     */
                    Advicelbl.setText(""); // Se limpia la etiqueta que indica errores
                    if (Tablero.Finish == false) { // Se verifica que la partida continue
                        if (e.getButton() == MouseButton.PRIMARY) { // Se da funcion al click izquierdo
                            if (Tablero.Tablerovalue[fila][col].Clean_ == false) { // Se verifica que el espacio no este revelado
                                if (Tablero.Tablerovalue[fila][col].flag == 0) { // Se verifica que no haya una bandera en el boton
                                    Tablero.Turns++; // Se agrega un turno al contador
                                    if (Tablero.Turns % 5 == 0) { // Se determina la cantidad de turnos para otrogar sugerencias
                                        Tablero.CountSuggestions++; // Se agrega una sugerencia segura
                                        Tablero.CounterSuggestions.setText(Integer.toString(Tablero.CountSuggestions)); // Se visibilizan la cantidad de sugerencias
                                        Tablero.SetStackSuggestions(); // Se implementa la pila de sugerencias
                                    }
                                    if (Tablero.Mine(Tablero.Tablerovalue[fila][col].Bomb)) { // Se verifica que el epacio posee una mina
                                        Tablero.MineReveal(); // Se revelan todas las minas
                                        Tablero.Finish = true; // Se finaliza la partida
                                        Tablero.Resetbtn.setText("Dead"); // // Se actualiza el estado del jugador para denotar el estado de la partida
                                    }
                                    else if (Tablero.Tablerovalue[fila][col].proxybomb == 0){ // Si el valor del espacio es 0
                                        Tablero.RevealEmpty(fila, col); // Se revelan los espacios vacios mas proximos
                                        FlagCounter(); // Se ajusta el contador de banderas
                                        Tablero.MinesCount.setText(Integer.toString(Tablero.FlagCount)); // Se indica la cantidad de banderas
                                        if (Computer.DummyInit == true){ // Se verifica que el enemigo es dummy
                                            Computer.Turn = true; // Se da el turno de la computadora
                                            Computer.DummyMode(); // La coputadora elije su espacio
                                        }
                                        else if (Computer.AdvancedInit == true) { // Se verifica que el enemigo es avanzado
                                            Computer.Turn = true; // Se da el turno de la computadora
                                            Computer.AdvancedMode(); // La coputadora elije su espacio
                                        }
                                    }
                                    else { // si el espacio es diferente a cero y no es una mina 
                                        Tablero.Tablerovalue[fila][col].Clean_ = true; // se revela el espacio
                                        Tablero.Tablerobtn[fila][col].setText(Integer.toString(Tablero.Tablerovalue[fila][col].proxybomb)); // se le pone el numero al espacio
                                        Tablero.Tablerobtn[fila][col].setStyle("-fx-background-color: #DADAD7;-fx-border-color: #C2C2C2;"); // se cambia el color para hacer mas claro que los espacios fueron revelados 
                                        if (Computer.DummyInit == true){ // Se verifica que el enemigo es dummy
                                            Computer.Turn = true; // Se da el turno de la computadora
                                            Computer.DummyMode(); // La coputadora elije su espacio
                                        }
                                        else if (Computer.AdvancedInit == true) {// Se verifica que el enemigo es avanzado
                                            Computer.Turn = true; // Se da el turno de la computadora
                                            Computer.AdvancedMode(); // La coputadora elije su espacio
                                        }
                                    }
                                    Tablero.Victory(); // se checkea si se ha ganado
                                    if (Tablero.Survive == true) { // si se ha ganado
                                        Tablero.Resetbtn.setText("Survive"); // se cambia el boton a una cara feliz para sombolizar Survive
                                    }
                                }
                            }
                        }
                        else if (e.getButton() == MouseButton.SECONDARY) { // El boton es presionado con el click derecho
                            if (Tablero.Tablerovalue[fila][col].Clean_ == false) { // Se verifica que el espacio no este revelado
                                if (Tablero.Tablerovalue[fila][col].flag == 0) { // Se verifica de que no tenga banderas
                                    Tablero.Tablerobtn[fila][col].setText("!!!"); // Se simboliza la bandera con !!!
                                    Tablero.Tablerovalue[fila][col].flag = 1; // Se cambia el valor del espacio a 1
                                    Tablero.FlagCount++; // Se agrega una bandera al contador
                                    Tablero.MinesCount.setText(Integer.toString(Tablero.FlagCount)); // Se avisa la cantidad de banderas

                                }
                                else { // La bandera es retirada
                                    Tablero.Tablerobtn[fila][col].setText(""); // Se elimina el !!!
                                    Tablero.Tablerovalue[fila][col].flag = 0; // Se cambia el valor del espacio a 0
                                    Tablero.FlagCount--; // Se elimina una bandera al contador
                                    Tablero.MinesCount.setText(Integer.toString(Tablero.FlagCount)); // Se avisa la cantidad de banderas
                                }
                            }
                        }
                    }
                });
            }
        }
    }

        /**
         * Se hace un llamado a los metodos SetMines()
         * y SetMineProximity, esto para colocar los contenidos al tablero
         */
        Tablero.SetMines(); // Se colocan las minas en el tablero
        Tablero.SetMinesProximity(); // Se colocan los numeros para indicar las minas proximas
    /**
     * Se añaden los labels y botones al panel.
     */
        layout.getChildren().addAll(PosibleMines, Tablero.MinesCount,Chronometerlbl, Tablero.Resetbtn,Dummybtn, Advancedbtn, Sugestionbtn, Advicelbl, Tablero.CounterSuggestions, InitControler);

}

    /**
     * Se determinan las variables
     * para dar un reinicio al juego
     */
    public static void Restart() {// Se crea el metodo para reiniciar la partica
        Tablero.FlagCount = 0; // Se borran todas las banderas
        Tablero.MinesCount.setText(Integer.toString(Tablero.FlagCount)); // Se borran en la etiqueta la cantidad de banderas
        Tablero.countersec = -1; // Se reinicia el contador de segundos
        Tablero.countermin = 0; // Se reinicia el contador de minutos
        Tablero.Finish = false; // Se reinicia la partida
        Tablero.Survive = false; // Se vuelve al estado Alive
        Tablero.Turns = 0; // Se reinicia el contador de segundos
        Tablero.CountSuggestions = 0;  // Se reinicia el contador de sugerencias
        Tablero.Resetbtn.setText("Alive");  // Se vuelve al estado Alive
        Computer.DummyInit = false; // El modo se quita
        Computer.AdvancedInit = false; // El modo se quita
        Computer.Turn = false; // Se reinicia el contador de turnos
        Computer.ListLinkMines.EmptyList(); // Se limpia la lista de minas
        Computer.ListLinkSafe.EmptyList(); // Se limpia la lista segura
        Computer.ListLinkTotal.EmptyList(); // Se limpia la lista general
        Computer.ListLinkNoSafe.EmptyList(); // Se limpia la lista incertidumbre
        Computer.ListLinkMines.ResetSize(); // El valor de la lista vuelve a 0
        Computer.ListLinkSafe.ResetSize(); // El valor de la lista vuelve a 0
        Computer.ListLinkTotal.ResetSize(); // El valor de la lista vuelve a 0
        Computer.ListLinkNoSafe.ResetSize(); // El valor de la lista vuelve a 0
        Tablero.CleanStack(Tablero.StackSuggestions); // Se limpia la pila de sugerencias
        Tablero.StackSuggestions.ResetSize(); // El valor de la pila vuelve a 0
        Tablero.CounterSuggestions.setText("0"); // Se avisa al jugador la cantidad de sugerencias

        Random staticr = new Random(); // Se establece el valor
        int colorbtn = staticr.nextInt(1); // Se determina el color del tablero
        for (int i=0; i<=7; i++) {
            for (int j=0; j<=7; j++) {
                Tablero.Tablerovalue[i][j].Bomb = 0; // Se eliminan las minas del tablero
                Tablero.Tablerovalue[i][j].flag = 0; // Se eliminan las banderas del tablero
                Tablero.Tablerovalue[i][j].proxybomb = 0; // Se eliminan los numeros proximos a la minas
                Tablero.Tablerovalue[i][j].Clean_ = false; // Se instaura el tablero de nuevo
                Tablero.Tablerobtn[i][j].setText(""); // Se limpia el texto de los botones
                Tablero.colorFijo(i, j, colorbtn); // Se asigna el color de los botones
            }
        }
        Tablero.SetMines(); // Se colocan las minas en el tablero
        Tablero.SetMinesProximity(); // Se colocan los numeros para indicar las minas proximas
    }

    /**
     * Se crea el metodo FlagCounter()
     * para llevar el conteo de banderas
     */
    public static void FlagCounter() {
        for (int i=0; i<=7; i++) {
            for (int j=0; j<=7; j++) {
                if (Tablero.Tablerovalue[i][j].flag == 1 && Tablero.Tablerovalue[i][j].Clean_ && Tablero.Tablerovalue[i][j].proxybomb == 0) { // si hay una bandera, el espacio se ha revelado y el numero es un 0
                    Tablero.Tablerobtn[i][j].setText(""); // se quita el texto del boton
                    Tablero.FlagCount--; // se disminuye la cantidad de banderas
                    Tablero.Tablerovalue[i][j].flag = 0; // se quita la bandera de la matriz de valores
                }
                if (Tablero.Tablerovalue[i][j].flag == 1 && Tablero.Tablerovalue[i][j].Clean_ && Tablero.Tablerovalue[i][j].proxybomb > 0) { // si hay una bandera, el espacio se ha revelado y el numero es diferente de 0
                    Tablero.Tablerobtn[i][j].setText(Integer.toString(Tablero.Tablerovalue[i][j].proxybomb)); // se le pone el numero de minas adyacentes al boton
                    Tablero.FlagCount--; // se disminuye la cantidad de banderas
                    Tablero.Tablerovalue[i][j].flag = 0; // se quita la bandera de la matriz de valores
                }
            }
        }
    }

    /**
     * Metodo para eleccion de Dummy
     * @param i fila del boton seleccionado
     * @param j columna del boton seleccionado
     */
    public static void ChooseDummy(int i, int j) {
        if (Tablero.Mine(Tablero.Tablerovalue[i][j].Bomb)) { // Se verifica si el espacio es una mina
            Tablero.UpdateTablero(); // Se refresca el tablero
            Tablero.MineRevealDummy(); // Se muestra la mina elegida por el Dummy
            Tablero.Finish = true; // se termina el juego
            Tablero.Resetbtn.setText(" Du Dead"); // Se denota la muerte del dummy

        }
        else if (Tablero.Tablerovalue[i][j].proxybomb == 0) { // Si el espacio elegido es 0
            Tablero.RevealEmpty(i, j); // Se mustran los espacios limpios proximos
            FlagCounter(); // Se modifica el contador de banderas
            Tablero.MinesCount.setText(Integer.toString(Tablero.FlagCount)); // Se muestra la cantidad de banderas
            Tablero.UpdateTablero(); // Se elimina el marcador del dummy
            Tablero.Tablerobtn[i][j].setStyle("-fx-background-color: #a18262;-fx-border-color: #ff6600;-fx-border-width: 3"); // Se muestra el espacio elegido
        }
        else { // El espacio no es 0 y no es mina
            Tablero.Tablerovalue[i][j].Clean_ = true; // Se muestran los numeros de minas proximas
            Tablero.Tablerobtn[i][j].setText(Integer.toString(Tablero.Tablerovalue[i][j].proxybomb)); // Se pone el numero de minas proximas al boton
            Tablero.UpdateTablero(); // Se limpia el tablero
            Tablero.Tablerobtn[i][j].setStyle("-fx-background-color: #a18262;-fx-border-color: #ff6600;-fx-border-width: 3"); // Se muestra el espacio elegido
            FlagCounter(); // Se modifica el contador de banderas
            Tablero.MinesCount.setText(Integer.toString(Tablero.FlagCount)); // Se muetra la cantidad de banderas
        }
        Tablero.Victory(); // Se verifica la sobrevivencia
        if (Tablero.Survive == true) { // Sobrevivio
            Tablero.Resetbtn.setText("Survive"); // Se muestra el texto Survive
        }
    }

    /**
     * Metodo para la eleccion del Avanzado
     * @param i fila del boton seleccionado
     * @param j columna del boton seleccionado
     */
    public static void ChooseAdvanced(int i, int j) {
        Tablero.Victory(); // Se verifica si sobrevivio
        if (Tablero.Survive == false) { // Se determina no sobrevivio
            if (Tablero.Mine(Tablero.Tablerovalue[i][j].Bomb)) { // Se verifica si el espacio es una mina
                Tablero.UpdateTablero(); // Se refresca el tablero
                Tablero.MineRevealAdvanced(); // // Se muestra la mina elegida por el Avanzado
                Tablero.Finish = true; // Se termina el juego
                Tablero.Resetbtn.setText("Dead"); // Se muestra el texto Dead

            }
            else if (Tablero.Tablerovalue[i][j].proxybomb == 0){ // Si el espacio elegido es 0
                Tablero.RevealEmpty(i, j); // Se mustran los espacios limpios proximos
                FlagCounter(); // Se modifica el contador de banderas
                Tablero.MinesCount.setText(Integer.toString(Tablero.FlagCount)); // Se muestra la cantidad de banderas
                Tablero.UpdateTablero(); // Se elimina el marcador del Avanzado
                Tablero.Tablerobtn[i][j].setStyle("-fx-background-color: #a18262;-fx-border-color: #000000;-fx-border-width: 3"); // Se muestra el espacio elegido
            }
            else {// El espacio no es 0 y no es mina
                Tablero.Tablerovalue[i][j].Clean_ = true; // Se muestran los numeros de minas proximas
                Tablero.Tablerobtn[i][j].setText(Integer.toString(Tablero.Tablerovalue[i][j].proxybomb)); // Se pone el numero de minas proximas al boton
                Tablero.UpdateTablero(); // Se limpia el tablero
                Tablero.Tablerobtn[i][j].setStyle("-fx-background-color: #a18262;-fx-border-color: #000000;-fx-border-width: 3"); // Se muestra el espacio elegido
                FlagCounter(); // Se modifica el contador de banderas
                Tablero.MinesCount.setText(Integer.toString(Tablero.FlagCount)); // Se muetra la cantidad de banderas
            }
        }
        else {
            Tablero.Resetbtn.setText("Survive"); // Se muestra el texto Survive
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}