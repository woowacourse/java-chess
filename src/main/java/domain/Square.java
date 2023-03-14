package domain;

public class Square {
    //TODO: caching
    private final Position position;
    private final String name;

    public Square(Position position, String name) {
        this.position = position;
        this.name = name;
    }
}
