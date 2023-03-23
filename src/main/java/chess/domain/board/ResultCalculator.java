package chess.domain.board;

import java.util.Map;

import chess.domain.piece.Side;

public class ResultCalculator {

    private final ScoreBySide scoreBySide;
    private final GameResultBySide gameResultBySide;

    public ResultCalculator(final ScoreBySide scoreBySide, final GameResultBySide gameResultBySide) {
        this.scoreBySide = scoreBySide;
        this.gameResultBySide = gameResultBySide;
    }

    public void saveTotalScoreBySide(Side side, Score totalScore) {
        scoreBySide.updateScore(side, totalScore);
    }

    public void saveGameResultBySide() {
        Score whiteScore = scoreBySide.findScoreBySide(Side.WHITE);
        Score blackScore = scoreBySide.findScoreBySide(Side.BLACK);
        saveGameResult(whiteScore, blackScore);
    }

    private void saveGameResult(Score whiteScore, Score blackScore) {
        if (whiteScore.isGreaterScore(blackScore)) {
            whiteWin();
        }
        if (whiteScore.isLessScore(blackScore)) {
            whiteLose();
        }
        if (whiteScore.isEqualScore(blackScore)) {
            draw();
        }
    }

    private void whiteWin() {
        gameResultBySide.updateGameResultBySide(Side.WHITE, GameResult.WIN);
        gameResultBySide.updateGameResultBySide(Side.BLACK, GameResult.LOSE);
    }

    private void whiteLose() {
        gameResultBySide.updateGameResultBySide(Side.WHITE, GameResult.LOSE);
        gameResultBySide.updateGameResultBySide(Side.BLACK, GameResult.WIN);
    }

    private void draw() {
        gameResultBySide.updateGameResultBySide(Side.WHITE, GameResult.DRAW);
        gameResultBySide.updateGameResultBySide(Side.BLACK, GameResult.DRAW);
    }

    public Map<Side, Score> getScoreBySide() {
        return scoreBySide.getScoreBySide();
    }

    public Map<Side, GameResult> getGameResultBySide() {
        return gameResultBySide.getGameResultBySide();
    }
}
