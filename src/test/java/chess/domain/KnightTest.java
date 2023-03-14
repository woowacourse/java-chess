package chess.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class KnightTest {

    @Test
    void 나이트의_이름이_대문자일때_흰색_진영이라면_예외를_던진다() {
        assertThatThrownBy(() -> new Knight("N", Side.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("흰색 진영일때는 대문자 이름이 올 수 없습니다.");
    }

    @Test
    void 나이트의_이름이_소문자일때_검정색_진영이라면_예외를_던진다() {
        assertThatThrownBy(() -> new Knight("n", Side.BLACK))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("검정색 진영일때는 소문자 이름이 올 수 없습니다.");
    }
}
