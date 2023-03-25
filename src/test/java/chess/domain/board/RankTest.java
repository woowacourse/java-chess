package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

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
    void 랭크는_1부터_8까지_인덱스를_갖는다(final Rank rank, final Character rankIndex) {
        assertThat(Rank.from(rankIndex)).isEqualTo(rank);
    }

    @Test
    void 전달받은_인덱스가_존재하지_않으면_예외를_던진다() {
        final Character input = '9';

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Rank.from(input))
                .withMessage("존재하지 않는 랭크 인덱스입니다.");
    }

    @Test
    void 랭크인덱스_차이를_계산한다() {
        final Rank one = Rank.ONE;
        final Rank two = Rank.TWO;

        assertThat(two.calculateDistance(one)).isEqualTo(1);
    }

    @Test
    void 다음_랭크를_확인한다() {
        final Rank four = Rank.FOUR;

        assertThat(four.next()).isEqualTo(Rank.FIVE);
    }

    @Test
    void 다음_랭크_인덱스_범위를_벗어나면_예외를_던진다() {
        final Rank eight = Rank.EIGHT;

        assertThatIllegalArgumentException()
                .isThrownBy(() -> eight.next())
                .withMessage("존재하지 않는 랭크 인덱스입니다.");
    }

    @Test
    void 이전_랭크를_확인한다() {
        final Rank four = Rank.FOUR;

        assertThat(four.prev()).isEqualTo(Rank.THREE);
    }

    @Test
    void 이전_랭크_인덱스_범위를_벗어나면_예외를_던진다() {
        final Rank one = Rank.ONE;

        assertThatIllegalArgumentException()
                .isThrownBy(() -> one.prev())
                .withMessage("존재하지 않는 랭크 인덱스입니다.");
    }
}
