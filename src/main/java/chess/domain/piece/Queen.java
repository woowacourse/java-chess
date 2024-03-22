package chess.domain.piece;

import chess.domain.position.Position;

public class Queen extends Piece {
    public Queen(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    public void move(Position target) {

    }

    @Override
    public PieceType getType() {
        return PieceType.QUEEN;
    }
}
