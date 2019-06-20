package chess.domain.pieces;

import chess.domain.Position;
import chess.domain.Team;

public class Bishop extends Piece {
    private static final int SCORE = 0;

    public Bishop(Position position, Team team) {
        super(position, team);
    }

    @Override
    boolean canMove(Position position) {
        return this.position.canMoveDiagonally(position);
    }
}
