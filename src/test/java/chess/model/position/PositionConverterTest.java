package chess.model.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class PositionConverterTest {

    @ParameterizedTest(name = "convert()는 {0}을 전달하면 그에 맞는 Position으로 반환한다.")
    @DisplayName("convert() 테스트")
    @MethodSource("chess.helper.arguments.PositionConverterArguments#provideValidConvertArguments")
    void convert_givenInputPosition_thenReturnPosition(
            final String inputPosition,
            final Position expected
    ) {
        // when
        final Position actual = PositionConverter.convert(inputPosition);

        // then
        assertThat(actual).isSameAs(expected);
    }

    @ParameterizedTest(name = "올바르지 못한 Rank {0}을 전달하면 예외가 발생한다.")
    @DisplayName("convert() Rank 실패 테스트")
    @CsvSource(value = {"A0:존재하지 않는 행입니다.", "AZ:올바른 행을 입력해주세요."}, delimiter = ':')
    void convert_givenInvalidRank_thenFail(final String inputPosition, final String expectedExceptionMessage) {
        // when, then
        assertThatThrownBy(() -> PositionConverter.convert(inputPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedExceptionMessage);
    }

    @Test
    @DisplayName("convert()는 올바르지 못한 File을 전달하면 예외가 발생한다.")
    void convert_givenInvalidFile_thenFail() {
        // when, then
        assertThatThrownBy(() -> PositionConverter.convert("Z1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 열입니다.");
    }
}
