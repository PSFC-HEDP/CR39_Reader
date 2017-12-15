package ScanDataReader;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by lahmann on 2016-12-08.
 */
public class Frame {

    private Integer number;
    private Float xPosition;
    private Float yPosition;
    private Integer xPositionIndex;
    private Integer yPositionIndex;
    private Double focus;

    private Float frameWidth;
    private Float frameHeight;
    private Float pixelSize;

    private ArrayList<Track> tracks = new ArrayList<>();

    /**
     * Setters
     */

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setxPosition(Float xPosition) {
        this.xPosition = xPosition;
    }

    public void setyPosition(Float yPosition) {
        this.yPosition = yPosition;
    }

    public void setxPositionIndex(Integer xPositionIndex) {
        this.xPositionIndex = xPositionIndex;
    }

    public void setyPositionIndex(Integer yPositionIndex) {
        this.yPositionIndex = yPositionIndex;
    }

    public void setFocus(Double focus) {
        this.focus = focus;
    }

    public void setFrameWidth(Float frameWidth) {
        this.frameWidth = frameWidth;
    }

    public void setFrameHeight(Float frameHeight) {
        this.frameHeight = frameHeight;
    }

    public void setPixelSize(Float pixelSize) {
        this.pixelSize = pixelSize;
    }

    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }

    public void addTrack(Track track){
        tracks.add(track);
    }


    /**
     * Getters
     */

    Integer getNumber() {
        return number;
    }

    Float getxPosition() {
        return xPosition;
    }

    Float getyPosition() {
        return yPosition;
    }

    Rectangle2D getBounds(){
        double x = xPosition - 0.5 * frameWidth;
        double y = yPosition - 0.5 * frameHeight;
        return new Rectangle2D.Double(x, y, frameWidth, frameHeight);
    }

    Integer getxPositionIndex() {
        return xPositionIndex;
    }

    Integer getyPositionIndex() {
        return yPositionIndex;
    }

    Double getFocus() {
        return focus;
    }

    Float getFrameWidth() {
        return frameWidth;
    }

    Float getFrameHeight() {
        return frameHeight;
    }

    Float getPixelSize() {
        return pixelSize;
    }

    ArrayList<Track> getTracks(AnalysisCuts cuts){

        ArrayList<Track> filteredTracks = new ArrayList<>();

        for (Track track : tracks){
            if (cuts.isUseDiameterLimits()){
                if (track.diameter < cuts.getMinDiameter())    continue;
                if (track.diameter > cuts.getMaxDiameter())    continue;
            }

            if (cuts.isUseContrastLimits()){
                int testContrast;
                if (cuts.isUseAverageContrast()){
                    testContrast = track.averageContrast;
                }else{
                    testContrast = track.contrast;
                }

                if (testContrast < cuts.getMinContrast())      continue;
                if (testContrast > cuts.getMaxContrast())      continue;
            }

            if (cuts.isUseEccentricityLimits()){
                if (track.eccentricity < cuts.getMinEccentricity())    continue;
                if (track.eccentricity > cuts.getMaxEccentricity())    continue;
            }

            /*
            if (cuts.isUseXLimits()){
                if (track.xPosition < cuts.getMinX())      continue;
                if (track.xPosition > cuts.getMaxX())      continue;
            }

            if (cuts.isUseYLimits()){
                if (track.yPosition < cuts.getMinY())      continue;
                if (track.yPosition > cuts.getMaxY())      continue;
            }
            */

            filteredTracks.add(track);
        }

        return filteredTracks;
    }

    ArrayList<Track> getTracks() {
        return tracks;
    }
}
