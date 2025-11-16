package utils;

public class Timer {
    private long dt;
    private long timestamp;

    public Timer(double dt_secs) { setDuration(dt_secs); }

    public void reset() { timestamp = System.currentTimeMillis(); }
    public long getStartTime() { return timestamp; }

    public boolean isFinished() { return System.currentTimeMillis() - timestamp >= dt; }

    public double getDuration() { return dt * 0.001; }

    public void setDuration(double dt_secs) {
        dt = (long)(dt_secs * 1000);
        timestamp = 0;
    }
}
