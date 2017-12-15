package ScanDataReader;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * Created by lahmann on 2016-12-09.
 */
public class ScanFileReader {

    protected BufferedInputStream inputStream;

    public ScanFileReader(String fileName) throws IOException{
        this(new File(fileName));
    }

    public ScanFileReader(File file) throws IOException{
        this.inputStream = new BufferedInputStream(new FileInputStream(file));
    }

    public Frame readFrame(float frameWidth, float frameHeight, float pixelSize) throws IOException{
        Frame frame = new Frame();
        frame.setNumber(getNextInteger());
        frame.setxPosition((float) 1e-5*getNextInteger());
        frame.setyPosition((float) 1e-5*getNextInteger());

        int numTracks = getNextInteger();

        skip(4*3);      // Skip the next 12 bytes

        frame.setFocus(1e-2*getNextInteger());
        frame.setxPositionIndex(getNextInteger());
        frame.setyPositionIndex(getNextInteger());

        frame.setFrameWidth(frameWidth);
        frame.setFrameHeight(frameHeight);
        frame.setPixelSize(pixelSize);

        ArrayList<Track> tracks = readTracks(frame, numTracks);
        frame.setTracks(tracks);

        return frame;
    }

    public ArrayList<Track> readTracks(Frame frame, int numTracks) throws IOException{

        /**
         * Grab all of the values to be sorted / converted later
         */

        float pixelSize   = frame.getPixelSize();
        float frameWidth  = frame.getFrameWidth();
        float frameHeight = frame.getFrameHeight();

        Short[] d = getShortArray(numTracks);
        byte[] e = getBytes(numTracks);
        byte[] c = getBytes(numTracks);
        byte[] a = getBytes(numTracks);
        Short[] x = getShortArray(numTracks);
        Short[] y = getShortArray(numTracks);

        ArrayList<Track> tracks = new ArrayList<>(numTracks);
        for (int i = 0; i < numTracks; i++){
            float diameter = 100 * d[i] * pixelSize;
            float xPosition = (float) (frame.getxPosition() + 0.5*frameWidth  - x[i]*pixelSize);
            float yPosition = (float) (frame.getyPosition() - 0.5*frameHeight + y[i]*pixelSize);

            Track track = new Track();
            track.setDiameter(diameter);
            track.setxPosition(xPosition);
            track.setyPosition(yPosition);
            track.setEccentricity(e[i]);
            track.setContrast(c[i]);
            track.setAverageContrast(a[i]);

            tracks.add(track);
        }

        return tracks;
    }

    public Integer getNextInteger() throws IOException{
        byte[] bytes = getBytes(Integer.BYTES);
        bytes = reverseArray(bytes);
        return ByteBuffer.wrap(bytes).getInt();
    }

    public Float getNextFloat() throws IOException{
        byte[] bytes = getBytes(Float.BYTES);
        bytes = reverseArray(bytes);
        return ByteBuffer.wrap(bytes).getFloat();
    }

    public Short getNextShort() throws IOException{
        byte[] bytes = getBytes(Short.BYTES);
        bytes = reverseArray(bytes);
        return ByteBuffer.wrap(bytes).getShort();
    }

    public Byte getNextByte() throws IOException{
        byte[] bytes = getBytes(1);
        return bytes[0];
    }

    public Short[] getShortArray(int n) throws IOException{
        Short[] array = new Short[n];
        for (int i = 0; i < n; i++){
            array[i] = getNextShort();
        }
        return array;
    }

    public byte[] getBytes(int n) throws IOException{
        byte[] array = new byte[n];
        inputStream.read(array);
        return array;
    }

    public void skip(int n) throws IOException{
        getBytes(n);
    }

    public void close() throws IOException{
        this.inputStream.close();
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
