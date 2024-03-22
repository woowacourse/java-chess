package chess.domain.piece;

import chess.domain.board.Route;

public class Queen extends Piece {

    public static final String QUEEN_NAME = "Q";

    public Queen(Color color) {
        super(color, QUEEN_NAME);
    }

    @Override
    public boolean canMove(Route route) {
        if (route.hasPiecePathExcludedTarget()) {
            return false;
        }
        return route.hasNoAllyAtTarget() && route.categoryNumOf(1);
    }
}

