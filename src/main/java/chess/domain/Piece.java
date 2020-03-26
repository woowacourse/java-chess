package chess.domain;

import chess.domain.position.Position;

public class Piece {
    private Team team;
    private PieceType pieceType;

    public Piece(Team team, PieceType pieceType) {
        this.team = team;
        this.pieceType = pieceType;
    }

    public String getAcronym() {
        if (team == Team.WHITE) {
            return pieceType.getAcronymToLowerCase();
        }
        return pieceType.getAcronymToUpperCase();
    }

    public Route findRoute(Position fromPosition, Position toPosition) {
        return pieceType.findRoute(fromPosition, toPosition, team);
    }

    public boolean isBlackTeam() {
        return this.team == Team.BLACK;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "team=" + team +
                ", pieceType=" + pieceType +
                '}';
    }

    public boolean isTeam(Team team) {
        return this.team == team;
    }

    public double getScore() {
        return pieceType.score();
    }
}
