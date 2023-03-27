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

class KnightTest {
    @Nested
    @DisplayName("나이트가 이동하는 경우")
    class moveCase {
        @ParameterizedTest(name = "{displayName} - {0}")
        @ValueSource(strings = {"b5", "d5", "e4", "e2", "b1", "d1", "a4", "a2"})
        @DisplayName("주위에 어떤 장기말도 없을 때, 나이트는 L자 모양 8방향으로 이동할 수 있다.")
        void rookFirstMove(String toPoint) {
            List<List<Piece>> boardStatus = Arrays.asList(
                    Arrays.asList(new Empty(), new Empty(), new Empty(), new Empty(), new Empty()), // a5, b5, c5, d5, e5
                    Arrays.asList(new Empty(), new Empty(), new Empty(), new Empty(), new Empty()), // a4, b4, c4, d4, e4
                    Arrays.asList(new Empty(), new Empty(), new WhiteKnight(), new Empty(), new Empty()), // a3, b3, c3, d3, e3
                    Arrays.asList(new Empty(), new Empty(), new Empty(), new Empty(), new Empty()), // a2, b2, c2, d2, e2
                    Arrays.asList(new Empty(), new Empty(), new Empty(), new Empty(), new Empty()) // a1, b1, c1, d1, e1
            );
            Board board = new Board(boardStatus);

            assertDoesNotThrow(() -> board.move("c3", toPoint));
        }

        @Test
        @DisplayName("룩을 L자 외의 방향으로 이동하려는 경우 예외가 발생한다.")
        void pawnMoveToInvalidDirection() {
            List<List<Piece>> boardStatus = Arrays.asList(
                    Arrays.asList(new Empty(), new Empty(), new Empty()), // a1, b1, c1
                    Arrays.asList(new Empty(), new BlackKnight(), new Empty()), // a2, b2, c2
                    Arrays.asList(new Empty(), new Empty(), new Empty()) // a3, b3, c3
            );
            Board board = new Board(boardStatus);

            assertAll(
                    () -> assertThatThrownBy(() -> board.move("b2", "a1"))
                            .as("왼쪽 아래 이동 불가").isInstanceOf(InvalidDestinationPointException.class),
                    () -> assertThatThrownBy(() -> board.move("b2", "b1"))
                            .as("아래 이동 불가").isInstanceOf(InvalidDestinationPointException.class),
                    () -> assertThatThrownBy(() -> board.move("b2", "c1"))
                            .as("오른쪽 아래 이동 불가").isInstanceOf(InvalidDestinationPointException.class),
                    () -> assertThatThrownBy(() -> board.move("b2", "c2"))
                            .as("오른쪽 이동 불가").isInstanceOf(InvalidDestinationPointException.class),
                    () -> assertThatThrownBy(() -> board.move("b2", "c3"))
                            .as("오른쪽 위 이동 불가").isInstanceOf(InvalidDestinationPointException.class),
                    () -> assertThatThrownBy(() -> board.move("b2", "b3"))
                            .as("위 이동 불가").isInstanceOf(InvalidDestinationPointException.class),
                    () -> assertThatThrownBy(() -> board.move("b2", "a2"))
                            .as("왼쪽 위 이동 불가").isInstanceOf(InvalidDestinationPointException.class),
                    () -> assertThatThrownBy(() -> board.move("b2", "a3"))
                            .as("왼쪽 이동 불가").isInstanceOf(InvalidDestinationPointException.class),
                    () -> assertThatThrownBy(() -> board.move("b2", "a2"))
                            .as("왼쪽 이동 불가").isInstanceOf(InvalidDestinationPointException.class)
            );
        }

        @Test
        @DisplayName("이동하려는 경로 사이에 다른 기물이 막고있을 경우, 건너뛰고 갈 수 있기 때문에 예외가 발생하지 않는다.")
        void givenPieceBetWeenTwoPoint_whenPawnMoveToPoint() {
            List<List<Piece>> boardStatus = Arrays.asList(
                    Arrays.asList(new Empty(), new Empty(), new Empty(), new Empty(), new Empty()), // a5, b5, c5, d5, e5
                    Arrays.asList(new Empty(), new Empty(), new BlackPawn(), new BlackPawn(), new Empty()), // a4, b4, c4, d4, e4
                    Arrays.asList(new Empty(), new Empty(), new WhiteKnight(), new Empty(), new Empty()), // a3, b3, c3, d3, e3
                    Arrays.asList(new Empty(), new Empty(), new Empty(), new Empty(), new Empty()), // a2, b2, c2, d2, e2
                    Arrays.asList(new Empty(), new Empty(), new Empty(), new Empty(), new Empty()) // a1, b1, c1, d1, e1
            );
            Board board = new Board(boardStatus);

            assertDoesNotThrow(() -> board.move("c3", "d5"));
        }

        @Test
        @DisplayName("이동하려는 위치에 우리 편의 기물이 있다면 이동이 불가능하다.")
        void givenTeamOnPoint_whenPawnMoveToPoint() {
            List<List<Piece>> boardStatus = Arrays.asList(
                    Arrays.asList(new Empty(), new BlackPawn(), new Empty()), // a1, b1, c1
                    Arrays.asList(new Empty(), new Empty(), new Empty()), // a2, b2, c2
                    Arrays.asList(new BlackKnight(), new Empty(), new Empty()) // a3, b3, c3
            );
            Board board = new Board(boardStatus);

            assertThatThrownBy(() -> board.move("a3", "b1"))
                    .isInstanceOf(BlockedPathException.class);
        }
    }
}