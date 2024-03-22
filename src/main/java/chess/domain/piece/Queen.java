package chess.domain.piece;

import chess.domain.board.Route;

public class Queen extends Piece {
    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Route route) {
        if (route.hasPiecePathExcludedTarget()) {
            return false;
        }
        return route.hasNoAllyAtTarget() && route.categoryNumOf(1);
    }
}

