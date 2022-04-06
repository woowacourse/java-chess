package chess.domain.board.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class RankTest {

    @Nested
    class OfTest {

        @Test
        void key에_대응되는_Rank_인스턴스_반환() {
            Rank actual = Rank.of("3");

            assertThat(actual).isEqualTo(Rank.THREE);
        }

        @Test
        void key에_대응되는_Rank_인스턴스가_없으면_예외() {
            assertThatThrownBy(() -> Rank.of("10"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("존재하지 않는 열입니다. (범위: 1~8)");
        }
    }

    @Test
    void allRanksDescending() {
        List<Rank> actual = Rank.allRanksDescending();

        List<Rank> expected = List.of(
                Rank.EIGHT, Rank.SEVEN, Rank.SIX, Rank.FIVE,
                Rank.FOUR, Rank.THREE, Rank.TWO, Rank.ONE);

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Nested
    class ValueDifferenceTest {

        @Test
        void 매개변수로_들어온_랭크까지의_상대적인_값_차이를_반환() {
            Rank from = Rank.ONE;
            Rank to = Rank.FOUR;

            int actual = from.valueDifference(to);

            assertThat(actual).isEqualTo(3);
        }

        @Test
        void 더_작은_크기의_랭크에_대해서는_음수_반환() {
            Rank from = Rank.FOUR;
            Rank to = Rank.ONE;

            int actual = from.valueDifference(to);

            assertThat(actual).isEqualTo(-3);
        }

        @Test
        void 같은_랭크에_대해서는_영_반환() {
            Rank from = Rank.FOUR;
            Rank to = Rank.FOUR;

            int actual = from.valueDifference(to);

            assertThat(actual).isZero();
        }
    }

    @Nested
    class OneRankTowardTest {

        @Test
        void 매개변수로_들어온_랭크를_향해_한단위_큰_랭크_반환() {
            Rank from = Rank.ONE;
            Rank to = Rank.FOUR;

            Rank actual = from.oneRankToward(to);
            Rank expected = Rank.TWO;

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 더_작은_크기의_랭크에_대해서는_한단위_작은_랭크_반환() {
            Rank from = Rank.FOUR;
            Rank to = Rank.ONE;

            Rank actual = from.oneRankToward(to);
            Rank expected = Rank.THREE;

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 같은_랭크에_대해서는_현재_랭크_그대로_반환() {
            Rank from = Rank.FOUR;
            Rank to = Rank.FOUR;

            Rank actual = from.oneRankToward(to);
            Rank expected = Rank.FOUR;

            assertThat(actual).isEqualTo(expected);
        }
    }
}