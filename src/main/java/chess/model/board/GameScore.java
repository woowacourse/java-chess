package chess.model.board;

import chess.model.Team;

public class GameScore {

    private static final String WHITE = "화이트";
    private static final String BLACK = "블랙";
    private static final String DRAW = "무승부";

    private final double whiteScore;
    private final double blackScore;

    public GameScore(Board board) {
        this.whiteScore = board.calculateScore(Team.WHITE);
        this.blackScore = board.calculateScore(Team.BLACK);
    }

    public String pickWinner() {
        if (whiteScore > blackScore) {
            return WHITE;
        }
        if (blackScore > whiteScore) {
            return BLACK;
        }
        return DRAW;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }
}
