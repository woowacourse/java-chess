package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;

import java.util.List;

public final class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position src, Position dest) {
        return findDirection(src, dest) != null;
    }

    @Override
    public Direction findDirection(Position src, Position dest) {
        List<Direction> directions = Direction.getRookDirections();
        return directions.stream()
                .filter(direction -> src.canCrossMovingStraight(direction, dest))
                .findFirst()
                .orElse(null);
    }
}
