package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
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

    @Test
    void 아군_진영인지_판단할_수_있다() {
        final Side side = Side.WHITE;

        final SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(side.isAlly(Side.WHITE)).isTrue();
        softAssertions.assertThat(side.isAlly(Side.BLACK)).isFalse();
        softAssertions.assertThat(side.isAlly(Side.NEUTRALITY)).isFalse();

        softAssertions.assertAll();
    }

    @Test
    void 중립_진영이_아군_진영을_판단하려_하면_예외를_던진다() {
        final Side side = Side.NEUTRALITY;

        assertThatThrownBy(() -> side.isAlly(Side.NEUTRALITY))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중립은 아군이 존재하지 않습니다.");
    }
}
