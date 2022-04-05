package chess.dto;

import chess.domain.board.Point;
import chess.domain.piece.Piece;
import chess.dto.BoardAndTurnInfo;

import java.util.HashMap;
import java.util.Map;

public class WebBoardDto {

    private final String turnColor;
    private final Map<String, String> board;
    private final boolean isRunnable;

    public WebBoardDto(BoardAndTurnInfo response, boolean isRunnable) {
        turnColor = response.getTurnColor().toString();
        board = new HashMap<>();
        Map<Point, Piece> responseBoard = response.getBoard();
        for (Point point : responseBoard.keySet()) {
            this.board.put(point.convertPointToId(),
                    responseBoard.get(point).convertPieceInfoToString());
        }
        this.isRunnable = isRunnable;
    }

    public String getTurnColor() {
        return turnColor;
    }

    public Map<String, String> getBoard() {
        return board;
    }

    public boolean isRunnable() {
        return isRunnable;
    }
}
