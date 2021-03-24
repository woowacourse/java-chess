package chess.domain.result;

import chess.domain.board.Coordinate;
import chess.domain.piece.Piece;

import java.util.Map;

public class Result {
    private static final Result EMPTY_RESULT = new Result(new Score(0), new Score(0));

    private final Score blackTeamScore;
    private final Score whiteTeamScore;

    private Result(Score blackTeamScore, Score whiteTeamScore) {
        this.blackTeamScore = blackTeamScore;
        this.whiteTeamScore = whiteTeamScore;
    }

    public static Result generateResult(double blackTeamScore, double whiteTeamScore) {
        return new Result(new Score(blackTeamScore), new Score(whiteTeamScore));
    }

    public static Result generateResult(Map<Coordinate, Piece> blackTeamPieces, Map<Coordinate, Piece> whiteTeamPieces) {
        return new Result(Score.from(blackTeamPieces), Score.from(whiteTeamPieces));
    }

    public static Result getEmptyResult() {
        return EMPTY_RESULT;
    }

    public double getBlackTeamScore() {
        return blackTeamScore.getScore();
    }

    public double getWhiteTeamScore() {
        return whiteTeamScore.getScore();
    }
}
