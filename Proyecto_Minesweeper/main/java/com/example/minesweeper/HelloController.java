package com.example.minesweeper;


import javafx.application.Platform;
import org.firmata4j.firmata.*;
import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import java.io.IOException;

public class HelloController {
    static boolean Init_control = false; //boleano para indicar si el control esta activado
    private static int  Locationi = 0;// Indice en i
    private static int Locationj = 0;// Indice en j
    static final String USBPORT = "COM4"; // puerto en el que se conecta el arduino

    static final int Actionbtn = 2; // pin del boton de accion
    static final int Rightbtn = 3; // pin del boton de la derecha
    static final int Leftbtn = 4; // pin del boton de la izquierda
    static final int Downbtn = 5; // pin del boton de abajo
    static final int Upbtn = 6; // pin del boton de arriba
    static final int Buzzer=7;// pin del buzzer
    static Pin InitBuzzer;//Inicia el buzzer
    static final int Led=8;// pin del led
    static Pin InitLed;// Inicia el led
    public static void ActivateArduino() throws IOException, InterruptedException {
        IODevice myArduino = new FirmataDevice(USBPORT);//Se Enlaza el puerto usb

        try { // se conecta el arduino

            myArduino.start();// Se enlaza el arduino
            myArduino.ensureInitializationIsDone();// El arduino empieza a funcionar
            System.out.println("Arduino it's redy");// Verificacion de conexion
            Init_control = true;// Arranca el arduino

        }
        catch (IOException ioexception) { // En caso de no funcionar
            System.out.println("Arduino have a conection problem");// Indica un problema en consola
        }
        finally { // Se crean variables para el arduino

            Pin Actbtn = myArduino.getPin(Actionbtn); // Variable del pin
            Actbtn.setMode(Pin.Mode.INPUT); // Se asigna INPUT para el boton

            Pin Rbtn = myArduino.getPin(Rightbtn); // Variable del pin
            Rbtn.setMode(Pin.Mode.INPUT); // sSe asigna INPUT para el boton

            Pin Lbtn = myArduino.getPin(Leftbtn); // Variable del pin
            Lbtn.setMode(Pin.Mode.INPUT); // Se asigna INPUT para el boton

            Pin Dbtn = myArduino.getPin(Downbtn); // Variable del pin
            Dbtn.setMode(Pin.Mode.INPUT); // Se asigna INPUT para el boton

            Pin Ubtn = myArduino.getPin(Upbtn); // Variable del pin
            Ubtn.setMode(Pin.Mode.INPUT); // Se asigna INPUT para el boton

            InitBuzzer = myArduino.getPin(Buzzer);//Variable delbuzzer
            InitBuzzer.setMode(Pin.Mode.OUTPUT);// Se asigna output

            InitLed = myArduino.getPin(Led);//Variable del led
            InitLed.setMode(Pin.Mode.OUTPUT);//Se asigna output


            while (Init_control == true) {
                if (Actbtn.getValue() != 0) { // Verifica el pulsado del boton
                    Platform.runLater(() ->Minesweeper.ChooseControl(Locationi,Locationj));// Acciona el boton
                    Thread.sleep(200);// Se hace un sleep para que haga la identificacion visible
                }
                if (Ubtn.getValue() != 0) { // Verifica el pulsado del boton
                    if (Locationi < 0){// Verifica la localizacion en i
                        ReturnOriginal();// devuelve el tablero al estado original
                        Locationi--;//Sube el indicador
                        Tablero.Tablerobtn[Locationi][Locationj].setMaxSize(35,35);//Se achica el boton para denotar al indicador
                        //Platform.runLater(() ->{});
                        Thread.sleep(200);//Se hace un sleep para que haga la identificacion visible
                    }
                }
                if (Dbtn.getValue() != 0) { // Verifica el pulsado del boton
                    if (Locationi < 7){//Verifica la localizacion en i
                        ReturnOriginal();// devuelve el tablero al estado original
                        Locationi++;//Baja el indicador
                        Tablero.Tablerobtn[Locationi][Locationj].setMaxSize(35,35);//Se achica el boton para denotar al indicador
                        //Platform.runLater(() ->{});
                        Thread.sleep(200);//Se hace un sleep para que haga la identificacion visible
                    }
                }
                if (Rbtn.getValue() != 0) { // Verifica el pulsado del boton
                    if (Locationj < 7){//Verifica la localizacion en j
                        ReturnOriginal();// devuelve el tablero al estado original
                        Locationj++;//Direcciona a la derecha el indicador
                        Tablero.Tablerobtn[Locationi][Locationj].setMaxSize(35,35);//Se achica el boton para denotar al indicador
                        //Platform.runLater(() ->{});
                        Thread.sleep(200);//Se hace un sleep para que haga la identificacion visible
                    }
                }
                if (Lbtn.getValue() != 0) { // Verifica el pulsado del boton
                    if (Locationj < 0){//Verifica la localizacion en j
                        ReturnOriginal();// devuelve el tablero al estado original
                        Locationj--;//Direcciona a la izquierda el indicador
                        Tablero.Tablerobtn[Locationi][Locationj].setMaxSize(35,35);//Se achica el boton para denotar al indicador
                        //Platform.runLater(() -> {});
                        Thread.sleep(200);//Se hace un sleep para que haga la identificacion visible
                    }
                }
            }
            myArduino.stop(); // Frena el while del arduino
            Init_control=false;// Apaga el control
        }
    }

    /**
     * El metodo ReturnOriginal devuelve el tablero al estado original,
     * pues el boton de la posicion se le coloco un color para denotar el indicador
     */
    public static void ReturnOriginal() {
        for (int i=0; i<=7; i++) {//Establece la dimension de la celda
            for (int j=0; j<=7; j++) {//Establece la dimension de la celda
                Tablero.Tablerobtn[Locationi][Locationj].setMaxSize(50,50);// Vuelve al tamaño original
            }
        }
    }
}
