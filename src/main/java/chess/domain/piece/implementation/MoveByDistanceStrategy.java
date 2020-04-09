package chess.domain.piece.implementation;

import chess.domain.board.BoardSituation;
import chess.domain.direction.MovingDirection;
import chess.domain.piece.MoveStrategy;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.List;
import java.util.stream.Collectors;

public abstract class MoveByDistanceStrategy implements MoveStrategy {

    private List<MovingDirection> directions;
    private Team team;

    public MoveByDistanceStrategy(List<MovingDirection> directions, Team team) {
        this.directions = directions;
        this.team = team;
    }

    @Override
    public List<Position> getMovablePositions(Position source, BoardSituation boardSituation) {
        return directions.stream()
                .filter(source::canMoveBy)
                .filter(direction -> canMove(direction, source, boardSituation))
                .map(source::moveByDirection)
                .collect(Collectors.toList());
    }

    private boolean canMove(MovingDirection direction, Position source, BoardSituation boardSituation) {
        Position forwardPosition = source.moveByDirection(direction);
        return boardSituation.canMoveOrAttack(forwardPosition, team);
    }
}
