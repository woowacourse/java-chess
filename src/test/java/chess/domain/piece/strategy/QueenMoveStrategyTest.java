package chess.domain.piece.strategy;

import chess.domain.board.ChessBoard;
import chess.domain.board.BoardFactory;
import chess.domain.position.Position;
import chess.exception.DomainException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static chess.domain.piece.Fixture.whiteQueen;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QueenMoveStrategyTest {
    private ChessBoard chessBoard;

    @BeforeEach
    void setUp() {
        chessBoard = BoardFactory.createBoard();
    }

    private static Stream<Arguments> queenCanMoveTest() {
        return Stream.of(
                Arguments.of(Position.of("a3"), Position.of("b4"), true),   // 한 대각선 이동
                Arguments.of(Position.of("a3"), Position.of("a4"), true),   // 한 칸의 직선 방향 이동
                Arguments.of(Position.of("a3"), Position.of("d6"), true),   // 다수의 대각선 이동
                Arguments.of(Position.of("a3"), Position.of("b3"), true),   // 한 File 이동
                Arguments.of(Position.of("a3"), Position.of("h3"), true),   // 다수의 File 이동
                Arguments.of(Position.of("a3"), Position.of("a6"), true)   // 다수의 Rank 이동외
        );
    }

    @DisplayName("의 이동 가능한 경우 테스트")
    @ParameterizedTest
    @MethodSource
    void queenCanMoveTest(Position from, Position to, boolean expected) {
        assertThat(whiteQueen.canMove(chessBoard.createMoveRoute(from, to))).isEqualTo(expected);
    }

    @DisplayName("잘못된 방향으로 이동하려고 한다면 예외")
    @ParameterizedTest
    @CsvSource({"c3,e4", "h2, g5"})
    void throwExceptionWhenWrongDirection(String from, String to) {
        assertThatThrownBy(() -> whiteQueen.canMove(chessBoard.createMoveRoute(Position.of(from), Position.of(to))))
                .isInstanceOf(DomainException.class)
                .hasMessage("움직일 수 없는 방향입니다.");
    }

    @DisplayName("기물이 가는 길에 다른 기물이 있으면 예외")
    @Test
    void whenBlockedThrowTest() {
        assertThatThrownBy(() -> whiteQueen.canMove(chessBoard.createMoveRoute(Position.of("d1"), Position.of("d3"))))
                .isInstanceOf(DomainException.class)
                .hasMessage("중간에 말이 있어 행마할 수 없습니다.");
    }

    @DisplayName("목적지에 같은 팀의 말이 있다면 예외")
    @ParameterizedTest
    @CsvSource({"d1, e1", "d1, d2"})
    void throwExceptionWhenMoveToSameTeam(String from, String to) {
        assertThatThrownBy(() -> whiteQueen.canMove(chessBoard.createMoveRoute(Position.of(from), Position.of(to))))
                .isInstanceOf(DomainException.class)
                .hasMessage("동일한 진영의 말이 있어서 행마할 수 없습니다.");
    }
}
