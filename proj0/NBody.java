import java.util.ArrayList;
import java.util.List;

public class NBody {

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] planets = readPlanets(filename);
        double radius = readRadius(filename);

        /* draw background */
        StdDraw.enableDoubleBuffering();

        double Time = 0;
        while(Time<=T){
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for(int i=0; i< planets.length;i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for(int i=0; i< planets.length;i++){
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.setScale(-radius,radius);
            StdDraw.picture(0 ,0 ,"images/starfield.jpg");
            for(Planet planet : planets){
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            Time+= dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }




    public static double readRadius(String s){
        In in = new In(s);
        double number = in.readDouble();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String s){
        In in = new In(s);
        int number = in.readInt();
        Planet[] planets = new Planet[number];
        double radius = in.readDouble();
        for(int i = 0; i < number; i++){
            Planet planet = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
            planets[i] = planet;
        }
        return planets;
    }
}
