package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class EndStateTest {

    @Test
    void 왕이_잡히는_경우_게임이_종료된다() {
        // given
        Board board = new InitialState();
        board = board.initialize();
        board = board.move("e2", "e4");
        board = board.move("e7", "e5");
        board = board.move("d1", "h5");
        board = board.move("f7", "f5");

        // when
        board = board.move("h5", "e8");

        // then
        assertThat(board.isEnd()).isTrue();
    }
}
