package chess.domain.result;

import chess.domain.board.Coordinate;
import chess.domain.piece.Piece;

import java.util.Map;

public class Scores {
    private static final Scores EMPTY_SCORES = new Scores(new Score(0), new Score(0));

    private final Score blackTeamScore;
    private final Score whiteTeamScore;

    private Scores(Score blackTeamScore, Score whiteTeamScore) {
        this.blackTeamScore = blackTeamScore;
        this.whiteTeamScore = whiteTeamScore;
    }

    public static Scores generateResult(Map<Coordinate, Piece> blackTeamPieces, Map<Coordinate, Piece> whiteTeamPieces) {
        return new Scores(Score.from(blackTeamPieces), Score.from(whiteTeamPieces));
    }

    public static Scores getEmptyScores() {
        return EMPTY_SCORES;
    }

    public double getBlackTeamScore() {
        return blackTeamScore.getScore();
    }

    public double getWhiteTeamScore() {
        return whiteTeamScore.getScore();
    }
}
