package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class FileTest {

    @DisplayName("문자를 입력받아 File을 생성한다.")
    @ParameterizedTest
    @CsvSource(value = {"'a', A", "'b', B", "'c', C", "'d', D", "'e', E", "'f', F", "'g', G", "'h', H"})
    void from(final String input, final File expected) {
        // given && when
        final File file = File.fromSymbol(input);

        // then
        assertThat(file).isEqualTo(expected);
    }

    @DisplayName("유효하지 않는 문자를 입력하면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"i", "j", "k", "z"})
    void invalidFile(final String input) {
        Assertions.assertThatThrownBy(() -> File.fromSymbol(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효한 파일 입력이 아닙니다.");
    }

    @DisplayName("현재보다 1칸 오른쪽인 경우의 파일을 반환한다..")
    @Test
    void right() {
        // given && when
        File file = File.C.right();

        // then
        assertThat(file).isEqualTo(File.D);
    }

    @DisplayName("현재보다 1칸 왼쪽인 경우의 파일을 반환한다..")
    @Test
    void left() {
        // given && when
        File file = File.C.left();

        // then
        assertThat(file).isEqualTo(File.B);
    }

    @DisplayName("다른 파일과의 절대값 거리 차이를 반환한다.")
    @Test
    void getDistance() {
        // given
        File file = File.C;

        // when
        int distance = file.getDistance(File.A);

        // then
        assertThat(distance).isEqualTo(2);
    }

    @DisplayName("더 큰 파일인지 확인한다.")
    @Test
    void isBigger() {
        // given
        File file = File.C;

        // when
        boolean isBigger = file.isBigger(File.A);

        // then
        assertThat(isBigger).isTrue();
    }

    @DisplayName("더 큰 파일이 아닌지 확인한다.")
    @Test
    void isNotBigger() {
        // given
        File file = File.C;

        // when
        boolean isBigger = file.isBigger(File.D);

        // then
        assertThat(isBigger).isFalse();
    }
}
