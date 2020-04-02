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

public class FileTest {

    @Test
    void of() {
        assertThat(File.of(1)).isEqualTo(File.A);
        assertThat(File.of(2)).isEqualTo(File.B);
    }

    @Test
    void of_잘못된_사용() {
        assertThatThrownBy(() -> File.of(0)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void canIncrease_증가할_수_있을때() {
        assertThat(File.A.canIncrease(7)).isTrue();
        assertThat(File.B.canIncrease(-1)).isTrue();
    }

    @Test
    void canIncrease_증가할_수_없을때() {
        assertThat(File.B.canIncrease(7)).isFalse();
        assertThat(File.B.canIncrease(-2)).isFalse();
    }

    @Test
    void increase_증가할_수_있을때() {
        assertThat(File.B.increase(3)).isEqualTo(File.E);
    }

    @Test
    void increase_증가할_수_없을때() {
        assertThatThrownBy(() -> File.B.increase(-3)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void canDecrease_감소할_수_있을때() {
        assertThat(File.A.canDecrease(-6)).isTrue();
        assertThat(File.B.canDecrease(1)).isTrue();
    }

    @Test
    void canDecrease_감소할_수_없을때() {
        assertThat(File.B.canDecrease(7)).isFalse();
        assertThat(File.B.canDecrease(-7)).isFalse();
    }

    @Test
    void decrease_감소할_수_있을때() {
        assertThat(File.H.decrease(3)).isEqualTo(File.E);
    }

    @Test
    void decrease_감소할_수_없을때() {
        assertThatThrownBy(() -> File.B.decrease(-9)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void calculateDistance() {
        assertThat(File.A.calculateDistance(File.H)).isEqualTo(7);
        assertThat(File.A.calculateDistance(File.A)).isEqualTo(0);
    }

    @Test
    void isLargerThan() {
        assertThat(File.A.isLargerThan(File.A)).isFalse();
        assertThat(File.H.isLargerThan(File.G)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("getPathTestCase")
    void getPathFromTo(File from, File to, List<File> expected) {
        assertThat(File.getPathFromTo(from, to)).isEqualTo(expected);
    }

    private static Stream<Arguments> getPathTestCase() {
        return Stream.of(
            Arguments.of(
                File.A, File.H,
                Arrays.asList(File.A, File.B, File.C, File.D, File.E, File.F, File.G, File.H)),
            Arguments.of(File.A, File.A, Collections.singletonList(File.A))
        );
    }
}
