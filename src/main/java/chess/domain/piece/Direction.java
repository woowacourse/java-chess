package chess.domain.piece;

enum Direction {

    LEFT(new Weight(0, -1)),
    RIGHT(new Weight(0, 1)),
    UP(new Weight(1, 0)),
    DOWN(new Weight(-1, 0)),
    UP_RIGHT(new Weight(1, 1)),
    DOWN_RIGHT(new Weight(-1, 1)),
    UP_LEFT(new Weight(1, -1)),
    DOWN_LEFT(new Weight(-1, -1)),
    UP_UP_RIGHT(new Weight(2, 1)),
    UP_UP_LEFT(new Weight(2, -1)),
    LEFT_LEFT_UP(new Weight(1, -2)),
    LEFT_LEFT_DOWN(new Weight(-1, -2)),
    RIGHT_RIGHT_UP(new Weight(1, 2)),
    RIGHT_RIGHT_DOWN(new Weight(-1, 2)),
    DOWN_DOWN_LEFT(new Weight(-2, -1)),
    DOWN_DOWN_RIGHT(new Weight(-2, 1));

    private final Weight weight;

    Direction(Weight weight) {
        this.weight = weight;
    }

    public Weight getWeight() {
        return weight;
    }
}
