package chess.model.direction.strategy;

import static chess.model.Team.NONE;

import chess.model.Team;
import chess.model.direction.Direction;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MultipleMove implements MoveStrategy {

    private final Team team;
    private final List<Direction> directions;

    public MultipleMove(Team team, List<Direction> directions) {
        this.team = team;
        this.directions = directions;
    }

    @Override
    public List<Position> searchMovablePositions(Position source, Map<Position, Piece> board) {
        List<Position> positions = new ArrayList<>();
        for (Direction direction : directions) {
            positions.addAll(createMovablePositions(source, direction, board));
        }
        return positions;
    }

    private List<Position> createMovablePositions(Position source, Direction direction, Map<Position, Piece> board) {
        if (!source.movableTo(direction)) {
            return new ArrayList<>();
        }
        List<Position> positions = new ArrayList<>();
        Position nextPosition = source.createPositionTo(direction);
        while (board.get(nextPosition).isSameTeam(NONE) && nextPosition.movableTo(direction)) {
            positions.add(nextPosition);
            nextPosition = nextPosition.createPositionTo(direction);
        }
        if (!board.get(nextPosition).isSameTeam(this.team)) {
            positions.add(nextPosition);
        }
        return positions;
    }
}
