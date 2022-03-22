package chess.domain.piece;

import chess.domain.Position;

public final class Knight extends Piece {

    private static final PieceType KNIGHT_TYPE = PieceType.KNIGHT;

    public Knight(Color color, Position position) {
        super(color, KNIGHT_TYPE, position);
    }
}
