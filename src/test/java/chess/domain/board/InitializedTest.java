package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class InitializedTest {

    @Test
    void 이미_초기화된_보드를_초기화를_하는_경우_예외를_던진다() {
        // given
        Board board = new Start();
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
        Board board = new Start();
        board = board.initialize();

        // when
        final boolean result = board.isInitialized();

        // then
        assertThat(result).isTrue();
    }

    @Test
    void 기물의_총_점수를_반환한다() {
        // given
        Board board = new Start();
        board = board.initialize();

        // when
        final double score = board.score(Color.WHITE);

        // then
        assertThat(score).isEqualTo(38.0);
    }

    @Test
    void 하나의_File에_여러_개의_폰이_존재하는_경우_각폰의_점수가_반이_된다() {
        // given
        Board board = new Start();
        board = board.initialize();
        board = board.move("e2", "e4");
        board = board.move("d7", "d5");
        board = board.move("e4", "d5");

        // when
        final double score = board.score(Color.WHITE);

        // then
        assertThat(score).isEqualTo(37.0);
    }
}
