package utils;

public class Path implements AnyPath {
    public AnyPath basePath;
    public String extraPath;
    public Path(AnyPath base, String extra) { basePath = base; extraPath = extra; }
    public Path(AnyPath base) { this(base, ""); }
    public Path(String base)  { this(new BasePath(base), ""); }
    public Path(Path path, String extra)  { AnyPath any = new Path(path.basePath, path.extraPath); this(any, extra); }
    public Path()  { this(new BasePath(), ""); }
    public Path add(String extra) { basePath = new Path(this); extraPath = extra; return this; }
    @Override public String getBase() { return basePath.getBase() + extraPath; }
    @Override public String getPath(String path) { return getBase() + path; }
}
