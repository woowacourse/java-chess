package chess.domain.pieces;

import chess.domain.position.Position;
import chess.domain.Team;

public class Blank extends Piece {
    private static final double SCORE = 0;

    public Blank(Position position, Team team) {
        super(position, team);
    }

    @Override
    public boolean canMove(Position position) {
        return false;
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public String toString() {
        return "BLANK";
    }
}
