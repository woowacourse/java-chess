package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class QueenTest {

    @Test
    void 퀸이_정상적으로_생성된다() {
        // given
        final Queen queen = Queen.from(Color.WHITE);

        // expect
        assertThat(queen.type()).isEqualTo(PieceType.QUEEN);
    }
}
