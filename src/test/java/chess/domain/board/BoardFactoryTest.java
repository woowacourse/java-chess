package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class BoardFactoryTest {

    @Test
    void 체스판을_초기_모양으로_생성한다() {
        Board board = BoardFactory.createBoard();

        assertThat(board.getBoards().values()).hasSize(64);
    }

}
