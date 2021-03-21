package chess.domain.board;

import chess.domain.piece.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class RankTest {

    private static Stream<Arguments> getRankInputAndEnum() {
        return Stream.of(Arguments.of("1", Rank.ONE),
                Arguments.of("3", Rank.THREE),
                Arguments.of("8", Rank.EIGHT));
    }

    private static Stream<Arguments> getDirectionAndRank() {
        return Stream.of(Arguments.of(Direction.LEFT_DOWN_DOWN, Rank.ONE),
                Arguments.of(Direction.LEFT, Rank.THREE),
                Arguments.of(Direction.LEFT_UP_UP, Rank.FIVE),
                Arguments.of(Direction.LEFT_UP, Rank.FOUR),
                Arguments.of(Direction.RIGHT_RIGHT_DOWN, Rank.TWO));
    }

    @DisplayName("문자열과 매치되는 Rank(행) 열거형을 탐색한다.")
    @ParameterizedTest
    @MethodSource("getRankInputAndEnum")
    void findRank(String rankInput, Rank expectedRank) {
        Rank rank = Rank.findRankByValue(rankInput);

        assertThat(rank).isEqualTo(expectedRank);
    }

    @DisplayName("문자열과 매치되는 Rank가 없는 경우 예외를 발생한다.")
    @Test
    void cannotFindRank() {
        assertThatCode(() -> Rank.findRankByValue("9"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 Rank(열)값 입니다.");
    }

    @DisplayName("두 Rank의 y값의 차이를 계산한다.")
    @Test
    void calculateDifference() {
        Rank firstRank = Rank.FIVE;
        Rank secondRank = Rank.THREE;

        int difference = firstRank.calculateDifference(secondRank);

        assertThat(difference).isEqualTo(2);
    }

    @DisplayName("Direction에 정의된 y값만큼 이동한다.")
    @ParameterizedTest
    @MethodSource("getDirectionAndRank")
    void move(Direction direction, Rank expectedRank) {
        Rank rank = Rank.THREE;

        Rank movedRank = rank.move(direction);

        assertThat(movedRank).isEqualTo(expectedRank);
    }

    @DisplayName("Direction만큼 Rank를 이동했을 때 y값이 1~8 범위가 아닌 경우 예외가 발생한다.")
    @Test
    void cannotMove() {
        assertThatCode(() -> Rank.TWO.move(Direction.LEFT_DOWN_DOWN))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 Rank(열)값 입니다.");
    }
}
