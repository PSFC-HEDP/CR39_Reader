package ScanDataReader;

/**
 * Created by lahmann on 2016-12-09.
 */

//TODO: Accept other types like doubles and ints
public class Track {

    protected Float diameter;
    protected Byte  contrast;
    protected Byte  averageContrast;
    protected Byte  eccentricity;
    protected Float xPosition;
    protected Float yPosition;

    public void setDiameter(Float diameter) {
        this.diameter = diameter;
    }

    public void setContrast(Byte contrast) {
        this.contrast = contrast;
    }

    public void setAverageContrast(Byte averageContrast) {
        this.averageContrast = averageContrast;
    }

    public void setEccentricity(Byte eccentricity) {
        this.eccentricity = eccentricity;
    }

    public void setxPosition(Float xPosition) {
        this.xPosition = xPosition;
    }

    public void setyPosition(Float yPosition) {
        this.yPosition = yPosition;
    }

    public Float getDiameter() {
        return diameter;
    }

    public Byte getContrast() {
        return contrast;
    }

    public Byte getAverageContrast() {
        return averageContrast;
    }

    public Byte getEccentricity() {
        return eccentricity;
    }

    public Float getxPosition() {
        return xPosition;
    }

    public Float getyPosition() {
        return yPosition;
    }
}
