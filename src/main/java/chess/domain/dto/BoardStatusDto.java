package chess.domain.dto;

import chess.domain.Turn;
import chess.domain.piece.Piece;

import java.util.Map;

public class BoardStatusDto {

    private final Map<String, Piece> pieces;
    private final Map<String, Double> score;
    private final Turn turn;

    public BoardStatusDto(Map<String, Piece> pieces, Map<String, Double> score, Turn turn) {
        this.pieces = pieces;
        this.score = score;
        this.turn = turn;
    }

    public Map<String, Piece> getPieces() {
        return pieces;
    }

    public Map<String, Double> getScore() {
        return score;
    }

    public Turn getTurn() {
        return turn;
    }
}
