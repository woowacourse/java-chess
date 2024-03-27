package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class FileTest {

    @ParameterizedTest
    @ValueSource(strings = {"i", "z", "1"})
    @NullAndEmptySource
    @DisplayName("올바르지 않은 열은 변환하는 경우 예외를 발생한다.")
    void invalidFileNameTest(String fileName) {
        assertThatThrownBy(() -> File.from(fileName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 열 번호입니다.");
    }

    @Test
    @DisplayName("파일의 차이를 올바르게 계산한다")
    void subtractTest() {
        // given
        File back = File.from("a");
        File source = File.from("c");
        File front = File.from("e");
        // when
        int subtractBack = source.subtract(back);
        int subtractFront = source.subtract(front);
        // then
        assertAll(
                () -> assertThat(subtractBack).isEqualTo(2),
                () -> assertThat(subtractFront).isEqualTo(-2)
        );
    }

    @Test
    @DisplayName("파일의 차이를 받아 해당 파일을 반환한다")
    void createFileByDifferenceOfTest() {
        // given
        File source = File.from("c");
        // when
        File back = source.createFileByDifferenceOf(2);
        File front = source.createFileByDifferenceOf(-2);
        // then
        assertAll(
                () -> assertThat(back).isEqualTo(File.from("e")),
                () -> assertThat(front).isEqualTo(File.from("a"))
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 9, 1000})
    @DisplayName("파일의 차이를 받아 반환한 파일이 범위에 벗어난다면 예외를 발생한다.")
    void createFileByDifferenceOfExceptionTest(int difference) {
        // given
        File source = File.from("a");
        // when, then
        assertThatThrownBy(() -> source.createFileByDifferenceOf(difference))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 열 번호입니다.");
    }
}
