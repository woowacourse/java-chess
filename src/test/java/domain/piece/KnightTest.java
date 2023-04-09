package domain.piece;

import dao.Movement;
import domain.Board;
import domain.point.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import util.ExceptionMessages;

import java.util.Map;

import static domain.Turn.BLACK;
import static domain.Turn.WHITE;
import static domain.point.File.*;
import static domain.point.Rank.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class KnightTest {
    @Nested
    @DisplayName("나이트가 이동하는 경우")
    class moveCase {
        @ParameterizedTest(name = "{displayName} - {0}")
        @ValueSource(strings = {"b5", "d5", "e4", "e2", "b1", "d1", "a4", "a2"})
        @DisplayName("주위에 어떤 장기말도 없을 때, 나이트는 L자 모양 8방향으로 이동할 수 있다.")
        void rookFirstMove(String toPoint) {
            // given
            Board board = Textures.makeBoard(Map.of(
                    new Point(C, THREE), new Knight(WHITE)
            ));

            // when & then
            assertDoesNotThrow(() -> board.move(new Movement(Point.fromSymbol("c3"), Point.fromSymbol(toPoint)), WHITE));
        }

        @ParameterizedTest(name = "{displayName} - {1}")
        @CsvSource(value = {"a1,왼쪽 아래 이동 불가", "b1,아래 이동 불가", "c1,오른쪽 아래 이동 불가",
                "c2,오른쪽 이동 불가", "c3,오른쪽 위 이동 불가", "b3,위 이동 불가",
                "a2,왼쪽 위 이동 불가", "a3,왼쪽 위 이동 불가"})
        @DisplayName("룩을 L자 외의 방향으로 이동하려는 경우 예외가 발생한다.")
        void pawnMoveToInvalidDirection(String destination, String description) {
            // given
            Board board = Textures.makeBoard(Map.of(
                    new Point(B, TWO), new Knight(BLACK)
            ));

            // when & then
            assertThatThrownBy(() -> board.move(new Movement(Point.fromSymbol("b2"), Point.fromSymbol(destination)), BLACK))
                    .as(description)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ExceptionMessages.INVALID_DESTINATION);
        }

        @Test
        @DisplayName("이동하려는 경로 사이에 다른 기물이 막고있을 경우, 건너뛰고 갈 수 있기 때문에 예외가 발생하지 않는다.")
        void givenPieceBetWeenTwoPoint_whenPawnMoveToPoint() {
            // given
            Board board = Textures.makeBoard(Map.of(
                    new Point(B, FOUR), new Pawn(BLACK),
                    new Point(B, THREE), new Pawn(BLACK),
                    new Point(B, TWO), new Pawn(BLACK),
                    new Point(C, FOUR), new Pawn(BLACK),
                    new Point(C, THREE), new Knight(WHITE),
                    new Point(C, TWO), new Pawn(BLACK),
                    new Point(D, FOUR), new Pawn(BLACK),
                    new Point(D, THREE), new Pawn(BLACK),
                    new Point(D, TWO), new Pawn(BLACK)
            ));

            // when & then
            assertDoesNotThrow(() -> board.move(new Movement(Point.fromSymbol("c3"), Point.fromSymbol("d5")), WHITE));
        }

        @Test
        @DisplayName("이동하려는 위치에 우리 편의 기물이 있다면 이동이 불가능하다.")
        void givenTeamOnPoint_whenPawnMoveToPoint() {
            // given
            Board board = Textures.makeBoard(Map.of(
                    new Point(B, ONE), new Pawn(BLACK),
                    new Point(A, THREE), new Knight(BLACK)
            ));

            // when & then
            assertThatThrownBy(() -> board.move(new Movement(Point.fromSymbol("a3"), Point.fromSymbol("b1")), BLACK))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ExceptionMessages.INVALID_DESTINATION);
        }
    }
}
