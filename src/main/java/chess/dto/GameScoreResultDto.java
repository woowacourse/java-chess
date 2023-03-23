package chess.dto;

import chess.domain.game.ChessGame;

public class GameScoreResultDto {

    private final double upperTeamScore;
    private final double lowerTeamScore;

    private GameScoreResultDto(final double upperTeamScore, final double lowerTeamScore) {
        this.upperTeamScore = upperTeamScore;
        this.lowerTeamScore = lowerTeamScore;
    }

    public static GameScoreResultDto toDto(final ChessGame chessGame) {
        return new GameScoreResultDto(chessGame.calculateScoreOfUpperTeam(), chessGame.calculateScoreOfLowerTeam());
    }

    public double getUpperTeamScore() {
        return upperTeamScore;
    }

    public double getLowerTeamScore() {
        return lowerTeamScore;
    }
}
