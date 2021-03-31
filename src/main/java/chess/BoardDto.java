package chess;

import java.util.Map;
import java.util.Objects;

import chess.domain.board.Point;
import chess.domain.piece.kind.Piece;

public class BoardDto {
    private final Map<Point, Piece> board;

    public BoardDto(Map<Point, Piece> chessBoard) {
        this.board = chessBoard;
    }

    public Map<Point, Piece> getBoard() {
        return board;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BoardDto boardDto = (BoardDto)o;
        return Objects.equals(board, boardDto.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board);
    }
}
