package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class RookTest {

    @Test
    void 룩이_정상적으로_생성된다() {
        // given
        final Rook rook = new Rook(Color.WHITE);

        // expect
        assertThat(rook.type()).isEqualTo(PieceType.ROOK);
    }
}
