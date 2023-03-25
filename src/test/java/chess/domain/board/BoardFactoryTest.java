package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class BoardFactoryTest {

    @Test
    void 체스판을_초기_모양으로_생성한다() {
        Map<Position, Piece> board = BoardFactory.createBoard();

        assertThat(board.values()).hasSize(64);
    }

}
