package chess.helper.arguments;

import static chess.model.position.Rank.EIGHTH;
import static chess.model.position.Rank.FIFTH;
import static chess.model.position.Rank.FIRST;
import static chess.model.position.Rank.FOURTH;
import static chess.model.position.Rank.SECOND;
import static chess.model.position.Rank.SEVENTH;
import static chess.model.position.Rank.SIXTH;
import static chess.model.position.Rank.THIRD;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

final class RankArguments {

    private RankArguments() {
    }

    private static Stream<Arguments> provideValidFindRankArguments() {
        return Stream.of(
                Arguments.of(1, FIRST), Arguments.of(2, SECOND), Arguments.of(3, THIRD), Arguments.of(4, FOURTH),
                Arguments.of(5, FIFTH), Arguments.of(6, SIXTH), Arguments.of(7, SEVENTH), Arguments.of(8, EIGHTH)
        );
    }

    private static Stream<Arguments> provideValidFindNextRankArguments() {
        return Stream.of(
                Arguments.of(1, SIXTH),Arguments.of(0, FIFTH),Arguments.of(-1, FOURTH),
                Arguments.of(2, SEVENTH),Arguments.of(-2, THIRD)
        );
    }

    private static Stream<Arguments> provideInvalidFindNextRankArguments() {
        return Stream.of(
                Arguments.of(FIRST, -1), Arguments.of(EIGHTH, 1)
        );
    }
}
