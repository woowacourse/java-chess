package chess.domain.piece;

import chess.domain.Team;

public enum PieceType {
    PAWN(1.0, "p"),
    KNIGHT(2.5, "n"),
    BISHOP(3.0, "B"),
    ROOK(5.0, "r"),
    QUEEN(9.0, "q"),
    KING(0.0, "k");

    private final double score;
    private final String acronym;

    PieceType(double score, String acronym) {
        this.score = score;
        this.acronym = acronym;
    }

    public double getScore() {
        return score;
    }

    public String getAcronym(Team team) {
        if (team == Team.BLACK) {
            return getAcronymToUpperCase();
        }

        return getAcronymToLowerCase();
    }

    private String getAcronymToUpperCase() {
        return acronym.toUpperCase();
    }


    private String getAcronymToLowerCase() {
        return acronym.toLowerCase();
    }
}