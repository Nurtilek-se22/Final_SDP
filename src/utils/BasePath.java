package utils;

public class BasePath implements AnyPath {
    public String basePath;
    public BasePath(String base) { basePath = base; }
    public BasePath() { basePath = "/"; }
    @Override public String getBase() { return basePath; } 
    @Override public String getPath(String path) { return basePath + path; } 
}
