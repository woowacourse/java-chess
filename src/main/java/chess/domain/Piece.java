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

    public boolean canMove(Position fromPosition, Position toPosition) {
        // Todo : 다른 조건들도 추가해야함. 지금은 다른 말들의 영향을 안받음
        return pieceType.canMove(fromPosition, toPosition, team);
    }
}
