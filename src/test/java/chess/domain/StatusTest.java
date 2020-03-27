package chess.domain;

import chess.domain.board.Boards;
import chess.domain.piece.Piece;
import chess.domain.piece.rook.Rook;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static chess.domain.position.Fixtures.A2;
import static org.assertj.core.api.Assertions.assertThat;

class StatusTest {

    private Boards boards;

    @Test
    void result() {
        Map<String, Piece> lowerBoard = new LinkedHashMap<>();
        lowerBoard.put("a2", new Rook(A2));
        Map<String, Piece> upperBoard = new LinkedHashMap<>();

        boards = Boards.of(lowerBoard, upperBoard);

        Map<Turn, Double> expected = new HashMap<>();
        expected.put(Turn.LOWER, 5.0);
        expected.put(Turn.UPPER, 0.0);

        assertThat(Status.result(boards)).isEqualTo(expected);
    }

    @Test
    void winner_Return_White() {
        Map<String, Piece> lowerBoard = new LinkedHashMap<>();
        lowerBoard.put("a2", new Rook(A2));
        Map<String, Piece> upperBoard = new LinkedHashMap<>();

        boards = Boards.of(lowerBoard, upperBoard);

        assertThat(Status.winner(boards)).isEqualTo("백");
    }

    @Test
    void winner_Return_Black() {
        Map<String, Piece> lowerBoard = new LinkedHashMap<>();
        Map<String, Piece> upperBoard = new LinkedHashMap<>();
        upperBoard.put("a2", new Rook(A2));

        boards = Boards.of(lowerBoard, upperBoard);

        assertThat(Status.winner(boards)).isEqualTo("흑");
    }

    @Test
    void winner_Return_Draw() {
        Map<String, Piece> lowerBoard = new LinkedHashMap<>();
        Map<String, Piece> upperBoard = new LinkedHashMap<>();

        boards = Boards.of(lowerBoard, upperBoard);

        assertThat(Status.winner(boards)).isEqualTo("없음 (무승부)");
    }
}