package chess.domain.square;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("파일")
class FileTest {

    @Test
    @DisplayName("알파벳 문자를 정상적으로 변환한다.")
    void fromTest() {
        // given
        File file = File.from('c');

        // when & then
        assertThat(file).isEqualTo(File.C);
    }

    @ParameterizedTest
    @ValueSource(chars = {'z', 'A'})
    @DisplayName("범위 밖의 값일 경우 예외가 발생한다.")
    void validateRangeTest(char input) {
        assertThatCode(() -> File.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("범위 밖의 파일 입니다.");
    }

    @Test
    @DisplayName("정수를 더할 수 있다.")
    void addTest() {
        // given
        File file = File.from('c');

        // when
        File added = file.add(2);

        // then
        assertThat(added).isEqualTo(File.E);
    }

    @Test
    @DisplayName("다른 파일까지의 벡터 값을 계산한다.")
    void getVectorToTest() {
        // given
        File sourceFile = File.from('c');
        File targetFile = File.from('f');

        // when
        int vectorTo = sourceFile.getVectorTo(targetFile);

        // when & then
        assertThat(vectorTo).isEqualTo(1);
    }

    @Test
    @DisplayName("목적지 파일부터의 거리를 계산한다.")
    void distanceFromTest() {
        // given
        File sourceFile = File.from('c');
        File targetFile = File.from('a');

        // when
        int vectorTo = sourceFile.distanceFrom(targetFile);

        // when & then
        assertThat(vectorTo).isEqualTo(2);
    }
}
