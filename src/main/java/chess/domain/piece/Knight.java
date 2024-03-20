package chess.domain.piece;

import chess.domain.color.Color;
import java.util.Set;

public class Knight extends Piece {
    private static Set<Direction> directions = Direction.getKnightDirection();

    public Knight(Position position, Color color) {
        super(position, color);
    }

    @Override
    public Set<Position> findMovablePositions(Position destination) {
        Set<Position> movable = position.findMovablePositions(directions);

        if (movable.contains(destination)) {
            return Set.of(destination);
        }
        throw new IllegalArgumentException("이동할 수 없습니다.");
    }

    @Override
    public Knight update(Position destination) {
        return new Knight(destination, color);
    }

    @Override
    public PieceType pieceType() {
        if (color == Color.WHITE) {
            return PieceType.WHITE_KNIGHT;
        }
        return PieceType.BLACK_KNIGHT;
    }
}
