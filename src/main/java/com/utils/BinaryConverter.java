package com.utils;

import java.io.IOException;
import java.io.ByteArrayOutputStream;

public class BinaryConverter {

    private ByteArrayOutputStream stream;
    private byte bitBuffer = 0;
    private byte bitsInBuffer = 0;

    public BinaryConverter() {
        this.stream = new ByteArrayOutputStream();
    }

    public byte[] getByteStream() {
        return stream.toByteArray();
    }

    public void writeBitToStream(char bitCharacter) {
        if (bitsInBuffer > 7) {
            stream.write(bitBuffer);
            bitsInBuffer = 0;
            bitBuffer = 0;
        }

        bitBuffer = (byte) ((byte) (bitBuffer << 1) | (bitCharacter == '1' ? 1 : 0));
        bitsInBuffer++;
    }

    public void flushBitBuffer() {
        if (bitsInBuffer > 0) {
            do {
                writeBitToStream('0');
            } while (bitsInBuffer != 1);
        }
    }
}
