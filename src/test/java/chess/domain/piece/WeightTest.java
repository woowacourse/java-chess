package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeightTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new Weight(1, 'a'))
                .doesNotThrowAnyException();
    }

    @DisplayName("랭크 가중치에 곱셈을 적용할 수 있다.")
    @Test
    void multiply() {
        Weight weight = new Weight(1, 'a');

        Weight result = weight.multiplyAtRankWeight(-1);

        Weight expected = new Weight(-1, 'a');
        assertThat(result).isEqualTo(expected);
    }
}
