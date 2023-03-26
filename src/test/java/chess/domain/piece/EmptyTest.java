package chess.domain.piece;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static chess.domain.PositionFixture.C_4;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class EmptyTest {

    @Test
    void Empty인지_알_수_있다() {
        final var piece = Empty.create(C_4);
        assertThat(piece.isEmpty()).isTrue();
    }
}
