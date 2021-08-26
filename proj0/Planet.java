import java.util.OptionalLong;

public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    private double G = 6.67e-11;

    public Planet(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double distance = Math.pow(Math.pow(this.xxPos - p.xxPos, 2) + Math.pow(this.yyPos - p.yyPos, 2), 0.5);
        return distance;
    }

    public double calcForceExertedBy(Planet p) {
        double f = G * (this.mass) * (p.mass) / Math.pow(calcDistance(p), 2);
        return f;
    }

    public double calcForceExertedByX(Planet p) {
        double x_dis = this.xxPos > p.xxPos ? p.xxPos - this.xxPos : this.xxPos - p.xxPos;
        return x_dis * calcForceExertedBy(p) / calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        double y_dis = this.yyPos > p.yyPos ? p.yyPos - this.yyPos : this.yyPos - p.yyPos;
        return y_dis * calcForceExertedBy(p) / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double xf_sum = 0;
        for (Planet p : planets) {
            if (p.equals(this)) {
                continue;
            } else if (p.xxPos < this.xxPos) {
                xf_sum += (-1) * calcForceExertedByX(p);
            } else {
                xf_sum += calcForceExertedByX(p);
            }
        }
        return xf_sum;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double yf_sum = 0;
        for (Planet p : planets) {
            if (p.equals(this)) {
                continue;
            } else if (p.yyPos < this.yyPos) {
                yf_sum += (-1) * calcForceExertedByY(p);
            } else {
                yf_sum += calcForceExertedByY(p);
            }
        }
        return yf_sum;
    }

    public void update(double time, double Fx, double Fy) {
        this.xxVel = this.xxVel + time * Fx / this.mass;
        this.yyVel = this.yyVel + time * Fy / this.mass;
        this.xxPos = this.xxPos + this.xxVel * time;
        this.yyPos = this.yyPos + this.yyVel * time;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + imgFileName);
    }


}
