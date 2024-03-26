package chess.domain.piece;

import chess.domain.board.Route;

public class Queen extends Piece {
    private static final String QUEEN_NAME = "Q";

    public Queen(Color color) {
        super(color, QUEEN_NAME);
    }

    @Override
    public boolean canMove(Route route) {
        if (route.hasPiecePathExclusive()) {
            return false;
        }
        return route.hasNoAllyAtTarget() && route.categoryNumOf(1);
    }
}

