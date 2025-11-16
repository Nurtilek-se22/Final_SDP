package math;

import java.awt.Dimension;
import java.awt.Point;

public class FPoint implements HasRect<FPoint> {
    public double x, y;
    public FPoint(double x, double y) { this.x = x; this.y = y;                  }
    public FPoint(double t)           { this(t, t);                              }
    public FPoint()                   { this(0, 0);                              }
    public FPoint(Point p)            { this(p.x, p.y);                          }
    public FPoint(HasLocation<?> loc) { this(loc.getX(), loc.getY());            }
    public FPoint(Dimension s)        { this(s.width, s.height);                 }
    public FPoint(HasSize<?>    size) { this(size.getWidth(), size.getHeight()); }

    // TODO: move to Has* family
    public FPoint norm() {
        double len = x*x + y*y;
        if (len == 0) return this;
        return scaleL(1/len);
    }

    @Override public double getWidth()  { return x; }
    @Override public double getHeight() { return y; }
    @Override public double getX()      { return x; }
    @Override public double getY()      { return y; }

    @Override public FPoint setWidth(double w)  {      x = w; return this; }
    @Override public FPoint setHeight(double h) {      y = h; return this; }
    @Override public FPoint setX(double x)      { this.x = x; return this; }
    @Override public FPoint setY(double y)      { this.y = y; return this; }

    @Override public String toString() {
        return String.format("FPoint{x: %.4f, y: %.4f}", (float)x, (float)y);
    }
}
