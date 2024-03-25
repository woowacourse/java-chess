package domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.board.ChessBoard;
import domain.board.ChessBoardFactory;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.junit.jupiter.api.Test;

class BlackPlayTest {
    private final ChessBoard board = ChessBoardFactory.createInitialChessBoard();

    @Test
    void 게임을_시작하면_예외가_발생한다() {
        GameState blackPlay = new BlackPlay(board);
        assertThatThrownBy(blackPlay::start)
                .isExactlyInstanceOf(IllegalStateException.class)
                .hasMessageContaining("이미 게임이 시작되었습니다.");
    }

    @Test
    void 흰색_기물이_이동하는_경우_예외가_발생한다() {
        GameState blackPlay = new BlackPlay(board);
        Position source = new Position(File.A, Rank.TWO);
        Position target = new Position(File.A, Rank.THREE);

        assertThatThrownBy(() -> blackPlay.move(source, target))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("검은색 기물 차례입니다.");
    }

    @Test
    void 기물_이동_후_흰색_턴이_시작된다() {
        GameState blackPlay = new BlackPlay(board);
        Position source = new Position(File.A, Rank.SEVEN);
        Position target = new Position(File.A, Rank.SIX);

        GameState whitePlay = blackPlay.move(source, target);

        assertThat(whitePlay).isExactlyInstanceOf(WhitePlay.class);
    }

    @Test
    void 게임을_종료할_수_있다() {
        GameState blackPlay = new BlackPlay(board);
        GameState end = blackPlay.end();

        assertThat(end).isExactlyInstanceOf(End.class);
    }
}
