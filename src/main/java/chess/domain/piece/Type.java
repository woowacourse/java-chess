package chess.domain.piece;

import chess.domain.board.Position;

public enum Type {
    KING("k", (start, end) -> true, InitialPosition::king),
    QUEEN("q", (start, end) -> true, InitialPosition::queen),
    ROOK("r", (start, end) -> true, InitialPosition::rook),
    KNIGHT("n", (start, end) -> true, InitialPosition::knight),
    BISHOP("b", (start, end) -> true, InitialPosition::bishop),
    PAWN("p", (start, end) -> true, InitialPosition::pawn);

    private final String name;
    private final MovingStrategy movingStrategy;
    private final InitialPosition initialPosition;

    Type(final String name, final MovingStrategy movingStrategy, final InitialPosition initialPosition) {
        this.name = name;
        this.movingStrategy = movingStrategy;
        this.initialPosition = initialPosition;
    }

    public boolean initPosition(Position position, Side side) {
        return initialPosition.test(position, side);
    }

    public String getName() {
        return name;
    }
}
