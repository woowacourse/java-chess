import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.board.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PointTest {

    @Test
    @DisplayName("포인트 객체 생성")
    void generatePoint() {
        assertThat(Point.of("a1")).isEqualTo(Point.of("a1"));
        assertThat(Point.of("h8")).isEqualTo(Point.of("h8"));
    }

    @Test
    @DisplayName("유효하지 않은 포인트 생성시 예외를 반환")
    void validatePoint() {
        assertThatIllegalArgumentException().isThrownBy(() -> Point.of("a9"));
        assertThatIllegalArgumentException().isThrownBy(() -> Point.of("i1"));
    }


    @Test
    @DisplayName("캐싱한 값을 가져오는지 테스트")
    void getCachePoint() {
        assertThat(Point.of("a1")).isSameAs(Point.of("a1"));
    }
}
