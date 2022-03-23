package chess.piece;

import chess.Position;
import chess.Team;

public class Bishop extends Piece {

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
}
