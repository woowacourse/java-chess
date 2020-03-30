package chess.piece.type.movable;

import chess.location.Location;
import chess.piece.type.Piece;

import java.util.Map;

public class KnightPieceMovable implements PieceMovable {
    @Override
    public boolean canMove(Map<Location, Piece> board, Location now, Location after) {
        return now.isKnightRange(after);
    }
}
