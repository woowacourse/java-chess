package chess.view.messsage;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.piece.Camp;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CampMessageConverterTest {

    @ParameterizedTest(name = "Camp.{0}일 때 {1}을 반환한다.")
    @DisplayName("convert() 테스트")
    @MethodSource("provideCampMessageConverterArguments")
    void convert_givenCamp_thenReturnMessage(final Camp camp, final String expected) {
        // when
        final String actual = CampMessageConverter.convert(camp);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCampMessageConverterArguments() {
        return Stream.of(
                Arguments.of(Camp.BLACK, "검은색"), Arguments.of(Camp.BLACK, "검은색")
        );
    }
}
