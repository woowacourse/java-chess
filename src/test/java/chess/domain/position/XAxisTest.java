package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class XAxisTest {
    @DisplayName("getBetween 메소드는 두 XAxis 사이에 위치한 XAxis 리스트를 반환한다.")
    @Test
    void getBetween_returnsBetweenXAxes() {
        // given
        XAxis from = XAxis.B;
        XAxis to = XAxis.F;

        // when
        List<XAxis> actual = XAxis.getBetween(from, to);

        // then
        assertThat(actual).containsExactly(XAxis.C, XAxis.D, XAxis.E);
    }
}
