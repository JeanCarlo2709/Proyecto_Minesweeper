package com.example.minesweeper;

public class ListLink {
    class Node {
        private int[] data; // Valor del nodo
        private Node next; // Siguiente nodo

        public Node(int[] data) {
            this.next = null;
            this.data = data;
        }

        /**
         * El metodo getData recibe los valores del nodo
         * @return Valor del nodo
         */
        public int[] getData() {
            return this.data;
        }
        /**
         * El metodo getNext analiza el siguente nodo
         * @return nodo sucesor
         */
        public Node getNext() {
            return this.next;
        }

        /**
         * El metodo setNext coloca el siguiente nodo
         * @param node un nodo
         */
        public void setNext(Node node) {
            this.next = node;
        }
    }

    private Node head;
    private int size;

    /**
     * El metodo ListLink da accesivilidad a listas enlazadas
     */
    public ListLink() {
        this.head = null;
        this.size = 0;
    }

    /**
     * El metodo Clean verifica si la lista esta vacia
     * @return true si la lista analizada esta vacia
     */
    public boolean Clean() {
        return this.head == null;
    }

    /**
     * El metodo size obtiene el tamaño de la lista
     * @return Tamaño de la lista analizada
     */
    public int Size() {
        return this.size;
    }

    /**
     * El metodo InsertFirst inserta un nodo al frente de la lista
     * @param data nodo a insertar
     */
    public void InsertFirst(int[] data) {
        Node newNode = new Node(data);
        newNode.next = this.head;
        this.head = newNode;
        this.size++;
    }

    /**
     * El metodo DeleteFirst borra el primer nodo de la lista
     * @return Nodo borrado temporal
     */
    public int[] DeleteFirst() {
        if (this.head != null) {
            Node temp = this.head;
            this.head = this.head.next;
            this.size--;
            return temp.getData();
        }
        else {
            return null;
        }
    }

    /**
     * El metodo DeleteFirst2 borra el primer nodo de la lista de manera definitiva
     */
    public void DeleteFirst2() {
        if (this.head != null) {
            this.head = this.head.next;
            this.size--;
        }
    }

    /**
     * El metodo Delete borra un valor especifico de la lista
     * @param i primer valor de la lista que se desea borrar
     * @param j segundo valor de la lista que se desea borrar
     * @return nodo que se desea borrar
     */
    public int[] Delete(int i, int j) {
        Node current = this.head;
        Node previous = this.head;

        while (current != null) {
            if (current.getData()[0] == i && current.getData()[1] == j) {
                if (current == this.head) {
                    this.head = this.head.getNext();
                    this.size--;
                }
                else {
                    previous.setNext(current.getNext());
                    this.size--;
                }
                return current.getData();
            }
            else {
                previous = current;
                current = current.getNext();
            }
        }
        return null;
    }

    /**
     * El metodo GetFirst obtiene el primer nodo del arregla
     * @return valor del primer nodo
     */
    public int[] GetFirst() {
        if (this.head == null) {
            return null;
        }
        else {
            return this.head.getData();
        }
    }

    /**
     * El metodo Take obtiene el nodo con valores especificos
     * @param i primer valor del arreglo
     * @param j segundo valor del arreglo
     * @return true si el arreglo pertenece la lista, false si el arreglo no pertenece a la lista
     */
    public boolean Take(int i, int j) {
        Node current = this.head;
        while (current != null) {
            if (current.getData()[0] == i && current.getData()[1] == j) {
                return true;
            }
            else {
                current = current.getNext();
            }
        }
        return false;
    }
    /**
     * El metodo empty indica que la lista esta vacia
     */
    public void EmptyList() {
        this.head = null;
    }

    /**
     * El metodo getHead obtiene el primer nodo de la lista enlazada
     * @return arreglo del primer nodo
     */
    public int[] GetHead() {
        if (this.head == null) {
            return null;
        }
        else {
            return this.head.getData();
        }
    }

    /**
     * El metodo get obtiene el nodo con un indice especifico
     * @param searchValue indice del arreglo que se quiere obtener
     * @return arreglo en la posicion del indice
     */
    public int[] Get(int searchValue) {
        Node current = this.head;
        int counter = 0;
        while (current != null) {
            if (counter == searchValue) {
                return current.getData();
            }
            else {
                current = current.getNext();
                counter++;
            }
        }
        return null;
    }

    /**
     * El metodo DelSpecific elimina el nodo con el indice especificado
     * @param i indice del nodo que se desea borrar
     */
    public void DelSpecific(int i) {
        Node current = this.head;
        Node previous = this.head;
        int counter = 0;

        while (current != null) {
            if (counter == i) {
                if (current == this.head) {
                    this.head = this.head.getNext();
                    counter++;
                    this.size--;
                }
                else {
                    previous.setNext(current.getNext());
                    counter++;
                    this.size--;
                }
            }
            else {
                previous = current;
                current = current.getNext();
                counter++;
            }
        }
    }

    /**
     * El metodo ResetSize deja el tamaño en 0
     */
    public void ResetSize() {
        this.size = 0;
    }

}