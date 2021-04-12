package chess.domain.piece.strategy;

import chess.domain.board.Board;
import chess.domain.board.InitBoardInitializer;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static chess.domain.piece.Fixture.whiteBishop;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BishopMoveStrategyTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = InitBoardInitializer.getBoard();
    }

    private static Stream<Arguments> bishopCanMoveTest() {
        return Stream.of(
                Arguments.of(Position.of("a3"), Position.of("b4"), true),   // 한 대각선 이동
                Arguments.of(Position.of("a3"), Position.of("d6"), true),   // 다수의 대각선 이동
                Arguments.of(Position.of("c5"), Position.of("a3"), true)   // 다수의 Rank 이동외
        );
    }

    @DisplayName("비숍의 이동 가능한 경우 테스트")
    @ParameterizedTest
    @MethodSource
    void bishopCanMoveTest(Position from, Position to, boolean expected) {
        assertThat(whiteBishop.canMove(board.createMoveOrder(from, to))).isEqualTo(expected);
    }

    @DisplayName("잘못된 방향으로 이동하려고 한다면 예외")
    @ParameterizedTest
    @CsvSource({"a1, a2", "a1, g1"})
    void throwExceptionWhenWrongDirection(String from, String to) {
        assertThatThrownBy(() -> whiteBishop.canMove(board.createMoveOrder(Position.of(from), Position.of(to))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 없는 방향입니다.");
    }

    @DisplayName("기물이 가는 길에 다른 기물이 있으면 예외")
    @Test
    void whenBlockedThrowTest() {
        assertThatThrownBy(() -> whiteBishop.canMove(board.createMoveOrder(Position.of("c1"), Position.of("e3"))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중간에 말이 있어 행마할 수 없습니다.");
    }

    @DisplayName("목적지에 같은 팀의 말이 있다면 예외")
    @ParameterizedTest
    @CsvSource({"c1, d2", "c1, b2"})
    void throwExceptionWhenMoveToSameTeam(String from, String to) {
        assertThatThrownBy(() -> whiteBishop.canMove(board.createMoveOrder(Position.of(from), Position.of(to))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("동일한 진영의 말이 있어서 행마할 수 없습니다.");
    }
}
