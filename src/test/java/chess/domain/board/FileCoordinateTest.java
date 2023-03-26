package chess.domain.board;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static chess.domain.board.FileCoordinate.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class FileCoordinateTest {

    @Test
    void 전달한_열_번호에_맞는_fileCoordinate_객체를_알_수_있다() {
        FileCoordinate fileCoordinate = findBy(1);

        assertThat(fileCoordinate).isEqualTo(A);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 9, 100})
    void 전달한_열_번호가_1에서_8_범위의_정수가_아니라면_예외를_발생시킨다(int columnNumber) {
        assertThatThrownBy(() -> findBy(columnNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 열 번호를 입력해주세요.");
    }

    @Test
    void FileCoordinate의_columnNumber_기준으로_정렬된_객체를_알_수_있다() {
        assertThat(getSortedFileCoordinates()).containsExactly(A, B, C, D, E, F, G, H);
    }

    @ParameterizedTest
    @CsvSource(value = {"A:B:1", "A:C:1", "B:A:-1", "A:A:0"}, delimiter = ':')
    void FileCoordinate_객체간의_크기를_비교할_수_있다(FileCoordinate fileCoordinate1, FileCoordinate fileCoordinate2, int expect) {
        assertThat(fileCoordinate1.compare(fileCoordinate2)).isEqualTo(expect);
    }
}
