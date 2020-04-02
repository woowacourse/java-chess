package chess.domain.piece;

import chess.domain.Team;

public abstract class Piece {

    private final String expression;
    protected final Team team;
    protected final double score;

    protected Piece(Team team, String expression, double score) {
        this.team = team;
        this.expression = expression;
        this.score = score;
    }

    public String getAcronym() {
        if (team == Team.WHITE) {
            return getAcronymToLowerCase();
        }
        return getAcronymToUpperCase();
    }

    public boolean belongs(Team current) {
        return this.team == current;
    }

    public boolean isKing() {
        return this instanceof King;
    }

    public boolean isPawn() {
        return this instanceof Pawn;
    }

    public boolean isKnight() {
        return this instanceof Knight;
    }

    public boolean isSameTeam(Piece piece) {
        return this.team == piece.team;
    }

    private String getAcronymToLowerCase() {
        return this.expression.toLowerCase();
    }

    private String getAcronymToUpperCase() {
        return this.expression.toUpperCase();
    }

    public abstract boolean canMove(MoveInformation moveInformation);

    public abstract double getScore();

    public abstract double getScore(boolean mustPawnScoreChangeToHalf);
}