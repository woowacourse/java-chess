package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PositionTest {

    @ParameterizedTest
    @CsvSource({"0, 1", "2, 9"})
    void 좌표의_값이_1과_8사이가_아니라면_예외를_던진다(final int x, final int y) {
        assertThatThrownBy(() -> new Position(x, y))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("좌표의 값은 1 ~ 8 사이여야 합니다.");
    }

    @Test
    void 내부의_값이_같다면_동등하다() {
        assertThat(new Position(2, 2)).isEqualTo(new Position(2, 2));
    }
}
