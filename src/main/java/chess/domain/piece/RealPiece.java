package chess.domain.piece;

import chess.domain.board.Paths;
import chess.domain.piece.strategy.PieceStrategy;
import chess.domain.position.Coordinate;
import chess.domain.position.Position;
import java.util.Map;

public final class RealPiece implements Piece {

    private final PieceColor color;
    private final PieceStrategy pieceStrategy;

    protected RealPiece(final PieceColor color, final PieceStrategy pieceStrategy) {
        this.color = color;
        this.pieceStrategy = pieceStrategy;
    }

    @Override
    public Paths possibleCoordinates(Coordinate currentCoordinate, Map<Coordinate, Position> boardPositions) {
        return new Paths(currentCoordinate, pieceStrategy.directions());
    }

    @Override
    public boolean isColor(PieceColor color) {
        return this.color.equals(color);
    }

    @Override
    public boolean isPawn() {
        return pieceStrategy.isPawn();
    }

    @Override
    public boolean isKing() {
        return pieceStrategy.isKing();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
