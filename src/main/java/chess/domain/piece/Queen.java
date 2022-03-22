package chess.domain.piece;

import chess.domain.Position;

public final class Queen extends Piece {

    private static final PieceType QUEEN_TYPE = PieceType.QUEEN;

    public Queen(Color color, Position position) {
        super(color, QUEEN_TYPE, position);
    }
}
