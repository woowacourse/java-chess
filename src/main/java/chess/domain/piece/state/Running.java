package chess.domain.piece.state;

import chess.domain.piece.move.CanNotMoveStrategy;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;

import java.util.List;

public abstract class Running extends Initialized {
    protected Running(String name, Position position, Team team, List<CanNotMoveStrategy> canNotMoveStrategies) {
        super(name, position, team, canNotMoveStrategies);
    }
}
