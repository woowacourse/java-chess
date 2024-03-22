package chess.domain.piece;

import chess.domain.position.Position;

public class King extends Piece {

    private static final int STEP_LIMIT = 1;

    public King(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    public void move(Position target) {

    }

    @Override
    public PieceType getType() {
        return PieceType.KING;
    }
}
