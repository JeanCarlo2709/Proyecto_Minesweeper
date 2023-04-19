package com.example.minesweeper;

public class ListLink {
    class Node {
        private int[] data; // Valor del nodo
        private Node next; // Siguiente nodo

        public Node(int[] data) {
            this.next = null;// Apunta siguiente a nulo
            this.data = data;//Obtiene el dato
        }

        /**
         * El metodo getData recibe los valores del nodo
         * @return Valor del nodo
         */
        public int[] getData() {
            return this.data;
        }//Da el valor del dato
        /**
         * El metodo getNext analiza el siguente nodo
         * @return nodo sucesor
         */
        public Node getNext() {
            return this.next;
        }// Da el nodo succesor

        /**
         * El metodo setNext coloca el siguiente nodo
         * @param node un nodo
         */
        public void setNext(Node node) {
            this.next = node;
        }//Coloca el nodo dentro del otro
    }

    private Node head;//Nodo cabeza
    private int size;// Variable tamaño

    /**
     * El metodo ListLink da accesivilidad a listas enlazadas
     */
    public ListLink() {
        this.head = null;//Apunta la cabeza a nulo
        this.size = 0;//Deja el tamaño en 0
    }

    /**
     * El metodo Clean verifica si la lista esta vacia
     * @return true si la lista analizada esta vacia
     */
    public boolean Clean() {
        return this.head == null;
    }//Deja la cabeza en 0 denotando una lista vacia

    /**
     * El metodo size obtiene el tamaño de la lista
     * @return Tamaño de la lista analizada
     */
    public int Size() {
        return this.size;
    }//Obtiene el tamaño

    /**
     * El metodo InsertFirst inserta un nodo al frente de la lista
     * @param data nodo a insertar
     */
    public void InsertFirst(int[] data) {
        Node newNode = new Node(data);//crea un nuevo nodo
        newNode.next = this.head;//Establece al nuevo nodo a la cabeza
        this.head = newNode;// establece al nodo como la cabeza
        this.size++;// Aumenta el tamaño
    }

    /**
     * El metodo DeleteFirst borra el primer nodo de la lista
     * @return Nodo borrado temporal
     */
    public int[] DeleteFirst() {
        if (this.head != null) {//establece la cabeza en nulo
            Node temp = this.head;//deja la cabeza como nodo temporal
            this.head = this.head.next;//Deja el nodo previo a la cabeza
            this.size--;// disminuye el tamaño
            return temp.getData();//deja el dato temporal
        }
        else {//si no se cumple
            return null;// devuelva nulo
        }
    }

    /**
     * El metodo DeleteFirst2 borra el primer nodo de la lista de manera definitiva
     */
    public void DeleteFirst2() {
        if (this.head != null) {//la cabeza es diferente de nulo
            this.head = this.head.next;//Deja el nodo previo a la cabeza
            this.size--;// disminuye el tamaño
        }
    }

    /**
     * El metodo Delete borra un valor especifico de la lista
     * @param i primer valor de la lista que se desea borrar
     * @param j segundo valor de la lista que se desea borrar
     * @return nodo que se desea borrar
     */
    public int[] Delete(int i, int j) {
        Node current = this.head;//Deja el nodo en la cabeza
        Node previous = this.head;//Deja el nodo en la cabeza

        while (current != null) {
            if (current.getData()[0] == i && current.getData()[1] == j) {// busca el nodo con el valor especifico
                if (current == this.head) {//
                    this.head = this.head.getNext();//deja la cabeza en el nodo previo
                    this.size--;// disminuye el tamaño
                }
                else {
                    previous.setNext(current.getNext());//Coloca el nodo previo como siguiente
                    this.size--;//disminuye el tamaño
                }
                return current.getData();//Obtiene el dato
            }
            else {
                previous = current;//deja al previo en el current
                current = current.getNext();//El current obtiene al siguiente
            }
        }
        return null;// devuelve nulo
    }

    /**
     * El metodo GetFirst obtiene el primer nodo del arregla
     * @return valor del primer nodo
     */
    public int[] GetFirst() {
        if (this.head == null) {//la cabeza es nula
            return null;//devuelve nulo
        }
        else {
            return this.head.getData();// obtiene el dato de la cabeza
        }
    }

    /**
     * El metodo Take obtiene el nodo con valores especificos
     * @param i primer valor del arreglo
     * @param j segundo valor del arreglo
     * @return true si el arreglo pertenece la lista, false si el arreglo no pertenece a la lista
     */
    public boolean Take(int i, int j) {
        Node current = this.head;//declara el nodo
        while (current != null) {
            if (current.getData()[0] == i && current.getData()[1] == j) {//Busca el nodo con el valor especifico
                return true;//Devuelve true
            }
            else {
                current = current.getNext();//pasa el nodo al siguiente
            }
        }
        return false;// devuelve false
    }
    /**
     * El metodo empty indica que la lista esta vacia
     */
    public void EmptyList() {
        this.head = null;
    }// deja la cabeza en nulo

    /**
     * El metodo getHead obtiene el primer nodo de la lista enlazada
     * @return arreglo del primer nodo
     */
    public int[] GetHead() {
        if (this.head == null) {//La cabeza es nula
            return null;//devuelve nulo
        }
        else {
            return this.head.getData();//Obtiene la cabeza
        }
    }

    /**
     * El metodo get obtiene el nodo con un indice especifico
     * @param searchValue indice del arreglo que se quiere obtener
     * @return arreglo en la posicion del indice
     */
    public int[] Get(int searchValue) {
        Node current = this.head;//Declara al nodo
        int counter = 0;// variable de contador
        while (current != null) {
            if (counter == searchValue) {//Busca el contador o indice con el valor requerido
                return current.getData();// cobtiene el dato
            }
            else {
                current = current.getNext();//Deja al current en el siguiente
                counter++;//aumenta el contador
            }
        }
        return null;// devuelve nulo
    }

    /**
     * El metodo DelSpecific elimina el nodo con el indice especificado
     * @param i indice del nodo que se desea borrar
     */
    public void DelSpecific(int i) {
        Node current = this.head;//declara el nodo
        Node previous = this.head;//declara el nodo
        int counter = 0;//variable de contador

        while (current != null) {
            if (counter == i) {// el contador es igual al indice
                if (current == this.head) {//el current esta a la caeza
                    this.head = this.head.getNext();//Pasa la cabeza al siguiente
                    counter++;//Aumenta el contador
                    this.size--;// reduce el tamaño
                }
                else {
                    previous.setNext(current.getNext());//Coloca el previo en el siguiene
                    counter++;//Aumenta el contador
                    this.size--;// reduce el tamaño
                }
            }
            else {
                previous = current;//el previo s igual al current
                current = current.getNext();//pasa al current al siguiente
                counter++;//Aumenta el contador
            }
        }
    }

    /**
     * El metodo ResetSize deja el tamaño en 0
     */
    public void ResetSize() {
        this.size = 0;
    }//Deja limpia la lista

}