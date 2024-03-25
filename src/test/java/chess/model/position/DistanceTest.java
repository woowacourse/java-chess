package chess.model.position;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.piece.Side;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DistanceTest {
    @ParameterizedTest
    @CsvSource(value = {"3,3,0,true", "2,2,-2,true", "1,0,5,false", "4,1,-1,false", "3,3,5,false"})
    @DisplayName("같은 변위인지 판단한다.")
    void hasSame(int displacement, int fileDiff, int rankDiff, boolean expected) {
        // when
        Distance distance = new Distance(fileDiff, rankDiff);
        boolean result = distance.hasSame(displacement);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "-2,WHITE", "-1,WHITE", "1,WHITE", "2,WHITE",
            "-2,BLACK", "-1,BLACK", "1,BLACK", "2,BLACK",
            "-2,EMPTY", "-1,EMPTY", "1,EMPTY", "2,EMPTY",
    })
    @DisplayName("File 차이가 0이 아니면 앞에 존재하지 않는다.")
    void isForwardFileNonZero(int givenFileDiff, Side givenSide) {
        // when
        Distance distance = new Distance(givenFileDiff, 3);
        boolean result = distance.isForward(givenSide);

        // then
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"1,true", "2,true", "-1,false", "-2,false"})
    @DisplayName("흰 진영일 때 앞에 있는지 판단한다.")
    void isForwardWhite(int givenRankDiff, boolean expected) {
        // when
        Distance distance = new Distance(0, givenRankDiff);
        boolean result = distance.isForward(Side.WHITE);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"-1,true", "-2,true", "1,false", "2,false"})
    @DisplayName("검은 진영일 때 앞에 있는지 판단한다.")
    void isForwardBlack(int givenRankDiff, boolean expected) {
        // when
        Distance distance = new Distance(0, givenRankDiff);
        boolean result = distance.isForward(Side.BLACK);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"1, -1, 0, 3"})
    @DisplayName("빈 진영일 때 항상 앞에 있지 않다.")
    void isForwardEmpty(int givenFile, int givenRank, boolean expected) {
        // when
        Distance distance = new Distance(givenFile, givenRank);
        boolean result = distance.isForward(Side.WHITE);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"BLACK", "WHITE", "EMPTY"})
    @DisplayName("제자리에 있으면 앞에 있지 않다.")
    void isForwardWhenStay(Side given) {
        // given
        int givenFile = 0;
        int givenRank = 0;

        // when
        Distance distance = new Distance(givenFile, givenRank);
        boolean result = distance.isForward(given);

        // then
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"1,1,true", "1,-1,true", "0,1,false"})
    @DisplayName("대각선에 있는지 판단한다.")
    void isDiagonalMovement(int givenFile, int givenRank, boolean expected) {
        // when
        Distance distance = new Distance(givenFile, givenRank);
        boolean result = distance.isDiagonalMovement();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("제자리에 있으면 대각선 움직임이 아니다.")
    void isDiagonalMovementWhenStay() {
        // given
        int givenFile = 0;
        int givenRank = 0;

        // when
        Distance distance = new Distance(givenFile, givenRank);
        boolean result = distance.isDiagonalMovement();

        // then
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"0,1,true", "0,4,true", "1,0,true", "-5,0,true", "2,4,false"})
    @DisplayName("십자가 움직임인지 판단한다.")
    void isCrossMovement(int givenFile, int givenRank, boolean expected) {
        // when
        Distance distance = new Distance(givenFile, givenRank);
        boolean result = distance.isCrossMovement();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("제자리에 있으면 대각선 움직임이 아니다.")
    void isCrossMovementWhenStay() {
        // given
        int givenFile = 0;
        int givenRank = 0;

        // when
        Distance distance = new Distance(givenFile, givenRank);
        boolean result = distance.isCrossMovement();

        // then
        assertThat(result).isFalse();
    }
}
