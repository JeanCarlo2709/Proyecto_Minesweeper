package com.example.minesweeper;

public class Stack {
    private ListLink stacklist = new ListLink();
    private int size;

    /**
     * El metodo push inserta un elemento al tope de la pila
     * @param newElement Elemento insertado
     */
    public void Push(int[] newElement) {
        this.size++;
        this.stacklist.InsertFirst(newElement);
    }

    /**
     * El metodo pop elimina el elemento del tope de la fila
     * @return Nueva pila
     */
    public int[] Pop() {
        this.size--;
        return this.stacklist.DeleteFirst();
    }

    /**
     * El metodo peek obtiene el valor del tope de la pila
     * @return Valor del elemento en el tope
     */
    public int[] Peek() {
        return this.stacklist.GetHead();
    }

    /**
     * El metodo size obtiene el tamaño de la pila
     * @return Tamaño de la lista
     */
    public int Size() {
        return this.size;
    }

    /**
     * El metodo resetSize declara en 0 el tamaño de la fila
     */
    public void ResetSize() {
        this.size = 0;
    }
}