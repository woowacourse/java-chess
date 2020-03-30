package chess.piece.type.movable;

import chess.location.Location;
import chess.piece.type.Piece;
import chess.piece.type.movable.PieceMovable;

import java.util.Map;

public class QueenPieceMovable implements PieceMovable {
    @Override
    public boolean canMove(Map<Location, Piece> board, Location now, Location after) {
        return now.isQueenRange(after) && hasNotObstacle(board, now, after);
    }
}
