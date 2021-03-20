package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("포인트 테스트")
class PointTest {

    @ParameterizedTest(name = "생성 테스트")
    @ValueSource(ints = {0, 7})
    void create(int point) {
        assertThat(Point.from(point)).isSameAs(Point.from(point));

        assertThatCode(() -> Point.from(point)).doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "인덱스를 벗어날 경우 예외 발생 테스트")
    @ValueSource(ints = {-1, 8})
    void validate(int point) {
        assertThatThrownBy(() -> Point.from(point)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("더하기가 가능한지 테스트")
    void isAdd() {
        Point point = Point.from(5);
        assertThat(point.isAdd(2)).isTrue();
        assertThat(point.isAdd(3)).isFalse();

        assertThat(point.isAdd(-5)).isTrue();
        assertThat(point.isAdd(-6)).isFalse();
    }

    @Test
    @DisplayName("더하기가 가능한지 테스트")
    void add() {
        Point point = Point.from(5);

        assertThatCode(() -> point.add(2)).doesNotThrowAnyException();

        assertThatCode(() -> point.add(-5)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("더하기가 가능한지 테스트")
    void addException() {
        Point point = Point.from(5);

        assertThatThrownBy(() -> point.add(3)).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> point.add(-6)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("비교 테스트")
    void equalsHash() {
        assertThat(Point.from(5).equals(Point.from(5))).isTrue();

        assertThat(Point.from(5).equals(Point.from(4))).isFalse();

        assertThat(Point.from(5).equals(Position.of("a1"))).isFalse();

        assertThat(Point.from(5).hashCode()).isEqualTo(Point.from(5).hashCode());
    }
}