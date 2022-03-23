package chess.domain.piece.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.piece.position.PositionUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PositionUtilTest {

    @DisplayName("fileToIdx 메서드는 a~h의 값을 받아 0~7로 변환하여 반환한다.")
    @ParameterizedTest(name = "rank {0}: index {1}")
    @CsvSource(value = {"a,0", "b,1", "c,2", "d,3", "e,4", "f,5", "g,6", "h,7"})
    void fileToIdx_ok(char rank, int expected) {
        int actual = PositionUtil.fileToIdx(rank);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("fileToIdx 메서드는 a~h 이외의 값을 입력하는 경우 예외를 발생시킨다.")
    @Test
    void fileToIdx_exceptionOnInvalidRange() {
        assertThatCode(() -> PositionUtil.fileToIdx('z'))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 포지션 입니다. (a1~h8)");
    }

    @DisplayName("rankToIdx 메서드는 1~8의 값을 받아 0~7로 변환하여 반환한다.")
    @ParameterizedTest(name = "file {0}: index {1}")
    @CsvSource(value = {"1,0", "2,1", "3,2", "4,3", "5,4", "6,5", "7,6", "8,7"})
    void rankToIdx_ok(char file, int expected) {
        int actual = PositionUtil.rankToIdx(file);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("rankToIdx 메서드는 1~8 이외의 값을 입력하는 경우 예외를 발생시킨다.")
    @Test
    void rankToIdx_exceptionOnInvalidRange() {
        assertThatCode(() -> PositionUtil.rankToIdx('0'))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 포지션 입니다. (a1~h8)");
    }
}
