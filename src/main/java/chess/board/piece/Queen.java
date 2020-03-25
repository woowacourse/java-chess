package chess.board.piece;

import chess.board.Vector;

public class Queen extends Piece {

    public Queen(final Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Vector vector) {
        return vector.isDiagonal() || vector.isStraight();
    }
}
