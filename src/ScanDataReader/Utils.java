package ScanDataReader;

public class Utils {

    static double[] rebinVector(double[] oldVector, int N){

        int newLength = (int) Math.floor(oldVector.length / (double) N);
        double[] rebinedVector = new double[newLength];

        for (int i = 0; i < (oldVector.length - N+1); i += N){
            int index = (int) Math.floor(i / (double) N);

            rebinedVector[index] = 0.0;
            for (int ii = i; ii < i+N; ii++){
                rebinedVector[index] += oldVector[ii];
            }
            rebinedVector[index] /= (double) N;
        }

        return rebinedVector;
    }

    static double[][] rebinMatrix(double[][] oldMatrix, int N){

        int newYLength = (int) Math.floor(oldMatrix.length / (double) N);
        int newXLength = (int) Math.floor(oldMatrix[0].length / (double) N);

        double[][] rebinedMatrix = new double[newYLength][newXLength];

        for (int i = 0; i < (oldMatrix.length - N+1); i+=N){
            int yIndex = (int) Math.floor(i / (double) N);
            for (int j = 0; j < (oldMatrix[0].length - N+1); j+=N){
                int xIndex = (int) Math.floor(j / (double) N);

                rebinedMatrix[yIndex][xIndex] = 0.0;
                for (int ii = i; ii < i+N; ii++){
                    for (int jj = j; jj < j+N; jj++){
                        rebinedMatrix[yIndex][xIndex] += oldMatrix[ii][jj];
                    }
                }
                rebinedMatrix[yIndex][xIndex] /= (double) (N*N);
            }
        }

        return rebinedMatrix;
    }
}
