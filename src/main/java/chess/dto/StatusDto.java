package chess.dto;

import chess.domain.GameResult;

public class StatusDto {

    private final double whitePlayerScore;
    private final double blackPlayerScore;
    private final String whitePlayerResult;
    private final String blackPlayerResult;

    private StatusDto(double whitePlayerScore, double blackPlayerScore, String whitePlayerResult,
            String blackPlayerResult) {
        this.whitePlayerScore = whitePlayerScore;
        this.blackPlayerScore = blackPlayerScore;
        this.whitePlayerResult = whitePlayerResult;
        this.blackPlayerResult = blackPlayerResult;
    }

    public static StatusDto of(final GameResult whitePlayer, final GameResult blackPlayer) {
        final double whitePlayerScore = whitePlayer.getPlayerScore();
        final double blackPlayerScore = blackPlayer.getPlayerScore();
        final String whitePlayerResult = whitePlayer.getPlayerResult();
        final String blackPlayerResult = blackPlayer.getPlayerResult();
        return new StatusDto(whitePlayerScore, blackPlayerScore, whitePlayerResult, blackPlayerResult);
    }
}
