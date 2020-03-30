package chess.piece.type.movable;

import chess.location.Location;
import chess.piece.type.Piece;

import java.util.Map;

public class QueenPieceMovable implements PieceMovable {
    @Override
    public boolean canMove(Map<Location, Piece> board, Location now, Location after) {
        return isQueenRange(now, after) && hasNotObstacle(board, now, after);
    }

    private boolean isQueenRange(Location now, Location destination) {
        return now.isDiagonal(destination) || now.isStraight(destination);
    }

}
