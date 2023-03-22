package chess.domain.piece;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class TypeTest {

    @Test
    void 진영에_맞는_심볼을_반환한다() {
        final Type type = Type.PAWN;

        final SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(type.getSymbol(Side.WHITE)).isEqualTo("p");
        softAssertions.assertThat(type.getSymbol(Side.BLACK)).isEqualTo("P");

        softAssertions.assertAll();
    }
}
