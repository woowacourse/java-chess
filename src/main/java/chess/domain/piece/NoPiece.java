package chess.domain.piece;

import chess.domain.board.Position;

public class NoPiece extends Piece {

    public NoPiece(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition) {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean exists() {
        return false;
    }
}
