package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class EmptyTest {

    @Test
    void 빈_칸이_정상적으로_생성된다() {
        // given
        final Empty empty = Empty.get();

        // expect
        assertThat(empty.type()).isEqualTo(PieceType.EMPTY);
    }
}
