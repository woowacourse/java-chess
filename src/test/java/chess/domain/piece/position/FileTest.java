package chess.domain.piece.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("File 은")
class FileTest {

    @Test
    void 생성_시_a_부터_h_사이에_포함되지_않는_문자가_들어오면_예외() {
        // when & then
        assertAll(
                () -> assertThatThrownBy(() -> File.from('i'))
                        .isInstanceOf(IllegalArgumentException.class),
                () -> assertThatThrownBy(() -> File.from(' '))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @ParameterizedTest
    @ValueSource(chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'})
    void 값이_같으면_동등하다(final char value) {
        // given
        final File file1 = File.from(value);
        final File file2 = File.from(value);

        // when & then
        assertThat(file1).isEqualTo(file2);
    }

    @Test
    void File_사이의_간격을_구할_수_있다() {
        // given
        final File from = File.from('b');
        final File dest = File.from('a');

        // when & then
        assertThat(from.interval(dest)).isEqualTo(-1);
    }

    @Test
    void 특정_거리를_더할_수_있다() {
        // given
        final File from = File.from('c');

        // when & then
        assertThat(from.plus(-2)).isEqualTo(File.from('a'));
    }
}
