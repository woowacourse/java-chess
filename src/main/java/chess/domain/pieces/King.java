package chess.domain.pieces;

import chess.domain.Position;
import chess.domain.Team;

public class King extends Piece {
    private static final int SCORE = 0;

    public King(Position position, Team team) {
        super(position, team);
    }
}
