package ScanDataReader;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * Created by lahmann on 2017-01-21.
 */
public class ScanFileWriter {

    protected BufferedOutputStream outputStream;

    public ScanFileWriter(String fileName) throws IOException {
        this(new File(fileName));
    }

    public ScanFileWriter(File file) throws IOException{
        this.outputStream = new BufferedOutputStream(new FileOutputStream(file));
    }

    public void writeFrame(Frame frame, float frameWidth, float frameHeight, float pixelSize) throws IOException{
        writeInteger(frame.getNumber());
        writeInteger((int) (1e5* frame.getxPosition()));
        writeInteger((int) (1e5* frame.getyPosition()));
        writeInteger(frame.getTracks().size());

        // Next 3 integers are junk
        writeInteger(0);
        writeInteger(0);
        writeInteger(0);

        writeInteger((int) (1e2*frame.getFocus()));
        writeInteger(frame.getxPositionIndex());
        writeInteger(frame.getyPositionIndex());

        writeTracks(frame, frameWidth, frameHeight, pixelSize);

    }

    public void writeTracks(Frame frame, float frameWidth, float frameHeight, float pixelSize) throws IOException{

        ArrayList<Track> tracks = frame.getTracks();

        short[] d = new short[tracks.size()];
        byte[] e = new byte[tracks.size()];
        byte[] c = new byte[tracks.size()];
        byte[] a = new byte[tracks.size()];
        short[] x = new short[tracks.size()];
        short[] y = new short[tracks.size()];

        for (int i = 0; i < tracks.size(); i++) {
            d[i] = (short) (0.01 * tracks.get(i).getDiameter() / pixelSize);
            e[i] = tracks.get(i).getEccentricity();
            c[i] = tracks.get(i).getContrast();
            a[i] = tracks.get(i).getAverageContrast();

            x[i] = (short) ((-tracks.get(i).getxPosition() + frame.getxPosition() + 0.5*frameWidth) / pixelSize);
            y[i] = (short) ((+tracks.get(i).getyPosition() - frame.getyPosition() + 0.5*frameHeight) / pixelSize);
        }

        writeShortArray(d);
        writeByteArray(e);
        writeByteArray(c);
        writeByteArray(a);
        writeShortArray(x);
        writeShortArray(y);
    }

    public void writeInteger(int value) throws IOException{
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        buffer.putInt(value);
        writeBytes(buffer.array());
    }

    public void writeFloat(float value) throws IOException{
        ByteBuffer buffer = ByteBuffer.allocate(Float.BYTES);
        buffer.putFloat(value);
        writeBytes(buffer.array());
    }

    public void writeShort(short value) throws IOException{
        ByteBuffer buffer = ByteBuffer.allocate(Short.BYTES);
        buffer.putShort(value);
        writeBytes(buffer.array());
    }

    public void writeByte(byte value) throws IOException{
        writeBytes(new byte[] {value});
    }

    public void writeShortArray(short[] array) throws IOException{
        for (short value : array) {
            writeShort(value);
        }
    }

    public void writeByteArray(byte[] array) throws IOException{
        for (byte value : array){
            writeByte(value);
        }
    }

    public void writeBytes(byte[] array) throws IOException{
        outputStream.write(reverseArray(array));
    }

    public void close() throws IOException{
        this.outputStream.close();
    }

    private static byte[] reverseArray(byte[] array){
        for (int i = 0; i < array.length/2; i++) {
            byte temp = array[i];
            array[i] = array[array.length-i-1];
            array[array.length-i-1] = temp;
        }
        return array;
    }
}
