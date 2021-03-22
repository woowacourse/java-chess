package chess.domain.board;

import chess.domain.Side;
import chess.domain.position.Position;
import chess.exception.InvalidMovementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {
    Board board;

    @BeforeEach
    void setUp() {
        board = Board.createGamingBoard();
    }

    @ParameterizedTest(name = "이동 성공")
    @MethodSource("moveSuccessTestcase")
    void moveSuccess(Position from, Position to) {
        board.move(from, to, Side.WHITE);
    }

    private static Stream<Arguments> moveSuccessTestcase() {
        return Stream.of(
                Arguments.of(Position.from("b2"), Position.from("b3")),
                Arguments.of(Position.from("b2"), Position.from("b4")),
                Arguments.of(Position.from("b1"), Position.from("c3"))
        );
    }

    @ParameterizedTest(name = "이동 불가, 예외 발생")
    @MethodSource("moveFailTestcase")
    void moveFail(Position from, Position to) {
        assertThatThrownBy(() -> board.move(from, to, Side.WHITE))
                .isInstanceOf(InvalidMovementException.class);
    }

    private static Stream<Arguments> moveFailTestcase() {
        return Stream.of(
                Arguments.of(Position.from("a1"), Position.from("a3")),
                Arguments.of(Position.from("a1"), Position.from("a2")),
                Arguments.of(Position.from("c1"), Position.from("b2")),
                Arguments.of(Position.from("c1"), Position.from("a3"))
        );
    }

    @Test
    @DisplayName("왕이 잡히면 true 반환")
    void isGameSet() {
        Board board = new Board(BoardTestInitializer.init());
        board.move(Position.from("d5"), Position.from("f6"), Side.WHITE);
        assertThat(board.isGameSet()).isTrue();
    }

    @Test
    @DisplayName("보드에 남아있는 각 팀의 기물 점수 합산")
    void score() {
        Board board = new Board(BoardTestInitializer.init());
        assertThat(board.score(Side.BLACK)).isEqualTo(6);
        assertThat(board.score(Side.WHITE)).isEqualTo(3.5);
    }

    @Test
    @DisplayName("킹을 잡은 쪽을 승자로 반환")
    void winner() {
        Board board = new Board(BoardTestInitializer.init());
        board.move(Position.from("d5"), Position.from("f6"), Side.WHITE);
        assertThat(board.winner()).isEqualTo(Side.WHITE);
    }
}