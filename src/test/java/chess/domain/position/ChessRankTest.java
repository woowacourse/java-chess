package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ChessRankTest {

    @DisplayName("랭크와 랭크 사이의 랭크들을 조회한다.")
    @ParameterizedTest
    @MethodSource("findRankBetweenArguments")
    void findRankBetween(ChessRank start, ChessRank end, List<ChessRank> expected) {
        // when
        List<ChessRank> rankBetween = ChessRank.findBetween(start, end);

        // then
        assertThat(expected).containsAll(rankBetween);
    }

    private static Stream<Arguments> findRankBetweenArguments() {
        return Stream.of(
                Arguments.of(ChessRank.ONE, ChessRank.EIGHT,
                        List.of(ChessRank.TWO,
                                ChessRank.THREE,
                                ChessRank.FOUR,
                                ChessRank.FIVE,
                                ChessRank.SIX,
                                ChessRank.SEVEN)),
                Arguments.of(ChessRank.EIGHT, ChessRank.ONE,
                        List.of(ChessRank.TWO,
                                ChessRank.THREE,
                                ChessRank.FOUR,
                                ChessRank.FIVE,
                                ChessRank.SIX,
                                ChessRank.SEVEN))
        );
    }
}
