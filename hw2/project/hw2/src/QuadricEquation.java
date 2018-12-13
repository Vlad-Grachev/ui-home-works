public class QuadricEquation {
    private double a, b, c;

    public QuadricEquation() {
    }

    public QuadricEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void setA(double a) {
        this.a = a;
    }

    public void setB(double b) {
        this.b = b;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double[] getRoots(){
        double[] roots = null;
        if(a != 0) {
            double discr = b * b - 4.0 * a * c;
            if (discr > 0.0) {
                roots = new double[2];
                discr = Math.sqrt(discr);
                roots[0] = (-b + discr) / (2.0 * a);
                roots[1] = (-b - discr) / (2.0 * a);
            } else if (discr == 0.0) {
                roots = new double[1];
                roots[0] = -b / (2.0 * a);
            }
        } else {
            roots = new double[1];
            roots[0] = - c / b;
        }
        return roots;
    }

    public String getRootsAsJson() {
        double[] roots = getRoots();
        if (roots == null) {
            return "{\"num\": \"0\"}";
        } else if (roots.length == 1) {
            return "{\"num\": \"1\", \"x1\": \"" + String.format("%.2f",roots[0]) + "\"}";
        } else {

            return "{\"num\": \"2\", \"x1\": \"" + String.format("%.2f",roots[0])
                    + "\", \"x2\": \"" + String.format("%.2f",roots[1]) + "\"}";
        }
    }
}
