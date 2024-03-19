package chess.domain.piece;

import chess.domain.Team;

public class PieceMeta {
    private final Team team;
    private final PieceType pieceType;

    public PieceMeta(Team team, PieceType pieceType) {
        this.team = team;
        this.pieceType = pieceType;
    }

    public boolean isSameType(PieceType pieceType) {
        return this.pieceType == pieceType;
    }

    public boolean isBlack() {
        return team == Team.BLACK;
    }
}
