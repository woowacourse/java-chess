package piece;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class QueenTest {
    @Test
    void Queen은_자신의_심볼을_반환한다() {
        Piece queen = new Queen(Team.WHITE, 1, 'a');
        assertThat(queen.symbol()).isEqualTo('q');
    }
}



