package chess.model.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashMap;
import org.junit.jupiter.api.Test;

public class BoardTest {
    @Test
    void 보드는_64개의_칸으로_구성한다() {
        // given
        Board board = new Board(new HashMap<>());

        // when, then
        assertThat(board.getSignatures().size()).isEqualTo(64);
    }

    @Test
    void 이동_경로에_다른_기물이_있으면_예외가_발생한다() {
        // given
        Board board = new InitialBoardGenerator().create();

        // when, then
        assertThatThrownBy(() -> board.move(Position.from(1, 1), Position.from(1, 3)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
