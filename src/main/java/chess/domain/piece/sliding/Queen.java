package chess.domain.piece.sliding;

import chess.domain.color.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;
import chess.domain.piece.Position;
import java.util.Set;

public class Queen extends SlidingPiece {
    private static Set<Direction> directions = Direction.getEightDirection();

    public Queen(Position position, Color color) {
        super(position, color, directions);
    }

    @Override
    public PieceType pieceType() {
        if (color == Color.WHITE) {
            return PieceType.WHITE_QUEEN;
        }
        return PieceType.BLACK_QUEEN;
    }

    @Override
    public Queen update(Position destination) {
        return new Queen(destination, color);
    }
}
