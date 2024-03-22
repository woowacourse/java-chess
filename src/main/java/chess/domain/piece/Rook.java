package chess.domain.piece;

import chess.domain.board.Route;

public class Rook extends Piece {
    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Route route) {
        if (route.hasPiecePathExcludedTarget()) {
            return false;
        }
        if (route.containsDiagonal()) {
            return false;
        }
        return route.hasNoAllyAtTarget() && route.categoryNumOf(1);
    }
}
