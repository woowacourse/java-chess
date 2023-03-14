package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
final class PositionTest {

    @ParameterizedTest
    @CsvSource(value = {
            "-1,0",
            "8,0"
    })
    @DisplayName("rank가 0~7 아닌 경우 예외가 발생한다.")
    void validateRangeOfRank(final int rank, final int file) {
        assertThatThrownBy(() -> new Position(rank, file))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("기물 위치는 최소 0 최대 7");
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0,-1",
            "0,8"
    })
    @DisplayName("file이 0~7 아닌 경우 예외가 발생한다.")
    void validateRangeOfFile(final int rank, final int file) {
        assertThatThrownBy(() -> new Position(rank, file))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("기물 위치는 최소 0 최대 7");
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0,0",
            "1,5",
            "7,3",
            "2,1",
            "3,6"
    })
    @DisplayName("포지션이 rank, file이 0~7 인경우 생성된다.")
    void checkSuccessCreatePosition(final int rank, final int file) {
        assertAll(
                () -> assertThat(new Position(rank, file))
                        .extracting("rank")
                        .isEqualTo(rank),
                () -> assertThat(new Position(rank, file))
                        .extracting("file")
                        .isEqualTo(file)
        );
    }

}
