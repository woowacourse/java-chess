package chess.domain.piece;

import chess.domain.board.Path;
import chess.domain.board.Position;

public enum Type {
    KING("k", MovingStrategy::king, InitialPosition::king, 0.0),
    QUEEN("q", MovingStrategy::queen, InitialPosition::queen, 9.0),
    ROOK("r", MovingStrategy::rook, InitialPosition::rook, 5.0),
    BISHOP("b", MovingStrategy::bishop, InitialPosition::bishop, 3.0),
    KNIGHT("n", MovingStrategy::knight, InitialPosition::knight, 2.5),
    PAWN("p", MovingStrategy::pawn, InitialPosition::pawn, 1.0);

    private final String name;
    private final MovingStrategy movingStrategy;
    private final InitialPosition initialPosition;
    private final double score;

    Type(final String name, final MovingStrategy movingStrategy,
        final InitialPosition initialPosition, final double score) {
        this.name = name;
        this.movingStrategy = movingStrategy;
        this.initialPosition = initialPosition;
        this.score = score;
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
