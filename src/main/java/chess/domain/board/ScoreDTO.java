package chess.domain.board;

import chess.domain.chess.Color;

public class ScoreDTO {
    private final double blackScore;
    private final double whiteScore;

    public ScoreDTO(Board board) {
        this(board.score(Color.BLACK), board.score(Color.WHITE));
    }

    public ScoreDTO(double blackScore, double whiteScore) {
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
    }
}
