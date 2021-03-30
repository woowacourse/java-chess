package chess.dto;

import chess.domain.Point;
import chess.domain.piece.kind.Piece;

import java.util.Map;

public class BoardDto {
    private final Map<Point, Piece> board;

    public BoardDto(Map<Point, Piece> board) {
        this.board = board;
    }

    public Map<Point, Piece> getBoard() {
        return board;
    }
}
