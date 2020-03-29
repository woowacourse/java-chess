package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class XTest {

    @Test
    void of() {
        assertThat(X.of(1)).isEqualTo(X.A);
        assertThat(X.of(2)).isEqualTo(X.B);
    }

    @Test
    void of_잘못된_사용() {
        assertThatThrownBy(() -> X.of(0)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void canIncrease_증가할_수_있을때() {
        assertThat(X.A.canIncrease(7)).isTrue();
        assertThat(X.B.canIncrease(-1)).isTrue();
    }

    @Test
    void canIncrease_증가할_수_없을때() {
        assertThat(X.B.canIncrease(7)).isFalse();
        assertThat(X.B.canIncrease(-2)).isFalse();
    }

    @Test
    void increase_증가할_수_있을때() {
        assertThat(X.B.increase(3)).isEqualTo(X.E);
    }

    @Test
    void increase_증가할_수_없을때() {
        assertThatThrownBy(() -> X.B.increase(-3)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void canDecrease_감소할_수_있을때() {
        assertThat(X.A.canDecrease(-6)).isTrue();
        assertThat(X.B.canDecrease(1)).isTrue();
    }

    @Test
    void canDecrease_감소할_수_없을때() {
        assertThat(X.B.canDecrease(7)).isFalse();
        assertThat(X.B.canDecrease(-7)).isFalse();
    }

    @Test
    void decrease_감소할_수_있을때() {
        assertThat(X.H.decrease(3)).isEqualTo(X.E);
    }

    @Test
    void decrease_감소할_수_없을때() {
        assertThatThrownBy(() -> X.B.decrease(-9)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void calculateDistance() {
        assertThat(X.A.calculateDistance(X.H)).isEqualTo(7);
        assertThat(X.A.calculateDistance(X.A)).isEqualTo(0);
    }

    @Test
    void isLargerThan() {
        assertThat(X.A.isLargerThan(X.A)).isFalse();
        assertThat(X.H.isLargerThan(X.G)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("getPathTestCase")
    void getPathFromTo(X from, X to, List<X> expected) {
        assertThat(X.getPathFromTo(from, to)).isEqualTo(expected);
    }

    private static Stream<Arguments> getPathTestCase() {
        return Stream.of(
            Arguments.of(X.A, X.H, Arrays.asList(X.A, X.B, X.C, X.D, X.E, X.F, X.G, X.H)),
            Arguments.of(X.A, X.A, Collections.singletonList(X.A))
        );
    }
}
