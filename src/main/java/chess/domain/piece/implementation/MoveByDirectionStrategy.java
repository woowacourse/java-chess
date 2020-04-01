package chess.domain.piece.implementation;

import chess.domain.board.BoardSituation;
import chess.domain.direction.MovingDirection;
import chess.domain.piece.MoveStrategy;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class MoveByDirectionStrategy implements MoveStrategy {

    private List<MovingDirection> directions;
    private Team team;

    public MoveByDirectionStrategy(List<MovingDirection> directions, Team team) {
        this.directions = directions;
        this.team = team;
    }

    @Override
    public List<Position> getMovablePositionsWithoutObstacles(Position source) {
        List<Position> positions = new ArrayList<>();
        for (MovingDirection direction : directions) {
            positions.addAll(getMovablePositionsByDirection(direction, source));
        }
        return positions;
    }

    private List<Position> getMovablePositionsByDirection(MovingDirection direction, Position source) {
        List<Position> positions = new ArrayList<>();
        Position startPosition = source;
        while (startPosition.canMoveBy(direction)) {
            startPosition = startPosition.moveByDirection(direction);
            positions.add(startPosition);
        }
        return positions;
    }

    @Override
    public List<Position> getMovablePositions(Position source, BoardSituation boardSituation) {
        List<Position> positions = new ArrayList<>();
        for (MovingDirection direction : directions) {
            positions.addAll(getMovablePositionsByDirection(direction, source, boardSituation));
        }
        return positions;
    }

    private List<Position> getMovablePositionsByDirection(MovingDirection direction, Position source, BoardSituation boardSituation) {
        List<Position> positions = new ArrayList<>();
        Position startPosition = source;
        while (startPosition.canMoveBy(direction)) {
            startPosition = startPosition.moveByDirection(direction);
            if (boardSituation.canMoveOrAttack(startPosition, team)) {
                positions.add(startPosition);
            }
            if (!boardSituation.canMove(startPosition)) {
                break;
            }
        }
        return positions;
    }
}
