package entity;

public enum Direction {
    Up(0),
    Down(1),
    Left(2),
    Right(3);

    public int value;
    private Direction(int val) { value = val; }
}
