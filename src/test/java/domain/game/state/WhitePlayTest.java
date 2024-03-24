package domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.board.ChessBoard;
import domain.board.ChessBoardFactory;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.junit.jupiter.api.Test;

class WhitePlayTest {
    private final ChessBoard board = ChessBoardFactory.createInitialChessBoard();

    @Test
    void 게임을_시작하면_예외가_발생한다() {
        GameState whitePlay = new WhitePlay(board);
        assertThatThrownBy(whitePlay::start)
                .isExactlyInstanceOf(IllegalStateException.class)
                .hasMessageContaining("이미 게임이 시작되었습니다.");
    }

    @Test
    void 검은색_기물이_이동하는_경우_예외가_발생한다() {
        GameState whitePlay = new WhitePlay(board);
        Position source = new Position(File.A, Rank.SEVEN);
        Position target = new Position(File.A, Rank.SIX);

        assertThatThrownBy(() -> whitePlay.move(source, target))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("흰색 기물 차례입니다.");
    }

    @Test
    void 기물_이동_후_검은색_턴이_시작된다() {
        GameState whitePlay = new WhitePlay(board);
        Position source = new Position(File.A, Rank.TWO);
        Position target = new Position(File.A, Rank.THREE);

        GameState blackPlay = whitePlay.move(source, target);

        assertThat(blackPlay).isExactlyInstanceOf(BlackPlay.class);
    }

    @Test
    void 게임을_종료할_수_있다() {
        GameState whitePlay = new WhitePlay(board);
        GameState end = whitePlay.end();

        assertThat(end).isExactlyInstanceOf(End.class);
    }
}
