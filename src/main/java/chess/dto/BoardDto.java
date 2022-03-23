package chess.dto;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public class BoardDto {
    private final Map<Position, Piece> board;

    public BoardDto(Map<Position, Piece> board) {
        this.board = board;
    }

    public static BoardDto from(Board board) {
        return new BoardDto(board.getBoard());
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
