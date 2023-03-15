package chess.domain.piece;

import chess.domain.Role;
import chess.domain.Team;

public class King extends Piece {

    public King(Team team) {
        super(Role.KING, team);
    }
}
