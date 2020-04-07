package chess.domain.coordinate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DirectionTest {

    @DisplayName("-1~+1 을 벗어나는 경우와 0,0을 입력한 경우 Exception 발생")
    @ParameterizedTest
    @CsvSource(value = {"2,0", "0,0"})
    void findByValue(int fileVariation, int rankVariation) {

        assertThatThrownBy(() -> Direction.findByValue(fileVariation, rankVariation))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("fileVariation : %d, rankVariation : %d, 입력값을 확인하시오.", fileVariation, rankVariation);
    }


    @DisplayName("0,0을 제외한 -1~+1 범위를 입력한 경우 정상으로 찾는다.")
    @ParameterizedTest
    @CsvSource(value = {
            "-1,1,LEFT_UP",
            "0,1,UP",
            "1,1,RIGHT_UP",
            "1,0,RIGHT",
            "1,-1,RIGHT_DOWN",
            "0,-1,DOWN",
            "-1,-1,LEFT_DOWN",
            "-1,0,LEFT",

    })
    void findByValue1(int fileVariation, int rankVariation, Direction expect) {
        Direction actual = Direction.findByValue(fileVariation, rankVariation);

        assertThat(actual).isEqualTo(expect);
    }
}