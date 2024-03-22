package chess.domain.piece;

import chess.domain.position.Position;

public class Knight extends Piece {

    private static final int SIDE_STEP = 1;
    private static final int STRAIGHT_STEP = 2;

    public Knight(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    public void move(Position target) {

    }

    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }
}
