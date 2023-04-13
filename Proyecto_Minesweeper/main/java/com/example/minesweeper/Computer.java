package com.example.minesweeper;

import java.util.Random;
public class Computer extends Minesweeper {
    static boolean Turn = false; // Variable que denota el turno de la computadora
    static boolean DummyInit = false; // Variable que determina si el dummy se inicializo
    static boolean AdvancedInit = false; // Variable que determina si el avanzado se inicializo
    static ListLink ListLinkMines = new ListLink(); // Lista enlazada de minas
    static ListLink ListLinkSafe = new ListLink(); // Lista enlazada de posiciones sin minas
    static ListLink ListLinkTotal = new ListLink(); // lista enlazada con el total de posibles posiciones
    static ListLink ListLinkNoSafe = new ListLink(); // Lista enlazada con posiciones que no saben si tienen una mina

    static Random computerchoice = new Random(); // Se crea un objeto random
    /**
     * Se crea el metodo DummyMode
     * para que elija el espacio
     */
    public static void DummyMode() {
        if (Turn == true) { // Determina que es el turno del Dummy
            int randi = computerchoice.nextInt(8); // Elije un indice i  aleatorio
            int randj = computerchoice.nextInt(8); // Elije un indice j aleatorio
            while (Tablero.Tablerovalue[randi][randj].Clean_ == true) { // Elije un espacio sin revelar
                randi = computerchoice.nextInt(8); // Elije un indice i aleatorio
                randj = computerchoice.nextInt(8); // Elije un indice j aleatorio
            }
            ChooseDummy(randi, randj); // Revela el espacio seleccionado
            Turn = false; // Determina que finalizo el turno del Dummy
        }
    }

    /**
     * Se crea el metodo AdvancedMode
     * para que elija el espacio
     */
    public static void AdvancedMode() {
        if (Turn == true) { // Determina que es el turno del avanzado
            SetListLinkMines(); // Obtiene la lista enlazada con las minas
            SetListLinkTotal(); // Obtiene la lista enlazada con las todos los posibles espacios
            Tablero.PrintListLink(ListLinkTotal, "ListLink general"); // Imprime la lista enlazada con las todos los posibles espacios
            System.out.println(" ");
            SetListLinksSegIncert(); // Crea una lista enlazada segura y con incertidumbre
            UpdateListLink(ListLinkSafe); // Actualiza la lista enlazada segura
            UpdateListLink(ListLinkNoSafe); // Actualiza la lista enlazada incertidumbre
            if (ListLinkSafe.Clean()) { // La lista enlazada segura esta vacia
                ChooseAdvanced(ListLinkNoSafe.GetFirst()[0], ListLinkNoSafe.GetFirst()[1]); // Se elige un espacio incertidumbre
                ListLinkNoSafe.DeleteFirst2(); // Se elimina el espacio de la lista
            }
            else { // La lista enlazada segura no esta vacia
                ChooseAdvanced(ListLinkSafe.GetFirst()[0], ListLinkSafe.GetFirst()[1]); // Seleccion un espacio seguro
                ListLinkSafe.DeleteFirst2(); // Se elimina el espacio de la lista
            }
            Turn = false; // Determina que finalizo el turno del Avanzado
        }
    }
    /**
     * Se crea el metodo AddCloseNoReveal
     * para agregar los espacios proximos sin revelar
     * @param i fila del espacio
     * @param j columna del espacio
     */
    public static void AddCloseNoReveal(int i, int j) {
        if (Tablero.CheckRange(i, j+1)) { // Se verifica que los indices no se salgan del arreglo
            if (Tablero.Tablerovalue[i][j+1].Clean_ == false) { // El espacio no esta revelado
                if (ListLinkMines.Take(i, j+1) == false) { // El espacio no se encuentra ya en la lista enlazada de minas
                    ListLinkMines.InsertFirst(new int [] {i,j+1}); // Se agrega a la Lista enlazada con minas
                }
            }
        }
        if (Tablero.CheckRange(i+1, j+1)) { // Se verifica que los indices no se salgan del arreglo
            if (Tablero.Tablerovalue[i+1][j+1].Clean_ == false) { // El espacio no esta revelado
                if (ListLinkMines.Take(i+1, j+1) == false) {  //  El espacio no se encuentra ya en la lista enlazada de minas
                    ListLinkMines.InsertFirst(new int [] {i+1,j+1}); // Se agrega a la Lista enlazada con minas
                }
            }
        }
        if (Tablero.CheckRange(i-1, j+1)) { // Se verifica que los indices no se salgan del arreglo
            if (Tablero.Tablerovalue[i-1][j+1].Clean_ == false) { // El espacio no esta revelado
                if (ListLinkMines.Take(i-1, j+1) == false) {  // El espacio no se encuentra ya en la lista enlazada de minas
                    ListLinkMines.InsertFirst(new int [] {i-1,j+1}); // Se agrega a la Lista enlazada con minas
                }
            }
        }
        if (Tablero.CheckRange(i+1, j)) { // Se verifica que los indices no se salgan del arreglo
            if (Tablero.Tablerovalue[i+1][j].Clean_ == false) { // El espacio no esta revelado
                if (ListLinkMines.Take(i+1, j) == false) {  // El espacio no se encuentra ya en la lista enlazada de minas
                    ListLinkMines.InsertFirst(new int [] {i+1,j}); // Se agrega a la Lista enlazada con minas
                }
            }
        }
        if (Tablero.CheckRange(i-1, j)) { // Se verifica que los indices no se salgan del arreglo
            if (Tablero.Tablerovalue[i-1][j].Clean_ == false) { // El espacio no esta revelado
                if (ListLinkMines.Take(i-1, j) == false) {  // El espacio no se encuentra ya en la lista enlazada de minas
                    ListLinkMines.InsertFirst(new int [] {i-1,j}); // Se agrega a la Lista enlazada con minas
                }
            }
        }
        if (Tablero.CheckRange(i, j-1)) { // Se verifica que los indices no se salgan del arreglo
            if (Tablero.Tablerovalue[i][j-1].Clean_ == false) { // El espacio no esta revelado
                if (ListLinkMines.Take(i, j-1) == false) {  // El espacio no se encuentra ya en la lista enlazada de minas
                    ListLinkMines.InsertFirst(new int [] {i,j-1}); // Se agrega a la Lista enlazada con minas
                }
            }
        }
        if (Tablero.CheckRange(i+1, j-1)) { // Se verifica que los indices no se salgan del arreglo
            if (Tablero.Tablerovalue[i+1][j-1].Clean_ == false) { // El espacio no esta revelado
                if (ListLinkMines.Take(i+1, j-1) == false) {  // El espacio no se encuentra ya en la lista enlazada de minas
                    ListLinkMines.InsertFirst(new int [] {i+1,j-1}); // Se agrega a la Lista enlazada con minas
                }
            }
        }
        if (Tablero.CheckRange(i-1, j-1)) { // Se verifica que los indices no se salgan del arreglo
            if (Tablero.Tablerovalue[i-1][j-1].Clean_ == false) { // El espacio no esta revelado
                if (ListLinkMines.Take(i-1, j-1) == false) {  // El espacio no se encuentra ya en la lista enlazada de minas
                    ListLinkMines.InsertFirst(new int [] {i-1,j-1}); // Se agrega a la Lista enlazada con minas
                }
            }
        }
    }
    /**
     * Se crea el metodo AddSafe para verificar y
     * añadir espacios seguros a la lista enlazada segura
     * @param i fila del espacio
     * @param j columna del espacio
     */
    public static void AddSafe(int i, int j) {
        if (Tablero.CheckRange(i, j + 1)) { // Se verifica que los indices no se salgan del arreglo
            if (Tablero.Tablerovalue[i][j + 1].Clean_ == true) { // Verifica si el espacio de la derecha esta revelado
                if (Tablero.Tablerovalue[i][j + 1].proxybomb > 0) { // Verifica si la cantidad de minas proximas del espacio de la derecha es mayor a 0
                    if (Tablero.Tablerovalue[i][j + 1].proxybomb == Tablero.TakeMinesProximity(i, j + 1)) { // Verifica si la cantidad de minas proximas del espacio de la derecha es igual a la cantidad de minas proximas tomando en cuenta la lista enlazada con minas
                        if (ListLinkMines.Take(i, j) == false) { // Verifica si el espacio no esta en la lista enlazada con minas
                            if (ListLinkSafe.Take(i, j) == false) { // Verifica si el espacio no esta ya en la lista enlazada segura
                                ListLinkSafe.InsertFirst(new int[]{i, j}); // Se agrega el espacio a la listaenlazada segura
                            }
                        }
                    }
                }
            }
        }
        if (Tablero.CheckRange(i + 1, j + 1)) { // Se verifica que los indices no se salgan del arreglo
            if (Tablero.Tablerovalue[i + 1][j + 1].Clean_ == true) { // Verifica si el espacio de abajo a la derecha esta revelado
                if (Tablero.Tablerovalue[i + 1][j + 1].proxybomb > 0) { // Verifica si la cantidad de minas proximas del espacio de la par es mayor a 0
                    if (Tablero.Tablerovalue[i + 1][j + 1].proxybomb == Tablero.TakeMinesProximity(i + 1, j + 1)) { // Verifica si la cantidad de minas proximas del espacio de la par es igual a la cantidad de minas adyacentes tomando en cuenta la lista enlazada con de minas
                        if (ListLinkMines.Take(i, j) == false) { // Verifica si el espacio no esta en la lista enlazada con minas
                            if (ListLinkSafe.Take(i, j) == false) { // Verifica si el espacio no esta ya en la lista enlazada segura
                                ListLinkSafe.InsertFirst(new int[]{i, j}); // Se agrega el espacio a la listaenlazada segura
                            }
                        }
                    }
                }
            }
            if (Tablero.CheckRange(i - 1, j + 1)) { // Se verifica que los indices no se salgan del arreglo
                if (Tablero.Tablerovalue[i - 1][j + 1].Clean_ == true) { // Verifica si el espacio de arriba a la derecha esta revelado
                    if (Tablero.Tablerovalue[i - 1][j + 1].proxybomb > 0) { // Verifica si la cantidad de minas proximas del espacio de la par es mayor a 0
                        if (Tablero.Tablerovalue[i - 1][j + 1].proxybomb == Tablero.TakeMinesProximity(i - 1, j + 1)) { // Verifica si la cantidad de minas proximas del espacio de la par es igual a la cantidad de minas adyacentes tomando en cuenta la lista enlazada con de minas
                            if (ListLinkMines.Take(i, j) == false) { // Verifica si el espacio no esta en la lista enlazada con minas
                                if (ListLinkSafe.Take(i, j) == false) { // Verifica si el espacio no esta ya en la lista enlazada segura
                                    ListLinkSafe.InsertFirst(new int[]{i, j}); // Se agrega el espacio a la listaenlazada segura
                                }
                            }
                        }
                    }
                }
            }
            if (Tablero.CheckRange(i + 1, j)) { // Se verifica que los indices no se salgan del arreglo
                if (Tablero.Tablerovalue[i + 1][j].Clean_ == true) { // Verifica si el espacio de abajo esta revelado
                    if (Tablero.Tablerovalue[i + 1][j].proxybomb > 0) { // Verifica si la cantidad de minas proximas del espacio de la par es mayor a 0
                        if (Tablero.Tablerovalue[i + 1][j].proxybomb == Tablero.TakeMinesProximity(i + 1, j)) { // Verifica si la cantidad de minas proximas del espacio es igual a la cantidad de minas adyacentes tomando en cuenta la lista enlazada con de minas
                            if (ListLinkMines.Take(i, j) == false) { // Verifica si el espacio no esta en la lista enlazada con minas
                                if (ListLinkSafe.Take(i, j) == false) { // Verifica si el espacio no esta ya en la lista enlazada segura
                                    ListLinkSafe.InsertFirst(new int[]{i, j}); // Se agrega el espacio a la listaenlazada segura
                                }
                            }
                        }
                    }
                }
            }
            if (Tablero.CheckRange(i - 1, j)) { // Se verifica que los indices no se salgan del arreglo
                if (Tablero.Tablerovalue[i - 1][j].Clean_ == true) { // Verifica si el espacio de arriba esta revelado
                    if (Tablero.Tablerovalue[i - 1][j].proxybomb > 0) { // Verifica si la cantidad de minas proximas del espacioo de la par es mayor a 0
                        if (Tablero.Tablerovalue[i - 1][j].proxybomb == Tablero.TakeMinesProximity(i - 1, j)) { // Verifica si la cantidad de minas proximas del espacio es igual a la cantidad de minas adyacentes tomando en cuenta la lista enlazada con de minas
                            if (ListLinkMines.Take(i, j) == false) { // Verifica si el espacio no esta en la lista enlazada con minas
                                if (ListLinkSafe.Take(i, j) == false) { // Verifica si el espacio no esta ya en la lista enlazada segura
                                    ListLinkSafe.InsertFirst(new int[]{i, j}); // Se agrega el espacio a la listaenlazada segura
                                }
                            }
                        }
                    }
                }
            }
            if (Tablero.CheckRange(i, j - 1)) { // Se verifica que los indices no se salgan del arreglo
                if (Tablero.Tablerovalue[i][j - 1].Clean_ == true) { // Verifica si el espacio de la izquierda esta revelado
                    if (Tablero.Tablerovalue[i][j - 1].proxybomb > 0) { // Verifica si la cantidad de minas proximas del espacio de la par es mayor a 0
                        if (Tablero.Tablerovalue[i][j - 1].proxybomb == Tablero.TakeMinesProximity(i, j - 1)) { // Verifica si la cantidad de minas proximas del espacio es igual a la cantidad de minas adyacentes tomando en cuenta la lista enlazada con de minas
                            if (ListLinkMines.Take(i, j) == false) { // Verifica si el espacio no esta en la lista enlazada con minas
                                if (ListLinkSafe.Take(i, j) == false) { // Verifica si el espacio no esta ya en la lista enlazada segura
                                    ListLinkSafe.InsertFirst(new int[]{i, j}); // Se agrega el espacio a la lista enlazada segura
                                }
                            }
                        }
                    }
                }
            }
            if (Tablero.CheckRange(i + 1, j - 1)) { // Se verifica que los indices no se salgan del arreglo
                if (Tablero.Tablerovalue[i + 1][j - 1].Clean_ == true) { // Verifica si el espacio de abajo a la izquierda esta revelado
                    if (Tablero.Tablerovalue[i + 1][j - 1].proxybomb > 0) { // Verifica si la cantidad de minas proximas del espacio de la par es mayor a 0
                        if (Tablero.Tablerovalue[i + 1][j - 1].proxybomb == Tablero.TakeMinesProximity(i + 1, j - 1)) { // Verifica si la cantidad de minas proximas del espacio de la par es igual a la cantidad de minas adyacentes tomando en cuenta la lista enlazada con de minas
                            if (ListLinkMines.Take(i, j) == false) { // Verifica si el espacio no esta en la lista enlazada con minas
                                if (ListLinkSafe.Take(i, j) == false) { // Verifica si el espacio no esta ya en la lista enlazada segura
                                    ListLinkSafe.InsertFirst(new int[]{i, j}); // Se agrega el espacio a la lista enlazada segura
                                }
                            }
                        }
                    }
                }
            }
            if (Tablero.CheckRange(i - 1, j - 1)) { // Se verifica que los indices no se salgan del arreglo
                if (Tablero.Tablerovalue[i - 1][j - 1].Clean_ == true) { // Verifica si el espacio de arriba a la izquierda esta revelado
                    if (Tablero.Tablerovalue[i - 1][j - 1].proxybomb > 0) { // Verifica si la cantidad de minas proximas del espacio de la par es mayor a 0
                        if (Tablero.Tablerovalue[i - 1][j - 1].proxybomb == Tablero.TakeMinesProximity(i - 1, j - 1)) { // Verifica si la cantidad de minas proximas del espacio de la par es igual a la cantidad de minas adyacentes tomando en cuenta la lista enlazada con de minas
                            if (ListLinkMines.Take(i, j) == false) { // Verifica si el espacio no esta en la lista enlazada con minas
                                if (ListLinkSafe.Take(i, j) == false) { // Verifica si el espacio no esta ya en la lista enlazada segura
                                    ListLinkSafe.InsertFirst(new int[]{i, j}); // Se agrega el espacio a la lista enlazada segura
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    /**
     * Se crea el metodo UpdateListLink para
     * que elimine los espacios revelados.
     * @param ListLink es una lista enlazada de arreglo bidimensional
     */
    public static void UpdateListLink(ListLink ListLink){ // Elimina los espacios revelados
        for (int i=0; i<=7; i++) {
            for (int j=0; j<=7; j++) {
                if (Tablero.Tablerovalue[i][j].Clean_ == true && ListLink.Take(i, j)) { // Verifica si el espacio revelado se encuentra en la lista
                    ListLink.Delete(i, j); // Elimina el espacio en la lista
                }
            }
        }
    }
    /**
     * Se crea el metodo SetListLinkMines para que indique
     * las minas proximas al boton presionado.
     */
    static void SetListLinkMines() {
        for (int i=0; i<=7; i++) {
            for (int j=0; j<=7; j++) {
                if (Tablero.Tablerovalue[i][j].Clean_ == true && Tablero.Tablerovalue[i][j].proxybomb > 0) { // Verifica si el espacio revelado tiene minas proximas
                    if (Tablero.Tablerovalue[i][j].proxybomb + Tablero.TakeRevealProximity(i, j) == 8) { // Verifica si la cantidad de minas proximas y la cantidad de espacios revelados es igual a 8
                        AddCloseNoReveal(i, j); // Se agregan los espacios sin revelar a la lista enlazada
                    }
                }
            }
        }
    }
    /**
     * Se crea el metodo SetListLinksSegIncert para que genere una lista segura y un de incertidumbre
     */
    public static void SetListLinksSegIncert() {
        Random r = new Random(); // Crea un objeto aleatorio
        int numrandom; // Se declara la variable para un numero random
        while (ListLinkTotal.Clean() == false) { // Verifica si la lista enlazada general no esta vacia
            Tablero.PrintListLink(ListLinkTotal, "lista enlazada total"); // Imprime la lista enlazada total
            System.out.println(" ");
            numrandom = r.nextInt(ListLinkTotal.Size()); // Crea un numero aleatorio del tamaño de la lista enlazada total para funcionar como indice
            AddSafe(ListLinkTotal.Get(numrandom)[0], ListLinkTotal.Get(numrandom)[1]); // Se agregan los numeros del indice correspondiente a la lista enlazada segura
            if (ListLinkSafe.Take(ListLinkTotal.Get(numrandom)[0], ListLinkTotal.Get(numrandom)[1]) == false) { // Verifica que el espacio no este en la lista enlazada segura
                if (ListLinkNoSafe.Take(ListLinkTotal.Get(numrandom)[0], ListLinkTotal.Get(numrandom)[1]) == false) { // Verifica que el espacio no este ya en la lista enlazada no segura
                    ListLinkNoSafe.InsertFirst(new int[] {ListLinkTotal.Get(numrandom)[0], ListLinkTotal.Get(numrandom)[1]}); // Verifica que se agregan a la no segura
                }
            }
            Tablero.PrintListLink(ListLinkSafe, "lista enlazada segura"); // Imprime la lista enlazada segura
            System.out.println(" ");
            Tablero.PrintListLink(ListLinkNoSafe, "lista enlazada incertidumbre"); // Imprime la lista enlazada no segura
            System.out.println(" ");
            ListLinkTotal.DelSpecific(numrandom); // Elimina el numero elegido de la lista enlazada total
            Tablero.PrintListLink(ListLinkTotal, "Lista enlazada total"); // se imprime la Lista enlazada total
            System.out.println(" ");
        }
    }

    public static void SetListLinkSafePrivate() {
        Random r = new Random(); // Crea un objeto random
        int numrandom; // Se declara la variable para un numero random
        while (ListLinkTotal.Clean() == false) { // Verifica si la lista enlazada total no esta vacia
            numrandom = r.nextInt(ListLinkTotal.Size()); // Crea un numero aleatorio del tamaño de la lista enlazada total para funcionar como indice
            AddSafe(ListLinkTotal.Get(numrandom)[0], ListLinkTotal.Get(numrandom)[1]); // Se agregan los numeros del indice correspondiente a la lista enlazada segura
            if (ListLinkSafe.Take(ListLinkTotal.Get(numrandom)[0], ListLinkTotal.Get(numrandom)[1]) == false) { // Verifica que el espacio no este en la lista enlazada segura
                if (ListLinkNoSafe.Take(ListLinkTotal.Get(numrandom)[0], ListLinkTotal.Get(numrandom)[1]) == false) { // Verifica que el espacio no este ya en la lista enlazada no segura
                    ListLinkNoSafe.InsertFirst(new int[] {ListLinkTotal.Get(numrandom)[0], ListLinkTotal.Get(numrandom)[1]}); // Verifica que se agregan a la no segura
                }
            }
            ListLinkTotal.DelSpecific(numrandom); // Elimina el numero elegido de la lista enlazada total
        }
    }

    /**
     * Se crea el metodo SetListLinkTotal para que agregue los espacios sin revelar
     */
    public static void SetListLinkTotal() {
        for (int i=0; i<=7; i++) {
            for (int j=0; j<=7; j++) {
                if (Tablero.Tablerovalue[i][j].Clean_ == false) { // Verifica si el espacio no se ha revelado
                    ListLinkTotal.InsertFirst(new int [] {i, j}); // Agrega a la lista enlazada total
                }
            }
        }
    }
}