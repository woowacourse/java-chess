package chess.domain.piece;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class EmptyTest {
    @Test
    void King은_자신의_pieceType을_반환한다() {
        assertThat(Empty.getInstance().pieceType()).isEqualTo(PieceType.EMPTY);
    }
}
