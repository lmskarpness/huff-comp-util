package com.huffman;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHandler {

    private String filenameOut;

    public FileHandler(String filenameOut) {
        this.filenameOut = filenameOut;
    }

    public void createCompressedFile(byte[] encodedData) {
        try (FileOutputStream fos = new FileOutputStream(filenameOut)) {
            fos.write(encodedData);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
