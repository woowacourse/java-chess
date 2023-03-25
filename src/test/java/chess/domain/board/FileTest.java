package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class FileTest {

    @Test
    void 파일은_A부터_H까지_존재한다() {
        final List<File> files = Arrays.stream(File.values())
                .collect(Collectors.toList());

        assertThat(files).containsExactly(File.A, File.B, File.C, File.D, File.E, File.F, File.G, File.H);
    }

    @ParameterizedTest
    @CsvSource(value = {"A:a", "B:b", "C:c", "D:d", "E:e", "F:f", "G:g", "H:h"}, delimiter = ':')
    void 파일은_a부터_h까지_인덱스를_갖는다(final File file, final Character fileIndex) {
        assertThat(File.from(fileIndex)).isEqualTo(file);
    }

    @Test
    void 전달받은_인덱스가_존재하지_않으면_예외를_던진다() {
        final Character input = 'i';

        assertThatIllegalArgumentException()
                .isThrownBy(() -> File.from(input))
                .withMessage("존재하지 않는 파일 인덱스입니다.");
    }

    @Test
    void 파일인덱스_차이를_계산한다() {
        final File a = File.A;
        final File b = File.B;

        assertThat(b.calculateDistance(a)).isEqualTo(1);
    }

    @Test
    void 다음_파일을_확인한다() {
        final File d = File.D;

        assertThat(d.next()).isEqualTo(File.E);
    }

    @Test
    void 다음_파일_인덱스_범위를_벗어나면_예외를_던진다() {
        final File h = File.H;

        assertThatIllegalArgumentException()
                .isThrownBy(() -> h.next())
                .withMessage("존재하지 않는 파일 인덱스입니다.");
    }

    @Test
    void 이전_파일을_확인한다() {
        final File d = File.D;

        assertThat(d.prev()).isEqualTo(File.C);
    }

    @Test
    void 이전_파일_인덱스_범위를_벗어나면_예외를_던진다() {
        final File a = File.A;

        assertThatIllegalArgumentException()
                .isThrownBy(() -> a.prev())
                .withMessage("존재하지 않는 파일 인덱스입니다.");
    }
}
