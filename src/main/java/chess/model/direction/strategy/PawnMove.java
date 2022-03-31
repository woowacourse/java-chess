package chess.model.direction.strategy;

import static chess.model.Team.NONE;

import chess.model.Team;
import chess.model.direction.Direction;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PawnMove implements MoveStrategy {

    private final Team team;

    public PawnMove(Team team) {
        this.team = team;
    }

    @Override
    public List<Position> searchMovablePositions(Position source, Map<Position, Piece> board) {
        List<Position> positions = new ArrayList<>();
        positions.addAll(searchCatchPositions(source, board));
        positions.addAll(searchBaseMovePositions(source, board));
        positions.addAll(searchSpecialMovePositions(source, board));
        return positions;
    }

    private List<Position> searchCatchPositions(Position source, Map<Position, Piece> board) {
        List<Direction> directions = Direction.pawnCatchDirection(team);
        return directions.stream()
                .filter(source::movableTo)
                .map(source::createPositionTo)
                .filter(position -> board.get(position).isOpponentTeam(team))
                .collect(Collectors.toUnmodifiableList());
    }

    private List<Position> searchBaseMovePositions(Position source, Map<Position, Piece> board) {
        Direction direction = Direction.pawnMoveDirection(team);
        Position nextPosition = source.createPositionTo(direction);
        if (board.get(nextPosition).isSameTeam(NONE)) {
            return List.of(nextPosition);
        }
        return new ArrayList<>();
    }

    private List<Position> searchSpecialMovePositions(Position source, Map<Position, Piece> board) {
        if (source.isNotStartLocation()) {
            return new ArrayList<>();
        }
        Direction direction = Direction.pawnMoveDirection(team);
        Position firstMovedPosition = source.createPositionTo(direction);
        Position secondMovedPosition = firstMovedPosition.createPositionTo(direction);
        if (board.get(firstMovedPosition).isSameTeam(NONE) && board.get(secondMovedPosition).isSameTeam(NONE)) {
            return List.of(secondMovedPosition);
        }
        return new ArrayList<>();
    }
}
