package chess.piece.type.movable;

import chess.board.Route;

public class RookPieceMovable implements PieceMovable {
    @Override
    public boolean canMove(Route route) {
        System.out.println("hasNotObstacle : " + hasNotObstacle(route));
        return route.isStraight() && hasNotObstacle(route);
    }
}
