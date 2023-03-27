package domain.piece;

import domain.Board;
import domain.exception.BlockedPathException;
import domain.exception.InvalidDestinationPointException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class KingTest {
    @Nested
    @DisplayName("킹이 이동하는 경우")
    class moveCase {
        @ParameterizedTest(name = "{displayName} - {0}")
        @ValueSource(strings = {"b4", "c3", "b2", "a3", "c4", "c2", "a2", "a4"})
        @DisplayName("주위에 어떤 장기말도 없을 때, 킹은 가로와 세로, 대각선 방향으로 1칸씩 이동할 수 있다.")
        void rookFirstMove(String toPoint) {
            List<List<Piece>> boardStatus = Arrays.asList(
                    Arrays.asList(new Empty(), new Empty(), new Empty()), // a5, b5, c5
                    Arrays.asList(new Empty(), new Empty(), new Empty()), // a4, b4, c4
                    Arrays.asList(new Empty(), new WhiteKing(), new Empty()), // a3, b3, c3
                    Arrays.asList(new Empty(), new Empty(), new Empty()), // a2, b2, c2
                    Arrays.asList(new Empty(), new Empty(), new Empty()) // a1, b1, c1
            );
            Board board = new Board(boardStatus);

            assertDoesNotThrow(() -> board.move("b3", toPoint));
        }

        @Test
        @DisplayName("킹을 자신의 반경에서 1칸을 초과하여 이동하려는 경우 예외가 발생한다.")
        void pawnMoveToInvalidDirection() {
            List<List<Piece>> boardStatus = Arrays.asList(
                    Arrays.asList(new Empty(), new Empty(), new Empty()), // a5, b5, c5
                    Arrays.asList(new Empty(), new Empty(), new Empty()), // a4, b4, c4
                    Arrays.asList(new Empty(), new WhiteKing(), new Empty()), // a3, b3, c3
                    Arrays.asList(new Empty(), new Empty(), new Empty()), // a2, b2, c2
                    Arrays.asList(new Empty(), new Empty(), new Empty()) // a1, b1, c1
            );
            Board board = new Board(boardStatus);

            assertAll(
                    () -> assertThatThrownBy(() -> board.move("b3", "b5"))
                            .as("위로 두 칸 이동 불가").isInstanceOf(InvalidDestinationPointException.class),
                    () -> assertThatThrownBy(() -> board.move("b3", "b1"))
                            .as("아래 두 칸 이동 불가").isInstanceOf(InvalidDestinationPointException.class)
            );
        }

        @Test
        @DisplayName("이동하려는 위치에 우리 편의 기물이 있다면 이동이 불가능하다.")
        void givenTeamOnPoint_whenPawnMoveToPoint() {
            List<List<Piece>> boardStatus = Arrays.asList(
                    Arrays.asList(new Empty(), new Empty(), new Empty()), // a1, b1, c1
                    Arrays.asList(new Empty(), new WhitePawn(), new Empty()), // a2, b2, c2
                    Arrays.asList(new Empty(), new WhiteKing(), new Empty()) // a3, b3, c3
            );
            Board board = new Board(boardStatus);

            assertThatThrownBy(() -> board.move("b3", "b2"))
                    .isInstanceOf(BlockedPathException.class);
        }
    }
}