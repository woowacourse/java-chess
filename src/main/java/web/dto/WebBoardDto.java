package web.dto;

import chess.domain.board.Point;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.Map;

public class WebBoardDto {

    private final String turnColor;
    private final Map<String, String> board;
    private boolean isRunnable;

    public WebBoardDto(Map<Point, Piece> pointPieces, Color currentTurn, boolean isRunnable) {
        this.turnColor = currentTurn.toString();
        this.board = new HashMap<>();
        for (Point point : pointPieces.keySet()) {
            this.board.put(point.convertPointToId(),
                    pointPieces.get(point).getPieceColor() + "-" + pointPieces.get(point).getPieceType());
        }
        this.isRunnable = isRunnable;
    }

    public String getTurnColor() {
        return turnColor;
    }

    public Map<String, String> getBoard() {
        return board;
    }

}
