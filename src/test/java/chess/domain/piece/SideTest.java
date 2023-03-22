package chess.domain.piece;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class SideTest {

    @Test
    void 자신의_진영을_판단할_수_있다() {
        final Side side = Side.WHITE;

        final SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(side.isWhite()).isTrue();
        softAssertions.assertThat(side.isBlack()).isFalse();
        softAssertions.assertThat(side.isNeutrality()).isFalse();

        softAssertions.assertAll();
    }
}
