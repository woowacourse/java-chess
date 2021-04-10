package chess.controller.dto.score;

import chess.domain.manager.GameStatus;
import chess.domain.piece.Owner;

public class GameResultDto {

    private final double whiteScore;
    private final double blackScore;
    private final Owner winner;

    public GameResultDto(final double whiteScore, final double blackScore, final Owner winner) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
        this.winner = winner;
    }

    public static GameResultDto toStatus(final GameStatus gameStatus) {
        return new GameResultDto(gameStatus.whiteScore(), gameStatus.blackScore(), gameStatus.judgeWinner());
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }

    public Owner getWinner() {
        return winner;
    }
}
