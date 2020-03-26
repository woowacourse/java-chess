package chess.domain.Piece.state;

import chess.domain.Piece.team.Team;
import chess.domain.Position;

public abstract class Dead extends Moved {
    protected Dead(Position position, Team team) {
        super(position, team);
    }
}
