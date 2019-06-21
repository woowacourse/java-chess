package chess.domain.board;

import chess.domain.direction.core.Square;
import chess.domain.piece.core.Piece;
import chess.domain.piece.core.Team;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static chess.domain.board.InitBoard.*;
import static chess.domain.piece.core.Team.*;

public class Board {
    private Map<Square, Piece> board;

    Board(Map<Square, Piece> board) {
        this.board = board;
    }

    public static Board drawBoard() {
        Map<Square, Piece> board = new HashMap<>();
        board.putAll(GENERAL.genertate(0, BLACK));
        board.putAll(SOLDIER.genertate(1, BLACK));
        board.putAll(SOLDIER.genertate(6,WHITE));
        board.putAll(GENERAL.genertate(7,WHITE));

        return new Board(board);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board1 = (Board) o;
        return Objects.equals(board, board1.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board);
    }
}
