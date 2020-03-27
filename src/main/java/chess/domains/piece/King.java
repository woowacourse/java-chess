package chess.domains.piece;

import chess.domains.position.Position;

import java.util.List;

public class King extends Piece {
    public King(PieceColor pieceColor) {
        super(pieceColor, "k", 0);
    }

    @Override
    public boolean isValidMove(Position current, Position target) {
        return Math.abs(current.xGapBetween(target)) <= 1
                && Math.abs(current.yGapBetween(target)) <= 1;
    }

    @Override
    public List<Position> findRoute(Position source, Position target) {
        return null;
    }
}
