package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class FileTest {

    @ParameterizedTest(name = "A 부터 H 사이 값이 아니라면 예외를 던진다. 입력: {0}")
    @ValueSource(strings = {"", "I", "허브"})
    void A_부터_H_사이_값이_아니라면_예외를_던진다(final String command) {
        // expect
        assertThatThrownBy(() -> File.from(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("파일은 A ~ H 사이의 값이어야 합니다.");
    }

    @ParameterizedTest(name = "A 부터 H 사이 값을 입력받으면 File을 반환한다 입력: {0}, 결과: {1}")
    @CsvSource({"A, A", "H, H"})
    void A_부터_H_사이_값을_입력받으면_File을_반환한다(final String command, final File file) {
        // expect
        assertThat(File.from(command)).isEqualTo(file);
    }

    @ParameterizedTest(name = "입력받은 파일과의 차이를 반환한다. 시작: {0}, 도착: {1}, 결과: {2}")
    @CsvSource({"A, D, -3", "B, B, 0", "H, G, 1"})
    void 입력받은_파일과의_차이를_반환한다(final File source, final File target, final int result) {
        // expect
        assertThat(source.calculateGap(target)).isEqualTo(result);
    }
}
