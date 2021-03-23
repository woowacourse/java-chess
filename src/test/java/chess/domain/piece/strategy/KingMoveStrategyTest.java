package chess.domain.piece.strategy;

import chess.domain.board.Board;
import chess.domain.board.DefaultBoardInitializer;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static chess.domain.piece.Fixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KingMoveStrategyTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = DefaultBoardInitializer.getBoard();
    }

    private static Stream<Arguments> kingCanMoveTest() {
        return Stream.of(
                Arguments.of(Position.of("a3"), Position.of("b4"), true),   // 한 대각선 이동
                Arguments.of(Position.of("a3"), Position.of("a4"), true),   // 한 칸의 직선 방향 이동
                Arguments.of(Position.of("a3"), Position.of("b3"), true)   // 한 File 이동
        );
    }

    @DisplayName("의 이동 가능한 경우 테스트")
    @ParameterizedTest
    @MethodSource
    void kingCanMoveTest(Position from, Position to, boolean expected) {
        assertThat(whiteKing.canMove(board.createMoveOrder(from, to))).isEqualTo(expected);
    }

    @DisplayName("잘못된 방향으로 이동하려고 한다면 예외")
    @ParameterizedTest
    @CsvSource({"c3,e4", "h2, g5"})
    void throwExceptionWhenWrongDirection(String from, String to) {
        assertThatThrownBy(() -> whiteKing.canMove(board.createMoveOrder(Position.of(from), Position.of(to))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 없는 방향입니다.");
    }

    @DisplayName("킹 기물이 2칸 이상 움직이면 예외")
    @Test
    void whenBlockedThrowTest() {
        assertThatThrownBy(() -> whiteKing.canMove(board.createMoveOrder(Position.of("d3"), Position.of("d5"))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("킹이 움직일 수 있는 범위를 벗어났습니다.");
    }

    @DisplayName("목적지에 같은 팀의 말이 있다면 예외")
    @ParameterizedTest
    @CsvSource({"d1, e1", "d1, d2"})
    void throwExceptionWhenMoveToSameTeam(String from, String to) {
        assertThatThrownBy(() -> whiteKing.canMove(board.createMoveOrder(Position.of(from), Position.of(to))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("동일한 진영의 말이 있어서 행마할 수 없습니다.");
    }
}