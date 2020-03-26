package chess.domain.piece;

import chess.domain.board.Path;
import chess.domain.board.Position;

public enum Type {
    KING("k", MovingStrategy::king, InitialPosition::king),
    QUEEN("q", MovingStrategy::queen, InitialPosition::queen),
    ROOK("r", MovingStrategy::rook, InitialPosition::rook),
    KNIGHT("n", MovingStrategy::knight, InitialPosition::knight),
    BISHOP("b", MovingStrategy::bishop, InitialPosition::bishop),
    PAWN("p", MovingStrategy::pawn, InitialPosition::pawn);

    private final String name;
    private final MovingStrategy movingStrategy;
    private final InitialPosition initialPosition;

    Type(final String name, final MovingStrategy movingStrategy, final InitialPosition initialPosition) {
        this.name = name;
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
}
