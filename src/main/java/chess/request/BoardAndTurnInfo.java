package chess.request;

import chess.domain.board.Point;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

import java.util.Map;

public class BoardAndTurnInfo implements Response {

    private final Map<Point, Piece> board;
    private final Color turnColor;

    public BoardAndTurnInfo(Map<Point, Piece> board, Color turnColor) {
        this.board = board;
        this.turnColor = turnColor;
    }

    public Map<Point, Piece> getBoard() {
        return board;
    }

    public Color getTurnColor() {
        return turnColor;
    }
}
