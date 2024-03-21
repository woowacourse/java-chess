package chess.domain.piece.sliding;

import chess.domain.color.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;
import chess.domain.piece.Position;
import java.util.Set;

public class Rook extends SlidingPiece {
    private static final Set<Direction> directions = Direction.getFourDirection();

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
