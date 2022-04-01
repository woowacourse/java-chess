package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class YAxisTest {
    @DisplayName("getBetween 메소드는 두 YAxis 사이에 위치한 YAxis 리스트를 반환한다.")
    @Test
    void getBetween_returnsBetweenYAxes() {
        // given
        YAxis from = YAxis.TWO;
        YAxis to = YAxis.SIX;

        // when
        List<YAxis> actual = YAxis.getBetween(from, to);

        // then
        assertThat(actual).containsExactly(YAxis.THREE, YAxis.FOUR, YAxis.FIVE);
    }
}
