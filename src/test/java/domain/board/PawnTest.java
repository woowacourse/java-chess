package domain.board;

import domain.Board;
import domain.piece.Piece;
import domain.piece.pawn.BlackPawn;
import domain.piece.pawn.WhitePawn;
import domain.piece.rook.BlackRook;
import domain.piece.rook.WhiteRook;
import domain.point.Point;
import domain.util.ExceptionMessages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static domain.Turn.BLACK;
import static domain.Turn.WHITE;
import static domain.point.File.*;
import static domain.point.Rank.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PawnTest {
    @Nested
    @DisplayName("검정색 폰을 움직이는 경우")
    class BlackPawnCase {
        @Test
        @DisplayName("폰을 처음 움직이는 경우, 한 번에 두 칸씩 이동할 수 있다.")
        void pawnFirstMove() {
            // given
            Map<Point, Piece> boardStatus = Map.of(
                    new Point(A, THREE), new BlackPawn()
            );
            Board board = Textures.makeBoard(boardStatus);

            // when & then
            assertDoesNotThrow(() -> board.move(Point.fromSymbol("a3"), Point.fromSymbol("a1"), BLACK));
        }

        @Test
        @DisplayName("폰을 처음 움직인 이후에는, 한 번에 한 칸씩 전진할 수 있다.")
        void pawnMoveAfterFirst() {
            // then
            Board board = Textures.makeBoard(Map.of(
                    new Point(A, FIVE), new BlackPawn()
            ));

            // when
            board.move(Point.fromSymbol("a5"), Point.fromSymbol("a3"), BLACK);

            // then
            assertThatThrownBy(() -> board.move(Point.fromSymbol("a3"), Point.fromSymbol("a1"), BLACK))
                    .as("최초의 이동이 아닌데 두 칸을 한번에 전진하려는 경우 예외가 발생한다.")
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ExceptionMessages.INVALID_DESTINATION);
            assertDoesNotThrow(() -> board.move(Point.fromSymbol("a3"), Point.fromSymbol("a2"), BLACK));
        }

        @ParameterizedTest(name = "{displayName} - {1}")
        @CsvSource(value = {"a1,왼쪽 아래 이동 불가", "c1,오른쪽 아래 이동 불가", "c2,오른쪽 이동 불가", "c3,오른쪽 위 이동 불가",
                "b3,위 이동 불가", "a3,왼쪽 위 이동 불가", "a2,왼쪽 이동 불가"})
        @DisplayName("주위에 장기말이 없을 때, 폰을 위쪽 방향 외의 다른 방향으로 이동하려는 경우 예외가 발생한다.")
        void pawnMoveToInvalidDirection(String destination, String description) {
            // given
            Board board = Textures.makeBoard(Map.of(
                    new Point(B, TWO), new BlackPawn()
            ));

            // when & then
            assertThatThrownBy(() -> board.move(Point.fromSymbol("b2"), Point.fromSymbol(destination), BLACK))
                    .as(description)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ExceptionMessages.INVALID_DESTINATION);
        }

        @Test
        @DisplayName("이동하려는 경로 사이에 다른 기물이 막고있을 경우, 전진하지 못하고 예외가 발생한다.")
        void givenPieceBetWeenTwoPoint_whenPawnMoveToPoint() {
            // given
            Board board = Textures.makeBoard(Map.of(
                    new Point(B, THREE), new BlackPawn(),
                    new Point(B, TWO), new BlackPawn()
            ));

            // when & then
            assertThatThrownBy(() -> board.move(Point.fromSymbol("b3"), Point.fromSymbol("b1"), BLACK))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ExceptionMessages.INVALID_DESTINATION);
        }

        @Test
        @DisplayName("이동하려는 위치에 우리 편의 기물이 있다면 이동이 불가능하다.")
        void givenTeamOnPoint_whenPawnMoveToPoint() {
            // given
            Board board = Textures.makeBoard(Map.of(
                    new Point(B, FOUR), new BlackPawn(),
                    new Point(B, TWO), new BlackPawn()
            ));

            // when & then
            assertThatThrownBy(() -> board.move(Point.fromSymbol("b4"), Point.fromSymbol("b2"), BLACK))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ExceptionMessages.INVALID_DESTINATION);
        }

        @ParameterizedTest(name = "{displayName} - {0}")
        @ValueSource(strings = {"a1", "c1"})
        @DisplayName("검은색 기물의 전진 방향 대각선으로 상대 기물이 있다면, 상대 기물이 있는 위치로 이동할 수 있다.")
        void enemyOnDiagonal(String target) {
            // given
            Board board = Textures.makeBoard(Map.of(
                    new Point(B, TWO), new BlackPawn(),
                    new Point(A, ONE), new WhiteRook(),
                    new Point(C, ONE), new WhitePawn()
            ));

            // when & then
            assertDoesNotThrow(() -> board.move(Point.fromSymbol("b2"), Point.fromSymbol(target), BLACK));
        }
    }

    @Nested
    @DisplayName("하얀색 폰을 움직이는 경우")
    class WhitePawnCase {
        @Test
        @DisplayName("폰을 처음 움직이는 경우, 한 번에 두 칸씩 이동할 수 있다.")
        void pawnFirstMove() {
            // given
            Map<Point, Piece> status = Map.of(
                    new Point(B, ONE), new WhitePawn()
            );
            Board board = Textures.makeBoard(status);

            // when & then
            assertDoesNotThrow(() -> board.move(Point.fromSymbol("b1"), Point.fromSymbol("b3"), WHITE));
        }

        @Test
        @DisplayName("폰을 처음 움직인 이후에는, 한 번에 한 칸씩 전진할 수 있다.")
        void pawnMoveAfterFirst() {
            // given
            Board board = Textures.makeBoard(Map.of(
                    new Point(B, ONE), new WhitePawn()
            ));
            board.move(Point.fromSymbol("b1"), Point.fromSymbol("b3"), WHITE);

            // when & then
            assertThatThrownBy(() -> board.move(Point.fromSymbol("b3"), Point.fromSymbol("b5"), WHITE))
                    .as("최초의 이동이 아닌데 두 칸을 한번에 전진하려는 경우 예외가 발생한다.")
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ExceptionMessages.INVALID_DESTINATION);
            assertDoesNotThrow(() -> board.move(Point.fromSymbol("b3"), Point.fromSymbol("b4"), WHITE));
        }

        @ParameterizedTest(name = "{displayName} - {1}")
        @CsvSource(value = {"b1,아래 이동 불가", "a1,왼쪽 아래 이동 불가", "c1,오른쪽 아래 이동 불가",
                "c2,오른쪽 이동 불가", "c3,오른쪽 위 이동 불가", "a3,왼쪽 위 이동 불가", "a2,왼쪽 이동 불가"})
        @DisplayName("주위에 장기말이 없을 때, 폰을 위 방향 외의 다른 방향으로 이동하려는 경우 예외가 발생한다.")
        void pawnMoveToInvalidDirection(String destination, String description) {
            // given
            Board board = Textures.makeBoard(Map.of(
                    new Point(B, TWO), new WhitePawn()
            ));

            // when & then
            assertThatThrownBy(() -> board.move(Point.fromSymbol("b2"), Point.fromSymbol(destination), WHITE))
                    .as(description)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ExceptionMessages.INVALID_DESTINATION);
        }

        @Test
        @DisplayName("이동하려는 경로 사이에 다른 기물이 막고있을 경우, 전진하지 못하고 예외가 발생한다.")
        void givenPieceBetWeenTwoPoint_whenPawnMoveToPoint() {
            // given
            Board board = Textures.makeBoard(Map.of(
                    new Point(B, TWO), new WhitePawn(),
                    new Point(B, THREE), new WhitePawn()
            ));

            // when & then
            assertThatThrownBy(() -> board.move(Point.fromSymbol("b2"), Point.fromSymbol("b4"), WHITE))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ExceptionMessages.INVALID_DESTINATION);
        }

        @Test
        @DisplayName("이동하려는 위치에 우리 편의 기물이 있다면 이동이 불가능하다.")
        void givenTeamOnPoint_whenPawnMoveToPoint() {
            // given
            Board board = Textures.makeBoard(Map.of(
                    new Point(B, TWO), new WhitePawn(),
                    new Point(B, FOUR), new WhitePawn()
            ));

            // when & then
            assertThatThrownBy(() -> board.move(Point.fromSymbol("b2"), Point.fromSymbol("b4"), WHITE))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ExceptionMessages.INVALID_DESTINATION);
        }

        @ParameterizedTest(name = "{displayName} - {0}")
        @ValueSource(strings = {"a3", "c3"})
        @DisplayName("하얀색 기물의 전진 방향 대각선으로 상대 기물이 있다면, 상대 기물이 있는 위치로 이동할 수 있다.")
        void enemyOnDiagonal(String target) {
            // given
            Board board = Textures.makeBoard(Map.of(
                    new Point(B, TWO), new WhitePawn(),
                    new Point(A, THREE), new BlackRook(),
                    new Point(C, THREE), new BlackPawn()
            ));

            // when & then
            assertDoesNotThrow(() -> board.move(Point.fromSymbol("b2"), Point.fromSymbol(target), WHITE));
        }
    }
}
