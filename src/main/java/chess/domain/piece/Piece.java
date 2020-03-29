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

    public double getScore() {
        if (this.pieceType == PieceType.PAWN) {
            throw new UnsupportedOperationException("폰에게는 매개변수있는 getScore 를 사용하세요.");
        }
        return this.pieceType.score();
    }

    public double getScore(boolean mustChange) {
        if (this.pieceType != PieceType.PAWN) {
            throw new UnsupportedOperationException("폰만 사용할 수 있는 메서드입니다.");
        }
        return this.pieceType.score(mustChange);
    }
}
