package chess.controller.mapper;

import chess.domain.board.FileCoordinate;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class FileCoordinateMapperTest {


    @ParameterizedTest
    @CsvSource(value = {"a:A", "b:B", "h:H"}, delimiter = ':')
    void 열_메세지를_전달하면_이에_맞는_객체를_반환한다(String columnView, FileCoordinate expect) {
        FileCoordinate fileCoordinate = FileCoordinateMapper.findBy(columnView);

        assertThat(fileCoordinate).isEqualTo(expect);
    }

    @ParameterizedTest
    @ValueSource(strings = {"t", "2", " "})
    void 올바르지_않는_열_메세지를_전달하면_예외가_발생한다(String columnView) {
        assertThatThrownBy(() -> FileCoordinateMapper.findBy(columnView))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 열 번호를 입력해주세요.");
    }

    @ParameterizedTest
    @CsvSource(value = {"1:A", "2:B", "8:H"}, delimiter = ':')
    void 열_값을_전달하면_이에_맞는_객체를_반환한다(int columnView, FileCoordinate expect) {
        FileCoordinate fileCoordinate = FileCoordinateMapper.findBy(columnView);

        assertThat(fileCoordinate).isEqualTo(expect);
    }

    @Test
    void 올바르지_않는_열_값을_전달하면_예외가_발생한다() {
        assertThatThrownBy(() -> FileCoordinateMapper.findBy(10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 열 번호를 입력해주세요.");
    }
}
