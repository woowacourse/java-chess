package chess.piece.type.movable;

import chess.board.Route;

public class QueenPieceMovable implements PieceMovable {

    @Override
    public boolean canMove(Route route) {
        return isQueenRange(route) && hasNotObstacle(route);
    }

    private boolean isQueenRange(Route route) {
        return route.isDiagonal() || route.isStraight();
    }

}
