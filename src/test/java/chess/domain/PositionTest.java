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
            "i,0",
            "j,0"
    })
    @DisplayName("rank가 a~h가 아닌 경우 예외가 발생한다.")
    void validateRangeOfRank(final char rank, final int file) {
        assertThatThrownBy(() -> new Position(rank, file))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("기물의 가로 위치는 a부터 h까지 놓을 수 있습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {
            "a,-1",
            "h,8"
    })
    @DisplayName("file이 0~7 아닌 경우 예외가 발생한다.")
    void validateRangeOfFile(final char rank, final int file) {
        assertThatThrownBy(() -> new Position(rank, file))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("기물의 세로 위치는 최소 0부터 최대 7까지 놓을 수 있습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {
            "a,0",
            "b,5",
            "c,3",
            "d,1",
            "e,6"
    })
    @DisplayName("포지션이 rank, file이 a~h이고, 0~7인 경우 생성된다.")
    void checkSuccessCreatePosition(final char rank, final int file) {
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
