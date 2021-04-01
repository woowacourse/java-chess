package chess.domain.DTO;

import chess.domain.state.TeamColor;

public class PieceOnBoardDTO {

    private final TeamColor teamColor;
    private final String pieceType;

    public PieceOnBoardDTO(TeamColor teamColor, String pieceType) {
        this.teamColor = teamColor;
        this.pieceType = pieceType;
    }

    public TeamColor getTeamColor() {
        return teamColor;
    }

    public String getPieceType() {
        return pieceType;
    }
}
