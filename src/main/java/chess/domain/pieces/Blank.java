package chess.domain.pieces;

import chess.domain.Position;
import chess.domain.Team;

public class Blank extends Piece {
    private static final int SCORE = 0;

    public Blank(Position position, Team team) {
        super(position, team);
    }

    @Override
    public boolean canMove(Position position) {
        return false;
    }
}
