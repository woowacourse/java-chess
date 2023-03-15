package chess.domain.piece;

import chess.domain.Role;
import chess.domain.Team;

public class Knight extends Piece {

    public Knight(Team team) {
        super(Role.KNIGHT, team);
    }
}
