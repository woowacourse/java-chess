package domain.position;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @DisplayName("Rank 객체의 value를 뺀다.")
    @Test
    void subtractFile() {
        Rank source = new Rank(3);
        Rank target = new Rank(2);

        assertThat(source.subtract(target)).isEqualTo(1);
    }

    @DisplayName("Rank의 number 범위를 벗어나면 에러를 발생한다.")
    @Test
    void rankRange() {
        assertAll(
                () -> assertThatThrownBy(() -> new Rank(9))
                        .isInstanceOf(IllegalArgumentException.class),
                () -> assertThatThrownBy(() -> new Rank(0))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }
}
