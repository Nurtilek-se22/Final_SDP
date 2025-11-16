package math;

import java.awt.Dimension;
import java.awt.Point;

public class FDimension implements HasRect<FDimension> {
    public double width, height;
    public FDimension(double w, double h) { width = w; height = h;                   }
    public FDimension(double s)           { this(s, s);                              }
    public FDimension()                   { this(0, 0);                              }
    public FDimension(Dimension s)        { this(s.width, s.height);                 }
    public FDimension(HasSize<?> size)    { this(size.getWidth(), size.getHeight()); }
    public FDimension(Point p)            { this(p.x, p.y);                          }
    public FDimension(HasLocation<?> loc) { this(loc.getX(), loc.getY());            }

    @Override public double getWidth()  { return width;  }
    @Override public double getHeight() { return height; }
    @Override public double getX()      { return width;  }
    @Override public double getY()      { return height; }

    @Override public FDimension setWidth(double w)  { width  = w; return this; }
    @Override public FDimension setHeight(double h) { height = h; return this; }
    @Override public FDimension setX(double x)      { width  = x; return this; }
    @Override public FDimension setY(double y)      { height = y; return this; }

    @Override public String toString() {
        return String.format("FDimension{width: %.4f, height: %.4f}", (float)width, (float)height);
    }
}
