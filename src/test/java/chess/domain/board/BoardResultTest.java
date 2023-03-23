package chess.domain.board;

import chess.domain.piece.Color;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class BoardResultTest {

    @Test
    void 기물들의_점수를_계산할_수_있다() {
        // given
        final Board board = BoardFactory.create();
        final BoardResult boardResult = new BoardResult(board.getBoard());

        // when
        final double blackScore = boardResult.calculatePoints(Color.BLACK);

        // then
        Assertions.assertThat(blackScore).isEqualTo(38.0);
    }

}