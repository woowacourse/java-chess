package chess.domain.piece;

import chess.domain.Team;

public enum PieceType {
    PAWN(1.0, "p", "pawn"),
    KNIGHT(2.5, "n", "knight"),
    BISHOP(3.0, "B", "bishop"),
    ROOK(5.0, "r", "rook"),
    QUEEN(9.0, "q", "queen"),
    KING(0.0, "k", "king"),
    EMPTY(-1, ".", "empty");

    private final double score;
    private final String acronym;
    private final String name;

    PieceType(double score, String acronym, String name) {
        this.score = score;
        this.acronym = acronym;
        this.name = name;
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

    public String getName() {
        return name;
    }
}