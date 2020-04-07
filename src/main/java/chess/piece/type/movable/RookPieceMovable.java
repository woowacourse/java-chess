package chess.piece.type.movable;

import chess.board.Route;
import chess.location.Location;
import chess.piece.type.Piece;

import java.util.Map;

public class RookPieceMovable implements PieceMovable {

    @Override
    public boolean canMove(Route route) {
        System.out.println("hasNotObstacle : " + hasNotObstacle(route));
        return route.isStraight() && hasNotObstacle(route);
    }

//    @Override
//    public boolean canMove(Map<Location, Piece> board, Location now, Location after) {
//        return now.isStraight(after) && hasNotObstacle(board, now, after);
//    }
}
