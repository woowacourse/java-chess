package chess.domain.position;

import chess.domain.piece.Piece;
import chess.domain.player.type.TeamColor;

public class PiecePosition {
    private final TeamColor teamColor;
    private final Long pieceId;
    private final Long positionId;

    public PiecePosition(Piece piece, Position position) {
        positionId = position.getId();
        if (piece != null) {
            pieceId = piece.getId();
            teamColor = piece.getTeamColor();
            return;
        }
        teamColor = null;
        pieceId = null;
    }

    public Long getPieceId() {
        return pieceId;
    }

    public Long getPositionId() {
        return positionId;
    }

    public TeamColor getTeamColor() {
        return teamColor;
    }

    public boolean isPieceExists() {
        return pieceId != null;
    }
}
