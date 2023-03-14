package piece;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class KnightTest {
    @Test
    void Knight은_자신의_심볼을_반환한다() {
        Piece knight = new Knight(Team.WHITE, 1, 'a');
        assertThat(knight.symbol()).isEqualTo('n');
    }
}
