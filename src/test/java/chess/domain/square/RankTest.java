package chess.domain.square;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.square.Rank;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RankTest {

    @Test
    void 랭크는_1부터_8까지_존재한다() {
        final List<Rank> ranks = Arrays.stream(Rank.values())
                .collect(Collectors.toList());

        assertThat(ranks).containsExactly(
                Rank.ONE, Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX, Rank.SEVEN, Rank.EIGHT
        );
    }

    @ParameterizedTest
    @CsvSource(
            value = {"ONE:1", "TWO:2", "THREE:3", "FOUR:4", "FIVE:5", "SIX:6", "SEVEN:7", "EIGHT:8"},
            delimiter = ':'
    )
    void 랭크는_1부터_8까지_인덱스를_갖는다(final Rank rank, final int rankIndex) {
        assertThat(Rank.from(rankIndex)).isEqualTo(rank);
    }

    @Test
    void 전달받은_인덱스가_존재하지_않으면_예외를_던진다() {
        final int input = 9;

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Rank.from(input))
                .withMessage("존재하지 않는 랭크 인덱스입니다.");
    }
}
