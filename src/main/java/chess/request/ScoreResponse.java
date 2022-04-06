package chess.request;

import chess.domain.board.Point;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.request.Response;

import java.util.Map;

public class ScoreResponse implements Response {

    private final Map<Point, Piece> board;
    private final Map<Color, Double> scores;

    public ScoreResponse(Map<Point, Piece> board, Map<Color, Double> scores) {
        this.board = board;
        this.scores = scores;
    }

    @Override
    public Map<Point, Piece> getBoard() {
        return board;
    }

    public double getWhiteScore() {
        return scores.get(Color.WHITE);
    }

    public double getBlackScore() {
        return scores.get(Color.BLACK);
    }
}
