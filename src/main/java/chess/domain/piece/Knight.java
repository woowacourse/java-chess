package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public class Knight extends Piece {

    private static final List<Direction> DIRECTIONS = Direction.knight();
    private static final double SCORE = 2.5;

    public Knight(Color color) {
        super(SCORE, color);
    }

    @Override
    public boolean isMovablePosition(Position source, Position target, Map<Position, Piece> board) {
        return isMovableDot(DIRECTIONS, source, target);
    }
}
