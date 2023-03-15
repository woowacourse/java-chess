package chess.domain;

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

    @ParameterizedTest(name = "1 부터 8 사이 값이 아니라면 예외를 던진다. 입력: {0}")
    @ValueSource(strings = {"", "0", "9", "가비"})
    void _1_부터_8_사이_값이_아니라면_예외를_던진다(final String command) {
        // expect
        assertThatThrownBy(() -> File.from(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("파일은 1 ~ 8 사이의 값이어야 합니다.");
    }

    @ParameterizedTest(name = "1 부터 8 사이 값을 입력받으면 File을 반환한다 입력: {0}, 출력: {1}")
    @CsvSource({"1, ONE", "8, EIGHT"})
    void _1_부터_8_사이_값을_입력받으면_File을_반환한다(final String command, final File file) {
        // expect
        assertThat(File.from(command)).isEqualTo(file);
    }
}
