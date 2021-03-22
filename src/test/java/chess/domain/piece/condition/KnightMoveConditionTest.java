package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.Position;
import chess.domain.piece.black.BlackKnight;
import chess.domain.piece.white.WhiteKnight;
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
                BlackKnight.createWithCoordinate(4, 4)
        ));

        int[] row = {2, 2, -2, -2, 1, -1, 1, -1};
        int[] col = {1, -1, 1, -1, 2, 2, -2, -2};

        for (int i = 0; i < row.length; i++) {
            int nextRow = 4 + row[i];
            int nextColumn = 4 + col[i];

            assertThat(condition.isSatisfyBy(board, BlackKnight.createWithCoordinate(4, 4), new Position(nextRow, nextColumn))).isTrue();
        }

        assertThat(condition.isSatisfyBy(board, BlackKnight.createWithCoordinate(4, 4), new Position(2, 4))).isFalse();
    }

    @DisplayName("나이트의 도착지에 아군 말이 있으면 안된다.")
    @Test
    void isSatisfyBy_sameColorNotAllowOnPath() {
        KnightMoveCondition condition = new KnightMoveCondition();
        Board board = new Board(Arrays.asList(
                BlackKnight.createWithCoordinate(4, 4),
                BlackKnight.createWithCoordinate(7, 5)
        ));

        assertThat(condition.isSatisfyBy(board, BlackKnight.createWithCoordinate(4, 4), new Position(7, 5))).isFalse();
    }

    @DisplayName("나이트의 도착지에 적군 말이 있으면 이동")
    @Test
    void isSatisfyBy_otherColorAllowOnPath() {
        KnightMoveCondition condition = new KnightMoveCondition();

        int[] row = {2, 2, -2, -2, 1, -1, 1, -1};
        int[] col = {1, -1, 1, -1, 2, 2, -2, -2};

        for (int i = 0; i < row.length; i++) {
            int nextRow = 4 + row[i];
            int nextColumn = 4 + col[i];

            Board board = new Board(Arrays.asList(
                    BlackKnight.createWithCoordinate(4, 4),
                    WhiteKnight.createWithCoordinate(nextRow, nextColumn)
            ));

            assertThat(condition.isSatisfyBy(board, BlackKnight.createWithCoordinate(4, 4), new Position(nextRow, nextColumn))).isTrue();
        }
    }
}