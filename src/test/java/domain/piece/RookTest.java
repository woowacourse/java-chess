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
import static domain.point.File.B;
import static domain.point.Rank.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class RookTest {
    @Nested
    @DisplayName("룩이 이동하는 경우")
    class moveCase {
        @ParameterizedTest(name = "{displayName} - {0}")
        @ValueSource(strings = {"b4", "b5", "c3", "b2", "b1", "a3"})
        @DisplayName("주위에 어떤 장기말도 없을 때, 룩은 가로와 세로 두 방향으로 무한히 이동할 수 있다.")
        void rookFirstMove(String toPoint) {
            // given
            Board board = Textures.makeBoard(Map.of(
                    new Point(B, THREE), new Rook(WHITE)
            ));

            // when & then
            assertDoesNotThrow(() -> board.move2(new Movement(Point.fromSymbol("b3"), Point.fromSymbol(toPoint)), WHITE));
        }

        @ParameterizedTest(name = "{displayName} - {1}")
        @CsvSource(value = {"a1,왼쪽 아래 이동 불가", "c1,오른쪽 아래 이동 불가", "c3,오른쪽 위 이동 불가", "a3,왼쪽 위 이동 불가"})
        @DisplayName("룩을 대각선 방향으로 이동하려는 경우 예외가 발생한다.")
        void pawnMoveToInvalidDirection(String destination, String description) {
            // given
            Board board = Textures.makeBoard(Map.of(
                    new Point(B, TWO), new Rook(WHITE)
            ));

            // when & then
            assertThatThrownBy(() -> board.move2(new Movement(Point.fromSymbol("b2"), Point.fromSymbol(destination)), WHITE))
                    .as(description)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ExceptionMessages.INVALID_DESTINATION);
        }

        @Test
        @DisplayName("이동하려는 경로 사이에 다른 기물이 막고있을 경우, 전진하지 못하고 예외가 발생한다.")
        void givenPieceBetWeenTwoPoint_whenPawnMoveToPoint() {
            // given
            Board board = Textures.makeBoard(Map.of(
                    new Point(B, TWO), new Rook(WHITE),
                    new Point(B, THREE), new Rook(BLACK)
            ));

            // when & then
            assertThatThrownBy(() -> board.move2(new Movement(Point.fromSymbol("b3"), Point.fromSymbol("b1")), BLACK))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ExceptionMessages.INVALID_DESTINATION);
        }

        @Test
        @DisplayName("이동하려는 위치에 우리 편의 기물이 있다면 이동이 불가능하다.")
        void givenTeamOnPoint_whenPawnMoveToPoint() {
            // given
            Board board = Textures.makeBoard(Map.of(
                    new Point(B, TWO), new Pawn(WHITE),
                    new Point(B, FOUR), new Rook(WHITE)
            ));

            // when & then
            assertThatThrownBy(() -> board.move2(new Movement(Point.fromSymbol("b4"), Point.fromSymbol("b2")), WHITE))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ExceptionMessages.INVALID_DESTINATION);
        }
    }
}
