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
            "0,i",
            "0,j"
    })
    @DisplayName("file이 a~h가 아닌 경우 예외가 발생한다.")
    void validateRangeOfRank(final int rank, final char file) {
        assertThatThrownBy(() -> Position.from(rank, file))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("기물의 세로 위치는 a부터 h까지 놓을 수 있습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {
            "-1,a",
            "8,h"
    })
    @DisplayName("rank가 0~7 아닌 경우 예외가 발생한다.")
    void validateRangeOfFile(final int rank, final char file) {
        assertThatThrownBy(() -> Position.from(rank, file))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("기물의 가로 위치는 최소 0부터 최대 7까지 놓을 수 있습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0,a",
            "5,b",
            "3,c",
            "1,d",
            "6,e"
    })
    @DisplayName("포지션이 rank, file이 a~h이고, 0~7인 경우 생성된다.")
    void checkSuccessCreatePosition(final int rank, final char file) {
        assertAll(
                () -> assertThat(Position.from(rank, file))
                        .extracting("rank")
                        .isEqualTo(Rank.from(rank)),
                () -> assertThat(Position.from(rank, file))
                        .extracting("file")
                        .isEqualTo(File.from(file))
        );
    }

}
