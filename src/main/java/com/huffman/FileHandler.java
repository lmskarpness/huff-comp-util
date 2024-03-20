package com.huffman;

import java.io.File;
import java.io.FileInputStream;
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

    public void createDecompressedFile(byte[] encodedData) {
        try (FileOutputStream fos = new FileOutputStream(filenameOut)) {
            fos.write(encodedData);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] createByteArrayFromFile(String filepath) {
        File file = new File(filepath);
        byte[] fileBytes = new byte[(int) file.length()];

        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(fileBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileBytes;
    }


}
