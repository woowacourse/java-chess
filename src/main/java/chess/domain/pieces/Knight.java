package chess.domain.pieces;

import chess.domain.Position;
import chess.domain.Team;

public class Knight extends Piece {
    private static final int SCORE = 0;

    public Knight(Position position, Team team) {
        super(position, team);
    }
}
