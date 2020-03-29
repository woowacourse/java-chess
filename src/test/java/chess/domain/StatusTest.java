package chess.domain;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static chess.domain.position.Fixtures.A2;
import static chess.domain.position.Fixtures.A3;
import static org.assertj.core.api.Assertions.assertThat;

class StatusTest {

    private Board board;

    @Test
    void result() {
        Map<Position, Piece> setter = new LinkedHashMap<>();
        setter.put(A2, new Rook(A2, Team.BLACK));

        board = Board.of(setter);

        Map<Team, Double> expected = new HashMap<>();
        expected.put(Team.BLACK, 5.0);
        expected.put(Team.WHITE, 0.0);

        assertThat(Status.result(board)).isEqualTo(expected);
    }

    @Test
    void winner_Return_White() {
        Map<Position, Piece> setter = new LinkedHashMap<>();
        setter.put(A2, new Rook(A2, Team.WHITE));

        board = Board.of(setter);

        assertThat(Status.winner(board)).isEqualTo("백");
    }

    @Test
    void winner_Return_Black() {
        Map<Position, Piece> setter = new LinkedHashMap<>();
        setter.put(A2, new Rook(A2, Team.BLACK));

        board = Board.of(setter);

        assertThat(Status.winner(board)).isEqualTo("흑");
    }

    @Test
    void winner_Return_Draw() {
        Map<Position, Piece> setter = new LinkedHashMap<>();
        setter.put(A2, new Rook(A2, Team.BLACK));
        setter.put(A3, new Rook(A3, Team.WHITE));

        board = Board.of(setter);

        assertThat(Status.winner(board)).isEqualTo("없음 (무승부)");
    }
}