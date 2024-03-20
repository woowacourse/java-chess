package chess.piece;

import chess.board.Position;

public class Knight extends Piece {

    private static final int MAX_UNIT_MOVE = 1;

    public Knight(Color color) {
        super(PieceType.KNIGHT, color, MAX_UNIT_MOVE);
    }

    @Override
    public boolean isMovable(Position source, Position destination) {
        return source.isOnKnightRoute(destination);
    }
}
