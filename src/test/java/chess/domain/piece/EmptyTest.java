package chess.domain.piece;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static chess.fixture.CoordinateFixture.A_ONE;
import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class EmptyTest {
    @Test
    void Empty은_자신의_심볼을_반환한다() {
        Piece empty = new Empty(Team.WHITE, A_ONE);
        assertThat(empty.symbol()).isEqualTo('e');
    }
}
