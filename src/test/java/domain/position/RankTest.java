package domain.position;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @DisplayName("Rank 객체의 value를 뺀다.")
    @Test
    void subtractFile() {
        Rank source = new Rank(3);
        Rank target = new Rank(1);

        Assertions.assertThat(source.subtract(target)).isEqualTo(2);
    }
}
