package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.Type;

public class Queen extends Piece {
    public Queen(Team team) {
        super(team, Type.QUEEN);
    }

    @Override
    public String getSymbol() {
        return team == Team.WHITE ? "♕" : "♛";
    }
}