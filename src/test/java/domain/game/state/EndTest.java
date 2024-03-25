package domain.game.state;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.board.ChessBoard;
import domain.board.ChessBoardFactory;
import org.junit.jupiter.api.Test;

class EndTest {
    private final ChessBoard board = ChessBoardFactory.createInitialChessBoard();

    @Test
    void 게임을_시작하면_예외가_발생한다() {
        GameState end = new End(board);

        assertThatThrownBy(end::start)
                .isExactlyInstanceOf(IllegalStateException.class)
                .hasMessageContaining("게임이 종료되었습니다.");
    }

    @Test
    void 기물_이동_시_예외가_발생한다() {
        GameState end = new End(board);

        assertThatThrownBy(() -> end.move(null, null))
                .isExactlyInstanceOf(IllegalStateException.class)
                .hasMessageContaining("게임이 종료되었습니다.");
    }

    @Test
    void 게임을_종료하면_예외가_발생한다() {
        GameState end = new End(board);

        assertThatThrownBy(end::end)
                .isExactlyInstanceOf(IllegalStateException.class)
                .hasMessageContaining("게임이 종료되었습니다.");
    }
}
