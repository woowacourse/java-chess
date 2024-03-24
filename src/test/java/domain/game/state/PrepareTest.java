package domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.board.ChessBoard;
import domain.board.ChessBoardFactory;
import org.junit.jupiter.api.Test;

class PrepareTest {
    private final ChessBoard board = ChessBoardFactory.createInitialChessBoard();

    @Test
    void 게임을_시작하면_흰색_턴이_시작된다() {
        GameState prepare = new Prepare(board);
        GameState whitePlay = prepare.start();

        assertThat(whitePlay).isExactlyInstanceOf(WhitePlay.class);
    }

    @Test
    void 기물_이동_시_예외가_발생한다() {
        GameState prepare = new Prepare(board);
        assertThatThrownBy(() -> prepare.move(null, null))
                .isExactlyInstanceOf(IllegalStateException.class)
                .hasMessageContaining("게임 시작 전입니다.");
    }

    @Test
    void 게임을_종료하면_예외가_발생한다() {
        GameState prepare = new Prepare(board);
        assertThatThrownBy(prepare::end)
                .isExactlyInstanceOf(IllegalStateException.class)
                .hasMessageContaining("게임 시작 전입니다.");
    }
}
