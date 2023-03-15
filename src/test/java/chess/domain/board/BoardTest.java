package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.pieces.Pawn;
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
        assertThat(board.getChessBoard().size()).isEqualTo(64);
    }

    @ParameterizedTest
    @CsvSource(value = {"c3, c3", "c2, b2", "c7,b7", "c7,c7"})
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
    @DisplayName("체스 말이 움직인다.")
    void move_success() {
        Board board = BoardFactory.createBoard();

        board.switchPosition("a2", "a3");

        assertThat(board.findPiece("a3").getName()).isEqualTo("p");
    }
}
