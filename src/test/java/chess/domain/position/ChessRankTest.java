package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessRankTest {

    static Stream<Arguments> moveArguments() {
        return java.util.stream.Stream.of(
                Arguments.arguments(ChessRank.SIX, Direction.DOWN, ChessRank.FIVE),
                Arguments.arguments(ChessRank.SIX, Direction.UP, ChessRank.SEVEN),
                Arguments.arguments(ChessRank.SIX, Direction.LEFT, ChessRank.SIX),
                Arguments.arguments(ChessRank.SIX, Direction.RIGHT, ChessRank.SIX),
                Arguments.arguments(ChessRank.SIX, Direction.DOWN_LEFT, ChessRank.FIVE),
                Arguments.arguments(ChessRank.SIX, Direction.DOWN_RIGHT, ChessRank.FIVE),
                Arguments.arguments(ChessRank.SIX, Direction.UP_LEFT, ChessRank.SEVEN),
                Arguments.arguments(ChessRank.SIX, Direction.UP_RIGHT, ChessRank.SEVEN)
        );
    }

    @DisplayName("주어진 Rank의 값으로 Rank을 찾는다.")
    @Test
    void findByValue() {
        // given
        String rankValue = "1";

        // when
        ChessRank rank = ChessRank.findByValue(rankValue);

        // then
        assertThat(rank).isEqualTo(ChessRank.ONE);
    }

    @DisplayName("체스 랭크 범위에 해당하지 않는 값을 조회하면 예외를 발생시킨다.")
    @Test
    void unknownChessRankValue() {
        // given
        String rankValue = "0";

        // when & then
        assertThatThrownBy(() -> ChessRank.findByValue(rankValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("체스 랭크 범위에 해당하지 않는 값입니다.");
    }

    @DisplayName("체스 랭크를 주어진 방향 정보에 따라 갱신한다.")
    @ParameterizedTest
    @MethodSource("moveArguments")
    void move(ChessRank before, Direction direction, ChessRank after) {
        //when & then
        assertThat(before.move(direction)).isEqualTo(after);
    }
}
