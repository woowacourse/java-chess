package chess.domain.piece;

import chess.domain.Team;

public class Piece {

    private Team team;
    private PieceType pieceType;

    public Piece(Team team, PieceType pieceType) {
        this.team = team;
        this.pieceType = pieceType;
    }

    public boolean canMove(MoveInformation moveInformation) {
        return pieceType.canMove(moveInformation);
    }

    public boolean isSameTeam(Piece piece) {
        return this.belongs(piece.team);
    }

    public boolean is(PieceType pieceType) {
        return this.pieceType == pieceType;
    }

    public boolean belongs(Team team) {
        return this.team == team;
    }

    public String getAcronym() {
        if (team == Team.WHITE) {
            return pieceType.getAcronymToLowerCase();
        }
        return pieceType.getAcronymToUpperCase();
    }
}
