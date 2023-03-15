package chess.domain.piece;

import chess.domain.Role;
import chess.domain.Team;

public class Bishop extends Piece {

    public Bishop(Team team) {
        super(Role.BISHOP, team);
    }
}
