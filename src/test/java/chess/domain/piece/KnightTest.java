package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class KnightTest {

    @Test
    void 나이트가_정상적으로_생성된다() {
        // given
        final Knight knight = Knight.from(Color.WHITE);

        // expect
        assertThat(knight.type()).isEqualTo(PieceType.KNIGHT);
    }
}
