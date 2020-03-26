package chess.domain.piece.state;

import chess.domain.piece.team.Team;
import chess.domain.position.Position;

public abstract class Running extends Initialized {
    protected Running(String name, Position position, Team team) {
        super(name, position, team);
    }
}
