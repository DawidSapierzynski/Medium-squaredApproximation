import Jama.Matrix;

public class Zad1 {
    public static void main(String[] args) {
        double[][] points = {{-1, 1}, {0, 2}, {1, 3}, {2, 2}, {2.5, 3}, {3.5, 4}, {4.5, 2.5}, {5.5, 1}};

        /*
        Postać wielomianu aproksymującego
        np. {0,1} = a1x + a0
        np. {3, 5, 7} = a3x^3 + a5x^5 + a7x^7
        */
        int[] postacWielomianu = {0, 1, 4, 5};

        //Tablica z numerami punktów (points) które mają być aproksymowane
        int[] numeryPunktow = {0, 1, 2, 3, 4, 5, 6, 7};

        Matrix wspolczynniki = policzWspolczynniki(points, numeryPunktow, postacWielomianu);

        System.out.print("A:");
        wspolczynniki.print(6, 6);

        double[][] wykres = new double[100][2];

        double k = (points[points.length - 1][0] - points[0][0]) / 100;
        double w = 0;
        int m = 0;

        for (double x = points[0][0]; x < points[points.length - 1][0]; x += k) {
            for (int j = 0; j < postacWielomianu.length; j++) {
                w += wspolczynniki.get(j, 0) * Math.pow(x, postacWielomianu[j]);
            }

            wykres[m][0] = x;
            wykres[m][1] = w;
            w = 0;
            m++;
        }


        GrafikaG.mygraph(wykres, points);
    }

    private static Matrix policzWspolczynniki(double[][] points, int[] numeryPunktow, int[] postacWielomianu) {

        Matrix x = generujX(points, numeryPunktow, postacWielomianu);
        Matrix y = generujY(points, numeryPunktow);

        System.out.print("X:");
        x.print(6, 6);

        System.out.print("Y:");
        y.print(6, 6);

        Matrix a = (((x.transpose().times(x)).inverse()).times(x.transpose())).times(y);


        return a;
    }

    private static Matrix generujY(double[][] points, int[] numeryPunktow) {
        Matrix y = new Matrix(numeryPunktow.length, 1);

        for (int i = 0; i < numeryPunktow.length; i++) {
            y.set(i, 0, points[numeryPunktow[i]][1]);
        }

        return y;
    }

    private static Matrix generujX(double[][] points, int[] numeryPunktow, int[] postacWielomianu) {
        Matrix x = new Matrix(numeryPunktow.length, postacWielomianu.length);

        for (int i = 0; i < numeryPunktow.length; i++) {
            for (int j = 0; j < postacWielomianu.length; j++) {
                x.set(i, j, Math.pow(points[numeryPunktow[i]][0], postacWielomianu[j]));
            }

        }
        return x;
    }
}
