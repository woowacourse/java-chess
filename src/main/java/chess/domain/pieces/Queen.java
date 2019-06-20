package chess.domain.pieces;

import chess.domain.Position;
import chess.domain.Team;

public class Queen extends Piece {
    private static final int SCORE = 0;

    public Queen(Position position, Team team) {
        super(position, team);
    }
}
