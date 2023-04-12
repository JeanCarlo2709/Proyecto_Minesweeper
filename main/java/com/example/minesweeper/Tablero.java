package com.example.minesweeper;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.Random;

public class Tablero {
    int Bomb; // Variable que denota a una mina
    int flag; // Variable que denota a una bandera
    int proxybomb; // Variable que denota a una mina cercana , si es un -1, significa que hay una mina en el espacio
    boolean Clean_; // Variable que denota a un espacio revelado
    static int FlagCount = 0; // Contador de banderas colocadas en el tablero
    static int countersec = 0; // Contador de segundos
    static int countermin = 0; // Contador de minutos
    static int Turns = 0; // Contador de turnos
    static int CountSuggestions = 0; // Contador de sugerencias
    static boolean Finish = false; // Variable que define el fin de la partida
    static boolean Survive = false; // Variable que define la sobrevivencia
    static Button[][] Tablerobtn = new Button[8][8]; // Matriz de botones
    static Tablero[][] Tablerovalue = new Tablero[8][8]; // Matriz con los valores de los botones
    static Stack StackSuggestions = new Stack(); // Pila de sugerencias
    static Button Resetbtn; // Boton para resetear la partida
    static Label MinesCount; // Etiqueta de minas posibles
    static Label CounterSuggestions; // Etiqueta de cantidad de sugerencias

    Tablero(int mina, int bandera, int proxybomb, boolean Clean_) { // Se define el Tablero
        this.Bomb = mina; // Se definen las minas
        this.flag = bandera; // Se definen las banderas
        this.proxybomb = proxybomb; // Se define como la cantidad de minas proximas
        this.Clean_ = Clean_; // Define si el espacio esta revelado
    }

    static int minminas = 8; // Cantidad de minas que apareceran en Tablero

    /**
     * Se crea el metodo SetMines que colocara las minas de manera aleatoria en el tablero.
     */
    public static void SetMines() { //Coloca las minas de manera aleatoria en el tablero
        Random r = new Random();
        int cantminas = 0; // contador de cantidad de minas
        while (cantminas < minminas) {
            for (int i = 0; i <= 7; i++) {
                for (int j = 0; j <= 7; j++) { // Se recorre el valor de la matriz
                    int numrandom = r.nextInt(8); //Elige la posicion de manera aleatoria en el tablero
                    if (cantminas == minminas) { // Si el tablero tiene la cantidad minima de minas, se dejan de spawnear
                        break;
                    }
                    else { // Si faltan minas
                        if (numrandom == 1) { //Si el numrandom es un 1, se coloca una mina en el espacio elegido
                            if (Tablerovalue[i][j].Bomb == 0) { // El espacio no tiene unamina
                                Tablerovalue[i][j].Bomb = 1; // Al espacio se le coloca una mina
                                cantminas++; // Se agrega al contador la cantidad de minas
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * El metodo Mine analiza si el espacio es una mina
     *
     * @param x Es la variable mina
     * @return true si es una mina, false si no es una mina
     */
    public static boolean Mine(int x) { // Verifica si hay una mina en la Tablero
        if (x == 1 || x == -1) { // Verifica que hay una mina en el espacio
            return true; // Devuelve true si en el espacio hay una mina
        }
        else {
            return false; // Devuelve false si no hay una mina en el espacio
        }
    }

    /**
     * El metodo MineReveal mustra las minas en el tablero denotando que murio y se acaba la partida.
     */
    public static void MineReveal() { //Muestra todas las minas en el tablero
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) { // Recorre el tablero
                if (Tablero.Mine(Tablerovalue[i][j].Bomb)) { // Verifica si hay una mina
                    Tablerobtn[i][j].setText("*"); // Muestra un * para denotar que hay una mina
                    Tablerobtn[i][j].setStyle("-fx-background-color: #FFFF00;"); // Se cambia el fondo a rojo
                }
            }
        }

    }
    /**
     * El metodo MineReveal mustra las minas en el tablero denotando que murio y se acaba la partida.
     */
    public static void MineRevealDummy() { //Muestra todas las minas en el tablero
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) { // Recorre el tablero
                if (Tablero.Mine(Tablerovalue[i][j].Bomb)) { // Verifica si hay una mina
                    Tablerobtn[i][j].setText("*"); // Muestra un * para denotar que hay una mina
                    Tablerobtn[i][j].setStyle("-fx-background-color: #FFFF00;"); // Se cambia el fondo a rojo
                }
            }
        }

    }
    /**
     * El metodo MineReveal mustra las minas en el tablero denotando que murio y se acaba la partida.
     */
    public static void MineRevealAdvanced() { //Muestra todas las minas en el tablero
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) { // Recorre el tablero
                if (Tablero.Mine(Tablerovalue[i][j].Bomb)) { // Verifica si hay una mina
                    Tablerobtn[i][j].setText("*"); // Muestra un * para denotar que hay una mina
                    Tablerobtn[i][j].setStyle("-fx-background-color: #FFFF00;"); // Se cambia el fondo a rojo
                }
            }
        }

    }


    /**
     * El metodo CheckRange verifica que el espacio se encuentre en el tablero de 8x8
     * @param i fila del boton
     * @param j columna del boton
     * @return true si el boton esta en el tablero, false si el boton no esta en el tablero
     */
    public static boolean CheckRange(int i, int j) {
        if (i < 0 || j < 0 || i > 7 || j > 7) { // Recorre la matriz y verifica que el espacio este en el tablero
            return false; // No esta en el tablero devuelve false
        }
        else {
            return true; // Esta en el tablero devuelve true
        }
    }
    /**
     * Coloca a los botones los numeros de minas mas proxima
     */
    public static void SetMinesProximity() { // Coloca los numeros de las minas proximas
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) { // Recorre el tablero
                int contador = 0;
                if (!Tablero.Mine(Tablerovalue[i][j].Bomb)) { // No hay una mina en el boton
                    if (CheckRange(i - 1, j - 1)) { // Si al noroeste no hay botones
                        if (Tablero.Mine(Tablerovalue[i - 1][j - 1].Bomb)) { // Si al noroeste hay una mina
                            contador++; // Se agrega al contador
                        }
                    }
                    if (CheckRange(i - 1, j)) { // Si arriba no hay botones
                        if (Tablero.Mine(Tablerovalue[i - 1][j].Bomb)) { // Si en el espacio arriba hay una mina
                            contador++; // Se agrega al contador
                        }
                    }
                    if (CheckRange(i - 1, j + 1)) { // Si al noreste no hay botones
                        if (Tablero.Mine(Tablerovalue[i - 1][j + 1].Bomb)) { // Si aal noreste hay una mina
                            contador++; // Se agrega al contador
                        }
                    }
                    if (CheckRange(i, j - 1)) { // Si a la izquierda no hay botones
                        if (Tablero.Mine(Tablerovalue[i][j - 1].Bomb)) { // Si a la izquierda hay una mina
                            contador++; // Se agrega al contador
                        }
                    }
                    if (CheckRange(i, j + 1)) { // Si a la derecha no hay botones
                        if (Tablero.Mine(Tablerovalue[i][j + 1].Bomb)) { // Si a la derecha hay una mina
                            contador++; // Se agrega al contador
                        }
                    }
                    if (CheckRange(i + 1, j - 1)) { // Si al suroeste no hay botones
                        if (Tablero.Mine(Tablerovalue[i + 1][j - 1].Bomb)) { // si al suroeste hay una mina
                            contador++; // Se agrega al contador
                        }
                        if (CheckRange(i + 1, j)) { // Si abajo no hay botones
                            if (Tablero.Mine(Tablerovalue[i + 1][j].Bomb)) { // Si abajo hay una mina
                                contador++; // Se agrega al contador
                            }
                        }
                        if (CheckRange(i + 1, j + 1)) { // Si al sureste no hay un boton
                            if (Tablero.Mine(Tablerovalue[i + 1][j + 1].Bomb)) { // Si al sureste hay una mina
                                contador++; // Se agrega al contador
                            }
                        }
                        Tablerovalue[i][j].proxybomb = contador; // se crea la variable contador
                    }
                    else { // Si el espacio es una mina
                        Tablerovalue[i][j].proxybomb = -1; // Se determina que hay una mina
                    }

                }
            }
        }
    }
        /**
         * El metodo RevealProximity muestra la cantidad de minas mas proximas
         * @param i fila del boton
         * @param j columna del boton
         */
        public static void RevealProximity ( int i, int j){
            if (CheckRange(i-1, j-1)) { // Se verifica que el boton este en el tablero
                Tablerobtn[i-1][j-1].setStyle("-fx-background-color: #a18262;-fx-border-color: #C2C2C2;"); // Se aclaran los botones
                Tablerovalue[i-1][j-1].Clean_ = true; // Se declara el espacio revelado
                if (Tablerovalue[i-1][j-1].proxybomb > 0) { // Se declara una mina proxima
                    Tablerobtn[i-1][j-1].setText(Integer.toString(Tablerovalue[i-1][j-1].proxybomb)); // Se muestra la cantidad de mina proximas
                }
            }
            if (CheckRange(i-1, j)) { // Se verifica que el boton este en el tablero
                Tablerobtn[i-1][j].setStyle("-fx-background-color: #a18262;-fx-border-color: #C2C2C2;"); // Se aclaran los botones
                Tablerovalue[i-1][j].Clean_ = true; // Se declara el espacio revelado
                if (Tablerovalue[i-1][j].proxybomb > 0) { // Se declara una mina proxima
                    Tablerobtn[i-1][j].setText(Integer.toString(Tablerovalue[i-1][j].proxybomb));  // Se muestra la cantidad de mina proximas
                }
            }
            if (CheckRange(i-1, j+1)) { // Se verifica que el boton este en el tablero
                Tablerobtn[i-1][j+1].setStyle("-fx-background-color: #a18262;-fx-border-color: #C2C2C2;"); // Se aclaran los botones
                Tablerovalue[i-1][j+1].Clean_ = true; // Se declara el espacio revelado
                if (Tablerovalue[i-1][j+1].proxybomb > 0) { // Se declara una mina proxima
                    Tablerobtn[i-1][j+1].setText(Integer.toString(Tablerovalue[i-1][j+1].proxybomb));  // Se muestra la cantidad de mina proximas
                }
            }
            if (CheckRange(i, j-1)) { // Se verifica que el boton este en el tablero
                Tablerobtn[i][j-1].setStyle("-fx-background-color: #a18262;-fx-border-color: #C2C2C2;"); // Se aclaran los botones
                Tablerovalue[i][j-1].Clean_ = true; // Se declara el espacio revelado
                if (Tablerovalue[i][j-1].proxybomb > 0) { // Se declara una mina proxima
                    Tablerobtn[i][j-1].setText(Integer.toString(Tablerovalue[i][j-1].proxybomb));  // Se muestra la cantidad de mina proximas
                }
            }
            if (CheckRange(i, j+1)) { // Se verifica que el boton este en el tablero
                Tablerobtn[i][j+1].setStyle("-fx-background-color: #a18262;-fx-border-color: #C2C2C2;"); // Se aclaran los botones
                Tablerovalue[i][j+1].Clean_ = true; // Se declara el espacio revelado
                if (Tablerovalue[i][j+1].proxybomb > 0) { // Se declara una mina proxima
                    Tablerobtn[i][j+1].setText(Integer.toString(Tablerovalue[i][j+1].proxybomb));  // Se muestra la cantidad de mina proximas
                }
            }
            if (CheckRange(i+1, j-1)) { // Se verifica que el boton este en el tablero
                Tablerobtn[i+1][j-1].setStyle("-fx-background-color: #a18262;-fx-border-color: #C2C2C2;"); // Se aclaran los botones
                Tablerovalue[i+1][j-1].Clean_ = true; // Se declara el espacio revelado
                if (Tablerovalue[i+1][j-1].proxybomb > 0) { // Se declara una mina proxima
                    Tablerobtn[i+1][j-1].setText(Integer.toString(Tablerovalue[i+1][j-1].proxybomb));  // Se muestra la cantidad de mina proximas
                }
            }
            if (CheckRange(i+1, j)) { // Se verifica que el boton este en el tablero
                Tablerobtn[i+1][j].setStyle("-fx-background-color: #a18262;-fx-border-color: #C2C2C2;"); // Se aclaran los botones
                Tablerovalue[i+1][j].Clean_ = true; // Se declara el espacio revelado
                if (Tablerovalue[i+1][j].proxybomb > 0) { // Se declara una mina proxima
                    Tablerobtn[i+1][j].setText(Integer.toString(Tablerovalue[i+1][j].proxybomb));  // Se muestra la cantidad de mina proximas
                }
            }
            if (CheckRange(i+1, j+1)) { // Se verifica que el boton este en el tablero
                Tablerobtn[i+1][j+1].setStyle("-fx-background-color: #a18262;-fx-border-color: #C2C2C2;"); // Se aclaran los botones
                Tablerovalue[i+1][j+1].Clean_ = true; // Se declara el espacio revelado
                if (Tablerovalue[i+1][j+1].proxybomb > 0) { // Se declara una mina proxima
                    Tablerobtn[i+1][j+1].setText(Integer.toString(Tablerovalue[i+1][j+1].proxybomb));  // Se muestra la cantidad de mina proximas
                }
            }
            Tablerobtn[i][j].setStyle("-fx-background-color: #a18262;-fx-border-color: #C2C2C2;"); // Se aclara el boton
            Tablerovalue[i][j].Clean_ = true; // Se declara el espacio revelado
            }


        /**
         * El metodo RevealEmptyProximity muestra los espacios vacios y con minas cercanos
         * @param i fila del espacio
         * @param j columna del espacio
         */
        public static void RevealEmptyProximity ( int i, int j) { // Muestra los espacios vacios y con minas cercanos
            Tablero.RevealProximity(i, j); // Muestra las minas proximas
            int posoriginali = i; // Almacena la posicion inicial en i
            int posoriginalj = j; // Almacena la posicion inicial en i
            while (j >= 0) { // Muestra la izquierda
                while (i <= 7) { // Muestra abajo
                    if (Tablerovalue[i][j].proxybomb == 0) { // Muestra los espacios vacios
                        Tablero.RevealProximity(i, j); // Muestra espacios vacios proximos
                        i++; // Se deplaza hacia abajo
                    }
                    else { // Frena si hay una mina proxima
                        break;
                    }
                }
                i = posoriginali; // Regresa a la posicion i inicial
                while (i >= 0) { // Muestra arriba
                    if (Tablerovalue[i][j].proxybomb == 0) { // Muestra los espacios vacios
                        Tablero.RevealProximity(i, j); // Muestra espacios vacios proximos
                        i--; // Se desplaza arriba
                    }
                    else { // Frena si hay una mina proxima
                        break;
                    }
                }
                i = posoriginali; // Regresa a la posicion i inicial
                if (Tablerovalue[i][j].proxybomb == 0) { // Muestra los espacios vacios
                    Tablero.RevealProximity(i, j); // Muestra espacios vacios proximos
                    j--; // Se desplaza a la izquierda
                }
                else { // Frena si hay una mina proxima
                    break;
                }
            }
            j = posoriginalj; // Regresa a la posicion j inicial
            while (j <= 7) { // revela hacia la derecha
                while (i <= 7) { // revela hacia abajo
                    if (Tablerovalue[i][j].proxybomb == 0) { // Muestra los espacios vacios
                        Tablero.RevealProximity(i, j); // Muestra espacios vacios proximos
                        i++; // Se desplaza abajo
                    }
                    else { // Frena si hay una mina proxima
                        break;
                    }
                }
                i = posoriginali; // Regresa a la posicion i inicial
                while (i >= 0) { // revela hacia arriba
                    if (Tablerovalue[i][j].proxybomb == 0) { // Muestra los espacios vacios
                        Tablero.RevealProximity(i, j); // Muestra espacios vacios proximos
                        i--; // Se desplaza arriba
                    }
                    else { // Frena si hay una mina proxima
                        break;
                    }
                }
                i = posoriginali; // Regresa a la posicion i inicial
                if (Tablerovalue[i][j].proxybomb == 0) { // Muestra los espacios vacios
                    Tablero.RevealProximity(i, j); // Muestra espacios vacios proximos
                    j++; // Se desplaza a la derecha
                }
                else { // Frena si hay una mina proxima
                    break;
                }
            }
        }

        /**
         * El metodo RevealEmpty muestra solo espacios vacios
         * @param i fila del espacio analizado
         * @param j columna del espacio analizado
         */
        public static void RevealEmpty ( int i, int j) { // Muestra espacios vacios proximos
            Tablero.RevealEmptyProximity(i, j);// muestr minas proximas
            int posoriginali = i; // Almacena posicion inicial en i
            int posoriginalj = j; // Almacena posicion inicial en j
            while (j >= 0) { // Muestra la izquierda
                while (i <= 7) { // Muestra abajo
                    if (Tablerovalue[i][j].proxybomb == 0) { // Muestra los espacios vacios
                        Tablero.RevealEmptyProximity(i, j); // Muestra espacios vacios proximos
                        i++; // Se desplaza abajo
                    }
                    else { // Frena si hay algo diferente de vacio
                        break;
                    }
                }
                i = posoriginali; // vuelve a la posicion original
                while (i >= 0) { // revela hacia arriba
                    if (Tablerovalue[i][j].proxybomb == 0) { // Muestra los espacios vacioss
                        Tablero.RevealEmptyProximity(i, j); // Muestra espacios vacios proximos
                        i--; // Se desplaza arriba
                    }
                    else { // Frena si hay algo diferente de vacio
                        break;
                    }
                }
                i = posoriginali; // se devuelve a la posicion i original
                if (Tablerovalue[i][j].proxybomb == 0) { // Muestra los espacios vacios
                    Tablero.RevealEmptyProximity(i, j); // Muestra espacios vacios proximos
                    j--; // Se desplaza a la izquierda
                    }
                else { // Frena si hay algo diferente de vacio
                    break;
                }
            }
            j = posoriginalj; // se devuelve a la posicion j original
            while (j <= 7) { // revela hacia la derecha
                while (i <= 7) { // revela hacia abajo
                    if (Tablerovalue[i][j].proxybomb == 0) { // Muestra los espacios vacios
                        Tablero.RevealEmptyProximity(i, j); // Muestra espacios vacios proximos
                        i++; // Se desplaza abajo
                        }
                    else { // Frena si hay algo diferente de vacio
                        break;
                    }
                }
                i = posoriginali; // se devuelve a la posicion i original
                while (i >= 0) { // revela hacia arriba
                    if (Tablerovalue[i][j].proxybomb == 0) { // Muestra los espacios vacios
                        Tablero.RevealEmptyProximity(i, j); // Muestra espacios vacios proximos
                        i--; // Se desplaza arriba
                        }
                    else { // Frena si hay algo diferente de vacio
                        break;
                    }
                }
                i = posoriginali; // vuelve a la posicion en la fila original
                if (Tablerovalue[i][j].proxybomb == 0) { // Muestra los espacios vacios
                    Tablero.RevealEmptyProximity(i, j); // Muestra espacios vacios proximos
                    j++; // sSe desplaza a la derecha
                    }
                else { // Frena si hay algo diferente de vacio
                    break;
                }
            }
        }

        /**
         * El metodo colorFijo establece el color de el tablero de momento, pues se piensa variar el color del tablero para simular otros escenarios
         * @param i fila del boton al que se le determinara el color
         * @param j columna del boton al que se le determinara el color
         * @param colorbtn numero aleatorio para cambiar el color de los botones
         */
        public static void colorFijo ( int i, int j, int colorbtn){ // Establece el color del tablero
            if (colorbtn == 0) { // El numero es 0
                Tablerobtn[i][j].setStyle("-fx-background-color: #008000;-fx-border-color: #272323;"); // Establece el color del boton
            }
        }

        /**
         * El metodo Victory verifica que el jugador sobreviviera
         * momento de llamar el metodo.
         */
        public static void Victory () {
            int cantrevelados = 0; // Contador de espacios revelados
            for (int i = 0; i <= 7; i++) {
                for (int j = 0; j <= 7; j++) {
                    if (Tablerovalue[i][j].Clean_ == true) { // Se determina que el espacio esta revelado
                        cantrevelados++; // Se agrega el espacio revelado
                    }
                }
            }
            if (64 - cantrevelados == minminas) { // Si el contador del minimo de minas es igual a los espacios disponibles
                Finish = true; // Se finaliza la partida
                Survive = true; // Se declara la sobrevivencia
                for (int i = 0; i <= 7; i++) {
                    for (int j = 0; j <= 7; j++) {
                        if (Mine(Tablerovalue[i][j].Bomb)) {
                            Tablerobtn[i][j].setStyle("-fx-background-color: #87F57A;"); // Se revelan las minas con un fondo verde
                        }
                    }
                }

            }
        }

        /**
         * El metodo UpdateTablero actualiza el estado del tablero
         */
        public static void UpdateTablero () {
            for (int i = 0; i <= 7; i++) {
                for (int j = 0; j <= 7; j++) {
                    if (Tablerovalue[i][j].Clean_ == true) { // Se declara el espacio revelado
                        Tablerobtn[i][j].setStyle("-fx-background-color: #a18262;-fx-border-color: #C2C2C2;"); // Se aclara el boton para denotar la revelacion
                    }
                }
            }
        }

        /**
         * El metodo TakeMinesProximity almacena las minas proximas al boton clickeado
         * @param i fila del boton
         * @param j columna del boton
         * @return cantidad de minas mas proximas al boton seleccionado
         */
        public static int TakeMinesProximity ( int i, int j){
            int cantminas = 0; // Declara la cantidad de minas cercanas
            if (CheckRange(i, j + 1)) { // Verifica que el boton este en el tablero
                if (Computer.ListLinkMines.Take(i, j + 1)) { // Verifica que el espacio esta en la lista enlazada con minas
                    cantminas++; // Se agrega al contador
                }
            }
            if (CheckRange(i + 1, j + 1)) { // Verifica que el boton este en el tablero
                if (Computer.ListLinkMines.Take(i + 1, j + 1)) { // Verifica que el espacio esta en la lista enlazada con minas
                    cantminas++; // Se agrega al contador
                }
            }
            if (CheckRange(i - 1, j + 1)) { // Verifica que el boton este en el tablero
                if (Computer.ListLinkMines.Take(i - 1, j + 1)) { // Verifica que el espacio esta en la lista enlazada con minas
                    cantminas++; // Se agrega al contador
                }
            }
            if (CheckRange(i + 1, j)) { // Verifica que el boton este en el tablero
                if (Computer.ListLinkMines.Take(i + 1, j)) { // Verifica que el espacio esta en la lista enlazada con minas
                    cantminas++; // Se agrega al contador
                }
            }
            if (CheckRange(i - 1, j)) { // Verifica que el boton este en el tablero
                if (Computer.ListLinkMines.Take(i - 1, j)) { // Verifica que el espacio esta en la lista enlazada con minas
                    cantminas++; // Se agrega al contador
                }
            }
            if (CheckRange(i, j - 1)) { // Verifica que el boton este en el tablero
                if (Computer.ListLinkMines.Take(i, j - 1)) { // Verifica que el espacio esta en la lista enlazada con minas
                    cantminas++; // Se agrega al contador
                }
            }
            if (CheckRange(i + 1, j - 1)) { // Verifica que el boton este en el tablero
                if (Computer.ListLinkMines.Take(i + 1, j - 1)) { // Verifica que el espacio esta en la lista enlazada con minas
                    cantminas++; // Se agrega al contador
                }
            }
            if (CheckRange(i - 1, j - 1)) { // Verifica que el boton este en el tablero
                if (Computer.ListLinkMines.Take(i - 1, j - 1)) { // Verifica que el espacio esta en la lista enlazada con minas
                    cantminas++; // Se agrega al contador
                }
            }
            return cantminas; // Se devuelve la cantidad de minas cercanas
        }
        /**
         * El metodo TakeRevealProximity almacena los espacios revelados proximos al boton clickeado
         * @param i fila del boton tocado
         * @param j columna del Boton tocado
         * @return cantidad de espacios proximos que se revelan al tocar el boton
         */
        public static int TakeRevealProximity ( int i, int j){
            int cantrevelados = 0; // Declara la cantidad de espacios revelados
            if (CheckRange(i, j + 1)) { // Verifica que el boton este en el tablero
                if (Tablerovalue[i][j + 1].Clean_ == true) { // Determina que el espacio ha sido revelado
                    cantrevelados++; // Se agrega al contador
                }
            }
            if (CheckRange(i + 1, j + 1)) { // Verifica que el boton este en el tablero
                if (Tablerovalue[i + 1][j + 1].Clean_ == true) { // Determina que el espacio ha sido revelado
                    cantrevelados++; // Se agrega al contador
                }
            }
            if (CheckRange(i - 1, j + 1)) { // Verifica que el boton este en el tablero
                if (Tablerovalue[i - 1][j + 1].Clean_ == true) { // Determina que el espacio ha sido revelado
                    cantrevelados++; // Se agrega al contador
                }
            }
            if (CheckRange(i + 1, j)) { // Verifica que el boton este en el tablero
                if (Tablerovalue[i + 1][j].Clean_ == true) { // Determina que el espacio ha sido revelado
                    cantrevelados++; // Se agrega al contador
                }
            }
            if (CheckRange(i - 1, j)) { // Verifica que el boton este en el tablero
                if (Tablerovalue[i - 1][j].Clean_ == true) { // Determina que el espacio ha sido revelado
                    cantrevelados++; // Se agrega al contador
                }
            }
            if (CheckRange(i, j - 1)) { // Verifica que el boton este en el tablero
                if (Tablerovalue[i][j - 1].Clean_ == true) { // Determina que el espacio ha sido revelado
                    cantrevelados++; // Se agrega al contador
                }
            }
            if (CheckRange(i + 1, j - 1)) { // Verifica que el boton este en el tablero
                if (Tablerovalue[i + 1][j - 1].Clean_ == true) { // Determina que el espacio ha sido revelado
                    cantrevelados++; // Se agrega al contador
                }
            }
            if (CheckRange(i - 1, j - 1)) { // Verifica que el boton este en el tablero
                if (Tablerovalue[i - 1][j - 1].Clean_ == true) { // Determina que el espacio ha sido revelado
                    cantrevelados++; // Se agrega al contador
                }
            }
            return cantrevelados; // Se devuelve la cantidad de espacios revelados
        }
        /**
         * El metodo SetStackSuggestions coloca sugerencias con espacios seguros
         * utilizando la lista de espacios seguros.
         * Obtiene todos los valores de la lista
         * segura y se les hace push a la pila.
         */
        public static void SetStackSuggestions () {
            Computer.SetListLinkMines(); // Verifica la lista con minas
            Computer.SetListLinkTotal(); // Verifica la lista con todos los posibles espacios
            Computer.SetListLinkSafePrivate(); // Crea la lista segura no visible
            if (Computer.ListLinkSafe.Clean() == false) { // Si la lista segura esta vacia
                StackSuggestions.Push(Computer.ListLinkSafe.GetFirst()); // Se elige una posicion al azar
            }
        }

        /**
         * El metodo CleanStack limpia la pila de sugerencias
         * @param Pila Es una pila
         */
        public static void CleanStack (Stack Pila){
            while (Pila.Size() > 0) { // Si el tamaño de la pila es mayor a 0
                Pila.Pop(); // Toma el ultimo dato de la pila
            }
        }

        /**
         * El metodoprintListLink imprime el la consola las listas enlazadas
         * @param List una lista enlazada de arreglos bidimensionales
         * @param SpecificLista lista especifica
         */
        public static void PrintListLink (ListLink List, String SpecificLista){
            int counter = 0; // Declara un contador
            System.out.print("Contenido de " + SpecificLista + " ");
            while (counter < List.Size()) { // Si se cumple que el contador es menor al tamaño de la lista
                System.out.print("(" + List.Get(counter)[0] + "," + List.Get(counter)[1] + ")"); // Imprime los valores del tablero en la consola
                counter++; // Aumenta el contador en 1
            }
        }
    }