package chess.domain.piece;

import chess.domain.Team;

public class Piece {

    private Team team;
    private PieceType pieceType;

    protected Piece() {
    }

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

    public boolean canMove() {
        return pieceType.canMove();
    }

    public boolean is(PieceType pieceType) {
        return this.pieceType == pieceType;
    }
}
