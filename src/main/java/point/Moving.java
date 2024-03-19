package point;

public class Moving {

    private final Position currentPosition;
    private final Position nextPosition;

    public Moving(Position currentPosition, Position nextPosition) {
        this.currentPosition = currentPosition;
        this.nextPosition = nextPosition;
    }
}
