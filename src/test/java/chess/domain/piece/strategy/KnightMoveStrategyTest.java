package chess.domain.piece.strategy;

import chess.domain.board.ChessBoard;
import chess.domain.board.BoardFactory;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static chess.domain.piece.Fixture.whiteKnight;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KnightMoveStrategyTest {
    private ChessBoard chessBoard;

    @BeforeEach
    void setUp() {
        chessBoard = BoardFactory.createBoard();
    }

    private static Stream<Arguments> knightCanMoveTest() {
        return Stream.of(
                Arguments.of(Position.of("b1"), Position.of("c3"), true),
                Arguments.of(Position.of("b1"), Position.of("a3"), true),
                Arguments.of(Position.of("a3"), Position.of("c4"), true)
                // TODO 8방을 모두 테스
        );
    }

    @DisplayName("나이트의 이동 가능한 경우 테스트")
    @ParameterizedTest
    @MethodSource
    void knightCanMoveTest(Position from, Position to, boolean expected) {
        assertThat(whiteKnight.canMove(chessBoard.createMoveRoute(from, to))).isEqualTo(expected);
    }

    @DisplayName("잘못된 방향으로 이동하려고 한다면 예외")
    @ParameterizedTest
    @CsvSource({"a1, a3", "a1, b8"})
    void throwExceptionWhenWrongDirection(String from, String to) {
        assertThatThrownBy(() -> whiteKnight.canMove(chessBoard.createMoveRoute(Position.of(from), Position.of(to))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 없는 방향입니다.");
    }

    @DisplayName("목적지에 같은 팀의 말이 있다면 예외")
    @ParameterizedTest
    @CsvSource({"c3, b1", "c3, d1"})
    void throwExceptionWhenMoveToSameTeam(String from, String to) {
        assertThatThrownBy(() -> whiteKnight.canMove(chessBoard.createMoveRoute(Position.of(from), Position.of(to))));
    }
}