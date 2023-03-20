package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class InitializedStateTest {

    @Test
    void 이미_초기화된_보드를_초기화를_하는_경우_예외를_던진다() {
        // given
        Board board = new InitialState();
        board = board.initialize();

        // expect
        final Board finalBoard = board;
        assertThatThrownBy(finalBoard::initialize)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 초기화된 보드입니다.");
    }

    @Test
    void 보드가_초기화_되었는지_확인한다() {
        // given
        Board board = new InitialState();
        board = board.initialize();

        // when
        final boolean result = board.isInitialized();

        // then
        assertThat(result).isTrue();
    }

    @Test
    void 게임의_결과를_반환한다() {
        // given
        Board board = new InitialState();
        board = board.initialize();

        // when
        final GameResult result = board.getResult();

        // then
        assertThat(result.getBoard()).hasSize(64);
    }
}
