package chess.domain.pieces;

import chess.domain.Position;
import chess.domain.Team;

public class Rook extends Piece {
    private static final int SCORE = 0;

    public Rook(Position position, Team team) {
        super(position, team);
    }
}
