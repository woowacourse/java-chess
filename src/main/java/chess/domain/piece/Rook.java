package chess.domain.piece;

import chess.domain.color.Color;
import java.util.Set;

public class Rook extends SlidingPiece {
    private static Set<Direction> directions = Direction.getFourDirection();

    public Rook(Position position, Color color) {
        super(position, color, directions);
    }

    @Override
    public PieceType pieceType() {
        if (color == Color.WHITE) {
            return PieceType.WHITE_ROOK;
        }
        return PieceType.BLACK_ROOK;
    }

    @Override
    public Rook update(Position destination) {
        return new Rook(destination, color);
    }
}
