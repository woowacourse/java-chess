package chess.piece.type.movable;

import chess.board.Route;
import chess.location.Location;
import chess.piece.type.Piece;

import java.util.Map;

public class BishopMovable implements PieceMovable {
    @Override
    public boolean canMove(Route route) {
        return route.isDiagonal() && hasNotObstacle(route);
    }

//    @Override
//    public boolean canMove(Map<Location, Piece> board, Location now, Location after) {
//        return now.isDiagonal(after) && hasNotObstacle(board, now, after);
//    }
}
