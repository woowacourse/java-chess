package chess.console.domain.piece;

import static chess.console.domain.board.Direction.NOTHING;

import chess.console.domain.board.Direction;
import chess.console.domain.board.Position;
import java.util.List;

public abstract class AbstractStraightPiece extends Piece {

    AbstractStraightPiece(Color color, List<Direction> directions) {
        super(color, directions);
    }

    @Override
    public boolean canMove(Position source, Position destination) {
        return findDirection(source, destination) != NOTHING;
    }

    @Override
    public Direction findDirection(Position source, Position destination) {
        return directions.stream()
                .filter(direction -> source.canCrossMovingStraight(direction, destination))
                .findFirst()
                .orElse(NOTHING);
    }
}
