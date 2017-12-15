package ScanDataReader;

import java.awt.geom.Rectangle2D;

/**
 * Created by lahmann on 2016-12-12.
 */
public class AnalysisCuts {

    private double minDiameter, maxDiameter;
    private double minX, maxX;
    private double minY, maxY;
    private int minContrast, maxContrast;
    private int minEccentricity, maxEccentricity;

    private boolean useAverageContrast = false;
    private boolean useDiameterLimits = false;
    private boolean useContrastLimits = false;
    private boolean useEccentricityLimits = false;
    private boolean useXLimits = false;
    private boolean useYLimits = false;

    /**
     * Prebuilt standard cut object
     */
    public static AnalysisCuts standardNeutronCuts(){
        AnalysisCuts cuts = new AnalysisCuts();
        cuts.setContrastLimits(0, 35);
        cuts.setEccentricityLimits(0, 35);
        cuts.setDiameterLimits(0.0, 100.0);
        return cuts;
    }


    /**
     * Setters
     */
    public void setDiameterLimits(double minDiameter, double maxDiameter){
        useDiameterLimits = true;
        this.minDiameter = minDiameter;
        this.maxDiameter = maxDiameter;
    }

    public void setContrastLimits(int minContrast, int maxContrast){
        useContrastLimits = true;
        this.minContrast = minContrast;
        this.maxContrast = maxContrast;
    }

    public void setEccentricityLimits(int minEccentricity, int maxEccentricity){
        useEccentricityLimits = true;
        this.minEccentricity = minEccentricity;
        this.maxEccentricity = maxEccentricity;
    }

    public void setXLimits(double minX, double maxX){
        useXLimits = true;
        this.minX = minX;
        this.maxX = maxX;
    }

    public void setYLimits(double minY, double maxY){
        useYLimits = true;
        this.minY = minY;
        this.maxY = maxY;
    }

    public void setAreaLimits(Rectangle2D limits){
        useXLimits = true;
        useYLimits = true;

        minX = limits.getMinX();
        maxX = limits.getMaxX();
        minY = limits.getMinY();
        maxY = limits.getMaxY();
    }

    public void setUseAverageContrast(boolean useAverageContrast) {
        this.useAverageContrast = useAverageContrast;
    }

    public void setUseDiameterLimits(boolean useDiameterLimits) {
        this.useDiameterLimits = useDiameterLimits;
    }

    public void setUseContrastLimits(boolean useContrastLimits) {
        this.useContrastLimits = useContrastLimits;
    }

    public void setUseEccentricityLimits(boolean useEccentricityLimits) {
        this.useEccentricityLimits = useEccentricityLimits;
    }

    public void setUseXLimits(boolean useXLimits) {
        this.useXLimits = useXLimits;
    }

    public void setUseYLimits(boolean useYLimits) {
        this.useYLimits = useYLimits;
    }

    /**
     * Getters
     */
    public double getMinDiameter() {
        return minDiameter;
    }

    public double getMaxDiameter() {
        return maxDiameter;
    }

    public double getMinX() {
        return minX;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMaxY() {
        return maxY;
    }

    public int getMinContrast() {
        return minContrast;
    }

    public int getMaxContrast() {
        return maxContrast;
    }

    public int getMinEccentricity() {
        return minEccentricity;
    }

    public int getMaxEccentricity() {
        return maxEccentricity;
    }

    public boolean isUseAverageContrast() {
        return useAverageContrast;
    }

    public boolean isUseDiameterLimits() {
        return useDiameterLimits;
    }

    public boolean isUseContrastLimits() {
        return useContrastLimits;
    }

    public boolean isUseEccentricityLimits() {
        return useEccentricityLimits;
    }

    public boolean isUseXLimits() {
        return useXLimits;
    }

    public boolean isUseYLimits() {
        return useYLimits;
    }
}
