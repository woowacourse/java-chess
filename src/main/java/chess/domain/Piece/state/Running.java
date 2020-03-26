package chess.domain.Piece.state;

import chess.domain.Piece.team.Team;
import chess.domain.position.Position;

public abstract class Running extends Initialized {
    protected Running(String name, Position position, Team team) {
        super(name, position, team);
    }
}
