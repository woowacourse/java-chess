package chess.domain;

public enum Direction {
    EAST(1, 0),
    SOUTH(0, -1),
    WEST(-1, 0),
    NORTH(0, 1),
    NORTH_EAST(1, 1),
    SOUTH_EAST(1, -1),
    SOUTH_WEST(-1, -1),
    NORTH_WEST(-1, 1),
    NOT_FIND(0, 0);

    private Point position;

    Direction(int positionX, int positionY) {
        this.position = new Point(positionX, positionY);
    }

    public static Direction findDirection (Point point) {
        for(Direction direction : values()) {
            if(direction.position.equals(point)) {
                return direction;
            }
        }
        return NOT_FIND;
    }
}
