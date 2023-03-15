package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class PawnTest {

    @Test
    void 폰이_정상적으로_생성된다() {
        // given
        final Pawn pawn = new Pawn(Color.WHITE);

        // expect
        assertThat(pawn.type()).isEqualTo(PieceType.PAWN);
    }
}
