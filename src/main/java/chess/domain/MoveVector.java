package chess.domain;

public enum MoveVector {
    East(new Direction(1, 0)),
    West(new Direction(-1, 0)),
    North(new Direction(0, 1)),
    South(new Direction(0, -1)),
    NorthEast(new Direction(1, 1)),
    NorthWest(new Direction(-1, 1)),
    SouthEast(new Direction(1, -1)),
    SouthWest(new Direction(-1, -1)),
    NightEastNorth(new Direction(2, 1)),
    NightEastSouth(new Direction(2, -1)),
    NightWestNorth(new Direction(-2, 1)),
    NightWestSouth(new Direction(-2, -1)),
    NightNorthEast(new Direction(1, 2)),
    NightNorthWest(new Direction(-1, 2)),
    NightSouthEast(new Direction(1, -2)),
    NightSouthWest(new Direction(-1, -2)),
    PawnFirstNorth(new Direction(0, 2)),
    PawnFirstSouth(new Direction(0, -2));

    Direction direction;

    private MoveVector(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }
}
