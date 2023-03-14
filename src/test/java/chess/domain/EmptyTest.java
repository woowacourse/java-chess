package chess.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class EmptyTest {

    @Test
    void 엠티의_이름이_점이_아닌경우_예외를_던진다() {
        assertThatThrownBy(() -> new Empty(" ", Side.NEUTRALITY))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Empty의 이름은 점이여야 합니다.");
    }

    @Test
    void 엠티의_진영이_중립이_아닌경우_예외를_던진다() {
        assertThatThrownBy(() -> new Empty(".", Side.BLACK))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Empty의 진영은 중립이여야 합니다.");
    }
}
