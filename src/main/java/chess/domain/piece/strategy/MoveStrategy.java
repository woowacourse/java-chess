package chess.domain.piece.strategy;

import chess.domain.board.position.Direction;
import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Team;
import java.util.ArrayList;
import java.util.List;

public abstract class MoveStrategy {

    public abstract boolean isValidateCanMove(Team team, Position from, Position to);

    public boolean isValidateCanMove(Team team, Piece targetPiece, Position from, Position to) {
        return true;
    }

    protected boolean isInvalidDirection(Position from, Position to, List<Direction> directions) {
        return directions.stream()
                .noneMatch(direction -> direction.isSameDirection(from, to));
    }

    protected boolean isInvalidDistance(Position from, Position to, List<Direction> directions) {
        return directions.stream()
                .noneMatch(direction -> direction.isSameDistance(from, to));
    }

    public List<Position> getRoute(Position from, Position to) {
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
