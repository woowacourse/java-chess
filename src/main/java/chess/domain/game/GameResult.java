package chess.domain.game;

import chess.domain.piece.Color;
import chess.domain.piece.Pieces;

public class GameResult {

    private final Color winner;
    private final double whiteScore;
    private final double blackScore;

    public GameResult(Color winner, double whiteScore, double blackScore) {
        this.winner = winner;
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public static GameResult calculate(Pieces chessmen) {
        ScoreCalculator scoreCalculator = new ScoreCalculator();

        Color winner = findWinner(chessmen);
        double whiteScore = scoreCalculator.calculate(chessmen.extractPiecesOf(Color.WHITE));
        double blackScore = scoreCalculator.calculate(chessmen.extractPiecesOf(Color.BLACK));

        return new GameResult(winner, whiteScore, blackScore);
    }

    private static Color findWinner(Pieces chessmen) {
        return chessmen.findKingWinner();
    }

    public Color getWinner() {
        return winner;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }
}
