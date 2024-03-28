package domain.game;

import domain.position.Position;
import domain.strategy.MoveStrategy;

import java.util.Set;

public class Piece {
    private final PieceType pieceType;
    private final MoveStrategy moveStrategy;

    public Piece(final PieceType pieceType, final MoveStrategy moveStrategy) {
        this.pieceType = pieceType;
        this.moveStrategy = moveStrategy;
    }

    public boolean hasColor(final TeamColor teamColor) {
        return teamColor.contains(pieceType);
    }

    public TeamColor color() {
        if (hasColor(TeamColor.WHITE)) {
            return TeamColor.WHITE;
        }
        return TeamColor.BLACK;
    }

    public boolean isMovable(final Position source, final Position destination, final Set<Position> piecePositions) {
        return moveStrategy.isMovable(source, destination, piecePositions);
    }

    public double value() {
        return pieceType.value();
    }

    public boolean isPawn() {
        return pieceType.isPawn();
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
