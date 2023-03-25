package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.pieces.Team;
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
    @CsvSource(value = {"c3:c3", "b1:d2", "c7:c7", "b8:d7"}, delimiter = ':')
    @DisplayName("같은 위치 혹은 같은 팀의 위치로 이동하는 경우 예외가 발생한다.")
    void throws_exception_when_move_invalid(final String start, final String end) {
        // given
        Position source = Position.from(start);
        Position destination = Position.from(end);
        Board board = BoardFactory.createBoard();

        // when & then
        assertThatThrownBy(
            () -> board.isMovable(source, destination)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("체스 말이 정상적으로 움직인다.")
    void move_success() {
        // given
        Position source = Position.from("a2");
        Position destination = Position.from("a3");
        Board board = BoardFactory.createBoard();

        // when
        board.switchPosition(source, destination);

        // then
        assertThat(board.findPiece(destination).getTeam()).isEqualTo(Team.WHITE);
        assertThat(board.findPiece(source).getTeam()).isEqualTo(Team.EMPTY);
    }

    @Test
    @DisplayName("lower 폰은 대각선에 있는 적을 공격할 수 있다.")
    void lower_pawn_attack_success() {
        // given
        Board board = BoardFactory.createBoard();
        board.switchPosition(Position.from("a2"), Position.from("a4"));
        board.switchPosition(Position.from("b7"), Position.from("b5"));

        // when & then
        assertDoesNotThrow(() -> board.isMovable(Position.from("a4"), Position.from("b5")));
    }

    @Test
    @DisplayName("upper 폰은 대각선에 있는 적을 공격할 수 있다.")
    void upper_pawn_attack_success() {
        // given
        Board board = BoardFactory.createBoard();
        board.switchPosition(Position.from("a2"), Position.from("a4"));
        board.switchPosition(Position.from("b7"), Position.from("b5"));
        board.switchPosition(Position.from("a1"), Position.from("a2"));

        // when & then
        assertDoesNotThrow(() -> board.isMovable(Position.from("b5"), Position.from("a4")));
    }

    @Test
    @DisplayName("lower 폰은 빈공간에서는 대각선으로 움직일 수 없다.")
    void lower_pawn_does_not_move_diagonal_when_space_is_empty() {
        // given
        Board board = BoardFactory.createBoard();

        // when & then
        assertThatThrownBy(() -> board.isMovable(Position.from("a2"), Position.from("b3")))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("upper 폰은 빈공간에서는 대각선으로 움직일 수 없다.")
    void upper_pawn_does_not_move_diagonal_when_space_is_empty() {
        // given
        Board board = BoardFactory.createBoard();
        board.switchPosition(Position.from("a2"), Position.from("a3"));

        // when & then
        assertThatThrownBy(() -> board.isMovable(Position.from("a7"), Position.from("b6")))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("lower 폰은 직진 공격을 할 수 없다.")
    void lower_pawn_does_not_attack_forward_enemy() {
        // given
        Board board = BoardFactory.createBoard();
        board.switchPosition(Position.from("a2"), Position.from("a4"));
        board.switchPosition(Position.from("a7"), Position.from("a5"));

        // when & then
        assertThatThrownBy(() -> board.isMovable(Position.from("a4"), Position.from("a5")))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("upper 폰은 직진 공격을 할 수 없다.")
    void upper_pawn_does_not_attack_forward_enemy() {
        // given
        Board board = BoardFactory.createBoard();
        board.switchPosition(Position.from("a2"), Position.from("a4"));
        board.switchPosition(Position.from("a7"), Position.from("a5"));
        board.switchPosition(Position.from("a1"), Position.from("a3"));

        // when & then
        assertThatThrownBy(() -> board.isMovable(Position.from("a5"), Position.from("a4")))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
