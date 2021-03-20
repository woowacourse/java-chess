package chess.dto;

import chess.domain.board.Board;
import chess.domain.piece.Color;

public class BoardDTO {

    private double whiteScore;
    private double blackScore;
    private String winner;

    public BoardDTO(Board board) {
        whiteScore = board.score(Color.WHITE);
        blackScore = board.score(Color.BLACK);
        winner = board.winner().color();
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }

    public String getWinner() {
        return winner;
    }
}
