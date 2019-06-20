package chess.domain.pieces;

import chess.domain.Position;
import chess.domain.Team;

public class Pawn extends Piece {
    private static final int SCORE = 0;

    public Pawn(Position position, Team team) {
        super(position, team);
    }
}
