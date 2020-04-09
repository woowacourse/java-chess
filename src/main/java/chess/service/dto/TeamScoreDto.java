package chess.service.dto;

import chess.manager.ChessManager;
import chess.piece.Team;

public class TeamScoreDto {
    private final double blackScore;
    private final double whiteScore;

    public TeamScoreDto(ChessManager chessManager) {
        this.whiteScore = chessManager.calculateScoreByTeam(Team.WHITE);
        this.blackScore = chessManager.calculateScoreByTeam(Team.BLACK);
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }
}
