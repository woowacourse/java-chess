package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileTest {

    @Test
    @DisplayName("from은 값에 맞는 File 객체를 반환해준다.")
    void test_from() {
        final int value = 5;
        final File file = File.from(value);
        assertSame(file, File.E);
    }

    @Test
    @DisplayName("유효하지 않는 수로 from을 호출시 예외처리 한다.")
    void test_fromThrowException() {
        final int unvalidatedValue = 9;

        assertThatThrownBy(() -> File.from(unvalidatedValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(File.FILE_NOT_FOUND_EXCEPTION);
    }

    @Test
    @DisplayName("File를 받아 두 수의 차를 반환한다.")
    void test_calculateFileGap() {
        int[] fileValues = {1, 5};
        final File value = File.from(fileValues[0]);
        final File subtrahend = File.from(fileValues[1]);

        final int result = value.calculateFileGap(subtrahend);

        assertThat(result)
                .isEqualTo(fileValues[0] - fileValues[1]);
    }

    @Test
    @DisplayName("값을 더한 Rank를 반환한다.")
    void test_addValue() {
        final int addition = 1;
        final File b = File.B;

        assertSame(b.addValue(addition), File.C);
    }
}
