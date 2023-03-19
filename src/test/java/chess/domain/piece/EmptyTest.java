package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class EmptyTest {

    @Test
    void 빈_기물을_정상적으로_반환한다() {
        // given
        final Empty empty = Empty.instance();

        // expect
        assertThat(empty.type()).isEqualTo(PieceType.EMPTY);
    }
}
