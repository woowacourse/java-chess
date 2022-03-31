package chess.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ResultTest {

    @ParameterizedTest(name = "현재 플레이어 점수 : {0}, 상대 플레이어 점수 : {1}, 결과 : {2}")
    @MethodSource("providePlayerScores")
    @DisplayName("두 플레이어의 킹이 살아있을 때, 두 플레이어의 점수로 결과를 구한다.")
    void findResultHasKing(final double currentPlayerScore, final double opponentPlayerScore, final Result expected) {
        final boolean hasKingCurrentPlayer = true;
        final boolean hasKingOpponentPlayer = true;

        final Result actual = Result.from(currentPlayerScore, opponentPlayerScore,
                hasKingCurrentPlayer, hasKingOpponentPlayer);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("providePlayerScores")
    @DisplayName("킹이 죽으면, 점수와 상관없이 패배한다.")
    void findResultDieKing(final double currentPlayerScore, final double opponentPlayerScore) {
        final boolean hasKingCurrentPlayer = false;
        final boolean hasKingOpponentPlayer = true;
        final Result expected = Result.LOSE;

        final Result actual = Result.from(currentPlayerScore, opponentPlayerScore,
                hasKingCurrentPlayer, hasKingOpponentPlayer);

        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> providePlayerScores() {
        return Stream.of(
                Arguments.of(38, 37, Result.WIN),
                Arguments.of(37, 38, Result.LOSE),
                Arguments.of(38, 38, Result.DRAW)
        );
    }

    @Test
    @DisplayName("두 왕이 모두 죽은 경우, 예외를 발생한다.")
    void hasNoKingException() {
        final boolean hasKingCurrentPlayer = false;
        final boolean hasKingOpponentPlayer = false;

        assertThatThrownBy(() -> Result.from(38, 38,
                hasKingCurrentPlayer, hasKingOpponentPlayer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 결과입니다.");
    }
}
