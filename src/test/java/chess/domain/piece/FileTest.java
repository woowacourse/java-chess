package chess.domain.piece;

import chess.domain.piece.position.File;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("File 은")
class FileTest {

    @ParameterizedTest(name = "생성 시 a 부터 h 사이에 포함되지 않는 문자(ex: {0})가 들어오면 예외")
    @ValueSource(chars = {'i', 'j', 'z', ' '})
    void 생성_시_a_부터_h_사이에_포함되지_않는_문자가_들어오면_예외(final char file) {
        // when & then
        assertThatThrownBy(() -> new File(file))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "생성 시 a 부터 h 사이에 포함되는 문자(ex: {0})가 들어오면 정상 생성")
    @ValueSource(chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'})
    void a_부터_h_사이에_포함되는_문자인_경우_정상_생성(final char file) {
        // when & then
        assertDoesNotThrow(() -> new File(file));
    }

    @ParameterizedTest
    @ValueSource(chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'})
    void 값이_같으면_동등하다(final char value) {
        // given
        final File file1 = new File(value);
        final File file2 = new File(value);

        // when & then
        assertThat(file1).isEqualTo(file2);
    }

    @ParameterizedTest(name = "File 사이의 간격을 구할 수 있다. 현재[{0}]인 경우 목적지인 [{1}] 과의 차이는 [{2}] 이다.")
    @CsvSource({
            "b,a,-1",
            "c,a,-2",
            "a,c,2",
            "a,b,1",
            "a,a,0",
            "c,c,0",
    })
    void File_사이의_간격을_구할_수_있다(final char currentFile, final char destination, final int distance) {
        // given
        final File from = new File(currentFile);
        final File dest = new File(destination);

        // when & then
        assertThat(from.interval(dest)).isEqualTo(distance);
    }

    @Test
    void 특정_거리를_더할_수_있다() {
        // given
        final File from = new File('c');

        // when & then
        assertThat(from.plus(-2)).isEqualTo(new File('a'));
    }
}
