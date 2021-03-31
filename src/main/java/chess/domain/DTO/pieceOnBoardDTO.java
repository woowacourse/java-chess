package chess.domain.DTO;

import chess.domain.state.TeamColor;

public class pieceOnBoardDTO {

    private final TeamColor teamColor;
    private final String pieceType;

    public pieceOnBoardDTO(TeamColor teamColor, String pieceType) {
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
