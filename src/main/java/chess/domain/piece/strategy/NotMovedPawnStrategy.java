package chess.domain.piece.strategy;

import chess.domain.Team;
import chess.domain.position.Position;

import java.util.Map;

public class NotMovedPawnStrategy extends MovingStrategy {
    @Override
    protected void checkObstacle(Position source, Position target, Map<Position, Team> boardDto) {

    }

    @Override
    protected void checkDirection(Position source, Position target) {

    }
}
