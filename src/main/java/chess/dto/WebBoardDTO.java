package chess.dto;

import chess.domain.board.Board;
import java.util.Map;

public class WebBoardDTO extends WebSimpleBoardDTO {
    private Map<String, String> pieces;

    public WebBoardDTO(Board board, int boardId) {
        super(board, boardId);
    }

    public Map<String, String> getPieces() {
        return pieces;
    }

    public void setPieces(Map<String, String> pieces) {
        this.pieces = pieces;
    }
}
