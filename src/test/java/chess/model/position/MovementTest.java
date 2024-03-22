package chess.model.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MovementTest {

    @ParameterizedTest
    @CsvSource(value = {"8,8", "1,8", "8,-2"})
    @DisplayName("File 혹은 Rank의 좌표차 절댓값이 7 초과이면 예외가 발생한다.")
    void validateDifference(int fileDifference, int rankDifference) {
        // whe & then
        assertThatThrownBy(
                () -> new Movement(new Difference(fileDifference), new Difference(rankDifference))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,1,true", "1,-1,true", "0,1,false"})
    @DisplayName("대각선에 있는지 판단한다.")
    void isDiagonal(int fileDifference, int rankDifference, boolean expected) {
        // when
        Movement movement = new Movement(new Difference(fileDifference), new Difference(rankDifference));
        boolean result = movement.isDiagonal();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("제자리에 있으면 대각선 움직임이 아니다.")
    void isDiagonalWhenStay() {
        // given
        int fileDifference = 0;
        int rankDifference = 0;
        Movement movement = new Movement(new Difference(fileDifference), new Difference(rankDifference));

        // when
        boolean result = movement.isDiagonal();

        // then
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"0,1,true", "0,4,true", "1,0,true", "-5,0,true", "2,4,false"})
    @DisplayName("십자가 움직임인지 판단한다.")
    void isCross(int fileDifference, int rankDifference, boolean expected) {
        // when
        Movement movement = new Movement(new Difference(fileDifference), new Difference(rankDifference));
        boolean result = movement.isOrthogonal();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("제자리에 있으면 대각선 움직임이 아니다.")
    void isCrossWhenStay() {
        // given
        int fileDifference = 0;
        int rankDifference = 0;

        // when
        Movement movement = new Movement(new Difference(fileDifference), new Difference(rankDifference));
        boolean result = movement.isOrthogonal();

        // then
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"3,3,0,true", "2,2,-2,true", "1,0,5,false", "4,1,-1,false", "3,3,5,false"})
    @DisplayName("같은 변위인지 판단한다.")
    void hasSameLengthOf(int displacement, int fileDifference, int rankDifference, boolean expected) {
        // when
        Movement movement = new Movement(new Difference(fileDifference), new Difference(rankDifference));
        boolean result = movement.hasLengthOf(displacement);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
