package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PointTest {

    @ParameterizedTest(name = "생성 테스트")
    @ValueSource(ints = {0, 7})
    void create(int point) {
        assertThat(Point.from(point)).isSameAs(Point.from(point));

        assertThatCode(() -> Point.from(point))
            .doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "인덱스를 벗어날 경우 예외 발생 테스트")
    @ValueSource(ints = {-1, 8})
    void validate(int point) {
        assertThatThrownBy(() -> Point.from(point))
            .isInstanceOf(IllegalArgumentException.class);
    }
}