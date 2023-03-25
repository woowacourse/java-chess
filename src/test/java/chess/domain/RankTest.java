package chess.domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RankTest {

    @Test
    void getOrderedRanks_메서드_테스트() {
        //given
        List<Rank> expected = List.of(Rank.EIGHT, Rank.SEVEN, Rank.SIX,
                Rank.FIVE, Rank.FOUR, Rank.THREE,
                Rank.TWO, Rank.ONE);

        //when
        List<Rank> actual = Rank.getReversedOrderedRanks();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void findRankByIndex_메서드_테스트() {
        //given
        Rank expected = Rank.FIVE;

        //when
        Rank actual = Rank.findRankByIndex(5);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void findRankByValue_메서드_테스트() {
        //given
        Rank expected = Rank.FIVE;

        //when
        Rank actual = Rank.findRankByValue("5");

        //then
        assertEquals(expected, actual);
    }
}
