package chess.dto;

import chess.domain.board.Board;
import chess.domain.piece.Color;

public class BoardDTO {

    private final double whiteScore;
    private final double blackScore;
    private final Color winner;

    public BoardDTO(Board board) {
        whiteScore = board.score(Color.WHITE);
        blackScore = board.score(Color.BLACK);
        winner = board.winner();
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }

    public Color getWinner() {
        return winner;
    }
}
