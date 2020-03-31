package chess.domains.piece;

import chess.domains.position.Position;

import java.util.Collections;
import java.util.List;

public class King extends Piece {
    public King(PieceColor pieceColor) {
        super(pieceColor, PieceType.KING);
    }

    @Override
    public boolean isValidMove(Position current, Position target) {
        return Math.abs(current.xGapBetween(target)) <= ONE_BLOCK
                && Math.abs(current.yGapBetween(target)) <= ONE_BLOCK;
    }

    @Override
    public List<Position> findRoute(Position source, Position target) {
        return Collections.emptyList();
    }
}
