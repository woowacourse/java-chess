package chess.dto;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public class BoardDto {

    private final Map<Position, Piece> board;

    public BoardDto(Map<Position, Piece> board) {
        this.board = Map.copyOf(board);
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
