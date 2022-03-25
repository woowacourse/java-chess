package chess.domain.piece.strategy;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import java.util.ArrayList;
import java.util.List;

public abstract class MoveStrategy {

    abstract boolean isValidateCanMove(Color color, Position from, Position to);

    protected List<Position> getRoute(Position from, Position to) {
        Direction direction = Direction.of(from, to);
        List<Position> positions = new ArrayList<>();
        Position movedPosition = from.advancePosition(direction);

        while (!movedPosition.equals(to)) {
            positions.add(movedPosition);
            movedPosition = movedPosition.advancePosition(direction);
        }
        return positions;
    }
}
