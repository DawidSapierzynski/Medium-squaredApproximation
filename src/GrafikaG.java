import ptolemy.plot.*;

import java.util.Random;


public class GrafikaG {
    public static void mygraph(double[][] points, double[][] points2) {
        Plot myPlot = new Plot();
        myPlot.setTitle("Plot example");
        myPlot.setXLabel("x");
        myPlot.setYLabel("s(x)");
        int dl = points.length;
        int d2 = points2.length;
        myPlot.setMarksStyle("none", 0);
        myPlot.setMarksStyle("dots", 1);
        for (int i = 0; i < dl - 1; i++) {
            myPlot.addPoint(0, points[i][0], points[i][1], true);
        }
        for (int i = 0; i < d2 - 1; i++) {
            myPlot.addPoint(1, points2[i][0], points2[i][1], false);
        }
        PlotApplication app = new PlotApplication(myPlot);
        app.setSize(1000, 800);
        app.setLocation(100, 10);
        app.setTitle("Plot");
    }

}
