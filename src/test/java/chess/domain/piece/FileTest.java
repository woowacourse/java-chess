package chess.domain.piece;

import chess.domain.position.File;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class FileTest {

    @DisplayName("char입력받아 File을 생성한다.")
    @ParameterizedTest
    @CsvSource(value = {"'a', A", "'b', B", "'c', C", "'d', D", "'e', E", "'f', F", "'g', G", "'h', H"})
    void from(final char input, final File expected) {
        // given && when
        final File file = File.fromSymbol(input);

        // then
        Assertions.assertThat(file).isEqualTo(expected);
    }

    @DisplayName("유효하지 않는 char을 입력하면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(chars = {'i', 'j', 'k', 'z'})
    void invalidFile(final char input) {
        Assertions.assertThatThrownBy(() -> File.fromSymbol(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효한 파일 입력이 아닙니다.");
    }
}
