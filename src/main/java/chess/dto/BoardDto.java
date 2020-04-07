package chess.dto;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;

public class BoardDto {
    Map<Position, Piece> board;

    public BoardDto(final Board board) {
        this.board = board.getBoard();
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
