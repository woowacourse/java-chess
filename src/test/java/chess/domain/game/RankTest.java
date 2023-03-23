package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class RankTest {

    private static Stream<Arguments> generatePath() {
        return Stream.of(
                Arguments.of(Rank.ONE, Rank.FOUR, List.of(Rank.TWO, Rank.THREE)),
                Arguments.of(Rank.SIX, Rank.EIGHT, List.of(Rank.SEVEN)),
                Arguments.of(Rank.EIGHT, Rank.TWO, List.of(Rank.SEVEN, Rank.SIX, Rank.FIVE, Rank.FOUR, Rank.THREE))
        );
    }

    @ParameterizedTest
    @CsvSource(value = {"1, ONE", "2, TWO", "3, THREE", "4, FOUR", "5, FIVE", "6, SIX", "7, SEVEN", "8, EIGHT"})
    void from_으로_문자로부터_Rank_를_생성할_수_있다(char input, Rank expected) {
        //given
        //when
        Rank result = Rank.from(input);

        //then
        assertEquals(result, expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"1, ONE", "2, TWO", "3, THREE", "4, FOUR", "5, FIVE", "6, SIX", "7, SEVEN", "8, EIGHT"})
    void from으로_order로부터_Rank를_생성할_수_있다(int input, Rank expected) {
        //given
        //when
        Rank result = Rank.from(input);

        //then
        assertEquals(result, expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"ONE, TWO, 1", "SIX, SEVEN, 1", "SEVEN, EIGHT, 1", "EIGHT, ONE, -7"})
    void getDifference(Rank origin, Rank destination, int expected) {
        //given
        //when
        int result = origin.getDifference(destination);

        //then
        assertEquals(result, expected);
    }

    @ParameterizedTest
    @MethodSource("generatePath")
    void createPath_로_중간_경로를_구할_수_있다(Rank origin, Rank destination, List<Rank> expected) {
        //given
        //when
        List<Rank> result = origin.createPath(destination);

        //then
        assertThat(result)
                .containsExactlyElementsOf(expected);
    }
}
