package chess.piece;

import chess.Position;
import chess.Team;

import java.util.List;

public class Bishop extends Piece {
    private static final String BLACK_NAME = "B";
    private static final String WHITE_NAME = "b";

    public Bishop(Position position, Team team) {
        super(position, team);
    }

    @Override
    public boolean isMovable(Position position) {
        return isCorrectDirection(position);
    }

    private boolean isCorrectDirection(Position position) {
        return this.position.isDiagonal(position);
    }

    @Override
    public String getName() {
        if (Team.BLACK.equals(team)) {
            return BLACK_NAME;
        }
        return WHITE_NAME;
    }

    @Override
    public List<Position> getIntervalPosition(Piece targetPiece) {
        return null;
    }
}
