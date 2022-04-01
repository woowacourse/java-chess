package chess.model.direction.strategy;

import chess.model.Team;
import chess.model.direction.Direction;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SingleMove implements MoveStrategy {

    private final Team team;
    private final List<Direction> directions;

    public SingleMove(Team team, List<Direction> directions) {
        this.team = team;
        this.directions = directions;
    }

    @Override
    public List<Position> searchMovablePositions(Position source, Map<Position, Piece> board) {
        return directions.stream()
                .filter(source::movableTo)
                .map(source::createPositionTo)
                .filter(position -> !board.get(position).isSameTeam(this.team))
                .collect(Collectors.toUnmodifiableList());
    }
}
