package chess.domain.piece;

import chess.domain.board.Path;
import chess.domain.board.Position;

public enum Type {
    KING("k", 0.0, MovingStrategy::king, InitialPosition::king),
    QUEEN("q", 9.0, MovingStrategy::queen, InitialPosition::queen),
    ROOK("r", 5.0, MovingStrategy::rook, InitialPosition::rook),
    KNIGHT("n", 3.0, MovingStrategy::knight, InitialPosition::knight),
    BISHOP("b", 2.5, MovingStrategy::bishop, InitialPosition::bishop),
    PAWN("p", 1.0, MovingStrategy::pawn, InitialPosition::pawn);

    private final String name;
    private final double score;
    private final MovingStrategy movingStrategy;
    private final InitialPosition initialPosition;

    Type(final String name, final double score, final MovingStrategy movingStrategy, final InitialPosition initialPosition) {
        this.name = name;
        this.score = score;
        this.movingStrategy = movingStrategy;
        this.initialPosition = initialPosition;
    }

    public boolean initPosition(Position position, Side side) {
        return initialPosition.isRightOn(position, side);
    }

    public boolean canMoveBetween(Path path) {
        if (path.distanceSquare() == 0) {
            return false;
        }
        return movingStrategy.test(path);
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }
}
