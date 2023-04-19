package com.example.minesweeper;

public class Stack {
    private ListLink stacklist = new ListLink();//Declara una lista enlazada
    private int size;//variable del tamaño

    /**
     * El metodo push inserta un elemento al tope de la pila
     * @param newElement Elemento insertado
     */
    public void Push(int[] newElement) {
        this.size++;//Aunmenta el tamaño
        this.stacklist.InsertFirst(newElement);//Agrega el elemento
    }

    /**
     * El metodo pop elimina el elemento del tope de la fila
     * @return Nueva pila
     */
    public int[] Pop() {
        this.size--;//Disminuye el tamaño
        return this.stacklist.DeleteFirst();//Elimina el primer elemento
    }

    /**
     * El metodo peek obtiene el valor del tope de la pila
     * @return Valor del elemento en el tope
     */
    public int[] Peek() {
        return this.stacklist.GetHead();
    }// obtiene el elemento en el tope

    /**
     * El metodo size obtiene el tamaño de la pila
     * @return Tamaño de la lista
     */
    public int Size() {
        return this.size;
    }//Obtiene el tamaño

    /**
     * El metodo resetSize declara en 0 el tamaño de la fila
     */
    public void ResetSize() {
        this.size = 0;
    }// Declara la pila como vacia
}