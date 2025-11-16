package math;

import java.awt.Dimension;
import java.awt.Point;

public class FRectangle implements HasRect<FRectangle> {
    public double x, y, width, height;
    public FRectangle(double x, double y, double w, double h) {
        this.x      = x;
        this.y      = y;
        this.width  = w;
        this.height = h;
    }
    public FRectangle(double x, double y, double s)         { this(x,        y,        s,               s);                }
    public FRectangle(double x, double y, Dimension s)      { this(x,        y,        s.width,         s.height);         }
    public FRectangle(double x, double y, HasSize<?> s)     { this(x,        y,        s.getWidth(),    s.getHeight());    }
    public FRectangle(double w, double h)                   { this(0,        0,        w,               h);                }
    public FRectangle(double s)                             { this(0,        0,        s,               s);                }
    public FRectangle(Dimension s)                          { this(0,        0,        s.width,         s.height);         }
    public FRectangle(HasSize<?> s)                         { this(0,        0,        s.getWidth(),    s.getHeight());    }
    public FRectangle(Point p, double w, double h)          { this(p.x,      p.y,      w,               h);                }
    public FRectangle(Point p, double s)                    { this(p.x,      p.y,      s,               s);                }
    public FRectangle(Point p, Dimension s)                 { this(p.x,      p.y,      s.width,         s.height);         }
    public FRectangle(Point p, HasSize<?> s)                { this(p.x,      p.y,      s.getWidth(),    s.getHeight());    }
    public FRectangle(Point p)                              { this(p.x,      p.y,      0,               0);                }
    public FRectangle(HasLocation<?> p, double w, double h) { this(p.getX(), p.getY(), w,               h);                }
    public FRectangle(HasLocation<?> p, double s)           { this(p.getX(), p.getY(), s,               s);                }
    public FRectangle(HasLocation<?> p, Dimension s)        { this(p.getX(), p.getY(), s.width,         s.height);         }
    public FRectangle(HasLocation<?> p, HasSize<?> s)       { this(p.getX(), p.getY(), s.getWidth(),    s.getHeight());    }
    public FRectangle(HasLocation<?> p)                     { this(p.getX(), p.getY(), 0,               0);                }
    public FRectangle()                                     { this(0,        0,        0,               0);                }

    @Override public double getX()      { return x;      }
    @Override public double getY()      { return y;      }
    @Override public double getWidth()  { return width;  }
    @Override public double getHeight() { return height; }

    @Override public FRectangle setX(double x)      { this.x = x; return this; }
    @Override public FRectangle setY(double y)      { this.y = y; return this; }
    @Override public FRectangle setWidth(double w)  { width  = w; return this; }
    @Override public FRectangle setHeight(double h) { height = h; return this; }

    @Override public String toString() {
        return String.format("FRectangle{x: %.4f, y: %.4f, width: %.4f, height: %.4f}", (float)x, (float)y, (float)width, (float)height);
    }
}
