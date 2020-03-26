package chess.domain.Piece.state;

import chess.domain.Piece.team.Team;
import chess.domain.position.Position;

public abstract class Running extends Initialized {
    protected Running(Position position, Team team) {
        super(position, team);
    }
}
