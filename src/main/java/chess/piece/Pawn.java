package chess.piece;

import chess.validator.PawnMoveValidator;

public class Pawn extends Piece {
    private static final double LOWER_SCORE = 0.5;

    public Pawn(Team team) {
        super(team, "P", new PawnMoveValidator(), 1);
    }

    public static double getLowerScore() {
        return LOWER_SCORE;
    }
}