package chess.domain.piece;

import chess.domain.Role;
import chess.domain.Team;

public class Queen extends Piece {

    public Queen(Team team) {
        super(Role.QUEEN, team);
    }
}
