package chess.dto;

import chess.domain.Point;
import chess.domain.piece.Color;
import chess.domain.piece.kind.Piece;

import java.util.Map;

public class ChessGameDto {
    private Map<Point, Piece> board;
    private Color color;

    public Map<Point, Piece> getBoard() {
        return this.board;
    }

    public void setBoard(Map<Point, Piece> board) {
        this.board = board;
    }

    public Color getCurrentColor() {
        return this.color;
    }

    public void setCurrentColor(Color color) {
        this.color = color;
    }
}
