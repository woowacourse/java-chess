package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class KingTest {

    @Test
    void 킹이_정상적으로_생성된다() {
        // given
        final King king = King.from(Color.WHITE);

        // expect
        assertThat(king.type()).isEqualTo(PieceType.KING);
    }
}
