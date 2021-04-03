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

import static chess.domain.piece.Fixture.whiteRook;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RookMoveStrategyTest {
    private ChessBoard chessBoard;

    @BeforeEach
    void setUp() {
        chessBoard = BoardFactory.createBoard();
    }

    private static Stream<Arguments> rookCanMoveTest() {
        return Stream.of(
                Arguments.of(Position.of("a3"), Position.of("b3"), true),   // 한 File 이동
                Arguments.of(Position.of("a3"), Position.of("h3"), true),   // 다수의 File 이동
                Arguments.of(Position.of("a3"), Position.of("a6"), true)   // 다수의 Rank 이동외
        );
    }

    @DisplayName("룩의 이동 가능한 경우 테스트")
    @ParameterizedTest
    @MethodSource
    void rookCanMoveTest(Position from, Position to, boolean expected) {
        assertThat(whiteRook.canMove(chessBoard.createMoveRoute(from, to))).isEqualTo(expected);
    }

    @DisplayName("잘못된 방향으로 이동하려고 한다면 예외")
    @ParameterizedTest
    @CsvSource({"a1, b2", "a1, b8"})
    void throwExceptionWhenWrongDirection(String from, String to) {
        assertThatThrownBy(() -> whiteRook.canMove(chessBoard.createMoveRoute(Position.of(from), Position.of(to))))
                .isInstanceOf(DomainException.class)
                .hasMessage("움직일 수 없는 방향입니다.");
    }

    @DisplayName("룩이 가는 길에 다른 기물이 있으면 예외")
    @Test
    void whenBlockedThrowTest() {
        assertThatThrownBy(() -> whiteRook.canMove(chessBoard.createMoveRoute(Position.of("a1"), Position.of("a3"))))
                .isInstanceOf(DomainException.class)
                .hasMessage("중간에 말이 있어 행마할 수 없습니다.");
    }

    @DisplayName("목적지에 같은 팀의 말이 있다면 예외")
    @ParameterizedTest
    @CsvSource({"a1, b1", "a1, a2"})
    void throwExceptionWhenMoveToSameTeam(String from, String to) {
        assertThatThrownBy(() -> whiteRook.canMove(chessBoard.createMoveRoute(Position.of(from), Position.of(to))));
    }
}