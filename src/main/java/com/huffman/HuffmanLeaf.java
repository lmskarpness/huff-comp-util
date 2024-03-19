package com.huffman;

public class HuffmanLeaf extends HuffmanNode {

    private char symbol;
    public HuffmanLeaf(int frequency, char symbol) {
        super(frequency);
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}
