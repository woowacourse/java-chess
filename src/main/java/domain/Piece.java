package domain;

import domain.board.Position;
import domain.strategy.MoveStrategy;

import java.util.Set;

public class Piece {
    private final PieceType pieceType;
    private final MoveStrategy moveStrategy;

    public Piece(final PieceType pieceType, final MoveStrategy moveStrategy) {
        this.pieceType = pieceType;
        this.moveStrategy = moveStrategy;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public boolean hasColor(final TeamColor teamColor) {
        return pieceType.hasColor(teamColor);
    }

    public boolean isMovable(final Position source, final Position destination, final Set<Position> piecePositions) {
        return moveStrategy.isMovable(source, destination, piecePositions);
    }
}
