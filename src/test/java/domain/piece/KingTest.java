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

import static domain.Turn.WHITE;
import static domain.point.File.B;
import static domain.point.Rank.THREE;
import static domain.point.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class KingTest {
    @Nested
    @DisplayName("킹이 이동하는 경우")
    class moveCase {
        @ParameterizedTest(name = "{displayName} - {0}")
        @ValueSource(strings = {"b4", "c3", "b2", "a3", "c4", "c2", "a2", "a4"})
        @DisplayName("주위에 어떤 장기말도 없을 때, 킹은 가로와 세로, 대각선 방향으로 1칸씩 이동할 수 있다.")
        void rookFirstMove(String toPoint) {
            // given
            Board board = Textures.makeBoard(Map.of(
                    new Point(B, THREE), new King(WHITE)
            ));

            // when & then
            assertDoesNotThrow(() -> board.move(new Movement(Point.fromSymbol("b3"), Point.fromSymbol(toPoint)), WHITE));
        }

        @ParameterizedTest(name = "{displayName} - {1}")
        @CsvSource(value = {"b5,위로 두 칸 이동 불가", "b1,아래 두 칸 이동 불가"})
        @DisplayName("킹을 자신의 반경에서 1칸을 초과하여 이동하려는 경우 예외가 발생한다.")
        void pawnMoveToInvalidDirection(String destination, String description) {
            // given
            Board board = Textures.makeBoard(Map.of(
                    new Point(B, THREE), new King(WHITE)
            ));

            // when & then
            assertThatThrownBy(() -> board.move(new Movement(Point.fromSymbol("b3"), Point.fromSymbol(destination)), WHITE))
                    .as(description)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ExceptionMessages.INVALID_DESTINATION);
        }

        @Test
        @DisplayName("이동하려는 위치에 우리 편의 기물이 있다면 이동이 불가능하다.")
        void givenTeamOnPoint_whenPawnMoveToPoint() {
            // given
            Board board = Textures.makeBoard(Map.of(
                    new Point(B, THREE), new King(WHITE),
                    new Point(B, TWO), new Pawn(WHITE)
            ));

            // when & then
            assertThatThrownBy(() -> board.move(new Movement(Point.fromSymbol("b3"), Point.fromSymbol("b2")), WHITE))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ExceptionMessages.INVALID_DESTINATION);
        }
    }
}
