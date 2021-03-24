package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class KnightMoveConditionTest {

    @DisplayName("나이트가 조건대로 움직이는지 확인한다.")
    @Test
    void isSatisfyBy() {
        KnightMoveCondition condition = new KnightMoveCondition();
        Board board = new Board(Collections.singletonList(
                Piece.createPawn(Color.BLACK, 4, 4)
        ));

        int[] row = {2, 2, -2, -2, 1, -1, 1, -1};
        int[] col = {1, -1, 1, -1, 2, 2, -2, -2};

        for (int i = 0; i < row.length; i++) {
            int dr = 4 + row[i];
            int dc = 4 + col[i];

            assertThat(condition.isSatisfyBy(board, Piece.createPawn(Color.BLACK, 4, 4), new Position(dr, dc))).isTrue();
        }

        assertThat(condition.isSatisfyBy(board, Piece.createPawn(Color.BLACK, 4, 4), new Position(2, 4))).isFalse();
    }

    @DisplayName("나이트의 도착지에 아군 말이 있으면 안된다.")
    @Test
    void isSatisfyBy_false() {
        KnightMoveCondition condition = new KnightMoveCondition();
        Board board = new Board(Arrays.asList(
                Piece.createKnight(Color.BLACK, 4, 4),
                Piece.createPawn(Color.BLACK, 7, 5)
        ));

        assertThat(condition.isSatisfyBy(board, Piece.createPawn(Color.BLACK, 4, 4), new Position(7, 5))).isFalse();
    }
}
