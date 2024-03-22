package chess.domain.piece;

import chess.domain.board.Route;

public class Rook extends Piece {

    public static final String ROOK_NAME = "R";

    public Rook(Color color) {
        super(color, ROOK_NAME);
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
