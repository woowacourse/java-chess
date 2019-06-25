package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.Type;

public class Bishop extends Piece {
    public Bishop(Team team) {
        super(team, Type.BISHOP);
    }

    @Override
    public String getSymbol() {
        return team == Team.WHITE ? "♗" : "♝";
    }
}