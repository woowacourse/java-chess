package chess.domain.Piece.state;

import chess.domain.Piece.team.Team;
import chess.domain.position.Position;

public abstract class Moved extends Initialized {
    protected Moved(Position position, Team team) {
        super(position, team);
    }
}
