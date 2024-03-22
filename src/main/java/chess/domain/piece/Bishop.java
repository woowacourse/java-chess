package chess.domain.piece;

import chess.domain.position.Position;

public class Bishop extends Piece {
    public Bishop(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    public void move(Position target) {

    }

    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }
}
