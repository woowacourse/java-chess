package chess.piece;

import chess.board.Position;

public class King extends Piece {

    private static final int MAX_UNIT_MOVE = 1;

    public King(Color color) {
        super(PieceType.KING, color, MAX_UNIT_MOVE);
    }

    @Override
    public boolean isMovable(Position source, Position destination) {
        return source.isAdjacent(destination);
    }
}
