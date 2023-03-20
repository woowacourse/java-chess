package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.factory.BoardFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BoardTest {

    @Test
    @DisplayName("체스 보드 생성")
    void create_success() {
        // when
        Board board = BoardFactory.createBoard();

        // then
        assertThat(board.getBoard().size()).isEqualTo(64);
    }

    @ParameterizedTest(name = "{0} -> {1} {displayName}")
    @CsvSource(value = {"c3:c3", "c2:b2", "c7:b7", "c7:c7"}, delimiter = ':')
    @DisplayName("같은 위치 혹은 같은 팀의 위치로 이동하는 경우 예외가 발생한다.")
    void throws_exception_when_move_invalid(final String start, final String end) {
        // given
        Board board = BoardFactory.createBoard();

        // when & then
        assertThatThrownBy(
            () -> board.switchPosition(start, end)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("체스 말이 정상적으로 움직인다.")
    void move_success() {
        // given
        Board board = BoardFactory.createBoard();

        // when
        board.switchPosition("a2", "a3");

        // then
        assertThat(board.findPiece("a3").getName()).isEqualTo("p");
    }

    @Test
    @DisplayName("lower 폰은 대각선에 있는 적을 공격할 수 있다.")
    void lower_pawn_attack_success() {
        // given
        Board board = BoardFactory.createBoard();
        board.switchPosition("a2", "a4");
        board.switchPosition("b7", "b5");

        // when & then
        assertDoesNotThrow(() -> board.switchPosition("a4", "b5"));
    }

    @Test
    @DisplayName("upper 폰은 대각선에 있는 적을 공격할 수 있다.")
    void upper_pawn_attack_success() {
        // given
        Board board = BoardFactory.createBoard();
        board.switchPosition("a2", "a4");
        board.switchPosition("b7", "b5");
        board.switchPosition("a1", "a2");

        // when & then
        assertDoesNotThrow(() -> board.switchPosition("b5", "a4"));
    }

    @Test
    @DisplayName("lower 폰은 빈공간에서는 대각선으로 움직일 수 없다.")
    void lower_pawn_does_not_move_diagonal_when_space_is_empty() {
        // given
        Board board = BoardFactory.createBoard();

        // when & then
        assertThatThrownBy(() -> board.switchPosition("a2", "b3"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("upper 폰은 빈공간에서는 대각선으로 움직일 수 없다.")
    void upper_pawn_does_not_move_diagonal_when_space_is_empty() {
        // given
        Board board = BoardFactory.createBoard();
        board.switchPosition("a2", "a3");

        // when & then
        assertThatThrownBy(() -> board.switchPosition("a7", "b6"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("lower 폰은 직진 공격을 할 수 없다.")
    void lower_pawn_does_not_attack_forward_enemy() {
        // given
        Board board = BoardFactory.createBoard();
        board.switchPosition("a2", "a4");
        board.switchPosition("a7", "a5");

        // when & then
        assertThatThrownBy(() -> board.switchPosition("a4", "a5"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("upper 폰은 직진 공격을 할 수 없다.")
    void upper_pawn_does_not_attack_forward_enemy() {
        // given
        Board board = BoardFactory.createBoard();
        board.switchPosition("a2", "a4");
        board.switchPosition("a7", "a5");
        board.switchPosition("a1", "a3");

        // when & then
        assertThatThrownBy(() -> board.switchPosition("a5", "a4"))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
