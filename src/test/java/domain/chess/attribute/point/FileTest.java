package domain.chess.attribute.point;

import domain.chess.File;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class FileTest {
    private static Stream<Arguments> maskingValues() {
        return Stream.of(
                Arguments.of(File.A, 'a'),
                Arguments.of(File.B, 'b'),
                Arguments.of(File.C, 'c'),
                Arguments.of(File.D, 'd'),
                Arguments.of(File.E, 'e'),
                Arguments.of(File.F, 'f'),
                Arguments.of(File.G, 'g'),
                Arguments.of(File.H, 'h')
        );
    }

    @ParameterizedTest
    @MethodSource("maskingValues")
    @DisplayName("파일은 가지고 있는 값에 따라 생성 인스턴스가 바뀐다.")
    void generate_different_instance_from_character(final File file, final char text) {
        assertThat(File.from(text))
                .isEqualTo(file);
    }

    @Test
    @DisplayName("A는 파일의 왼쪽 끝이다.")
    void a_is_left_end() {
        final var sut = File.A;

        final var result = sut.isLeftEnd();

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("H는 파일의 오른쪽 끝이다.")
    void h_is_right_end() {
        final var sut = File.H;

        final var result = sut.isRightEnd();

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("D에서 왼쪽 방향으로 세 번 이동하면 A이다.")
    void d_move_left_three_is_a() {
        final var sut = File.D;

        final var result = sut.moveLeft(3);

        assertThat(result).isEqualTo(File.A);
    }

    @Test
    @DisplayName("왼쪽으로 이동할 수 있는 범위를 벗어나면 예외를 발생한다.")
    void throw_exception_when_left_move_is_out_of_range() {
        final var sut = File.A;

        assertThatThrownBy(() -> sut.moveLeft(1))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("A에서 오른쪽 방향으로 두 번 이동하면 C이다.")
    void a_move_right_two_is_c() {
        final var sut = File.A;

        final var result = sut.moveRight(2);

        assertThat(result).isEqualTo(File.C);
    }

    @Test
    @DisplayName("오른쪽으로 이동할 수 있는 범위를 벗어나면 예외를 발생한다.")
    void throw_exception_when_right_move_is_out_of_range() {
        final var sut = File.H;

        assertThatThrownBy(() -> sut.moveRight(4))
                .isInstanceOf(IllegalStateException.class);
    }

}
