package chess.domain.piece;

enum Direction {

    LEFT(0, -1),
    RIGHT(0, 1),
    UP(1, 0),
    UP_UP(2, 0),
    DOWN(-1, 0),
    UP_RIGHT(1, 1),
    DOWN_RIGHT(-1, 1),
    UP_LEFT(1, -1),
    DOWN_LEFT(-1, -1),
    UP_UP_RIGHT(2, 1),
    UP_UP_LEFT(2, -1),
    LEFT_LEFT_UP(1, -2),
    LEFT_LEFT_DOWN(-1, -2),
    RIGHT_RIGHT_UP(1, 2),
    RIGHT_RIGHT_DOWN(-1, 2),
    DOWN_DOWN_LEFT(-2, -1),
    DOWN_DOWN_RIGHT(-2, 1);

    private final Weight weight;

    Direction(int rankWeight, int fileWeight) {
        this.weight = new Weight(rankWeight, fileWeight);
    }

    public Weight getWeight() {
        return weight;
    }
}
