package chess.domain.square;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.square.File;
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
    void 파일은_a부터_h까지_인덱스를_갖는다(final File file, final String fileIndex) {
        assertThat(File.from(fileIndex)).isEqualTo(file);
    }

    @Test
    void 전달받은_인덱스가_존재하지_않으면_예외를_던진다() {
        final String input = "i";

        assertThatIllegalArgumentException()
                .isThrownBy(() -> File.from(input))
                .withMessage("존재하지 않는 파일 인덱스입니다.");
    }
}
