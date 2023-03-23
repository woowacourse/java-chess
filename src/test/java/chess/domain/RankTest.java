package chess.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class RankTest {

    @Test
    void getOrderedRanks() {
        //given
        List<Rank> expected = List.of(Rank.EIGHT, Rank.SEVEN, Rank.SIX,
                Rank.FIVE, Rank.FOUR, Rank.THREE,
                Rank.TWO, Rank.ONE);

        //when
        List<Rank> actual = Rank.getReversedOrderedRanks();

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findRankByIndex() {
        //given
        Rank expected = Rank.FIVE;

        //when
        Rank actual = Rank.findRankByIndex(5);

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findRankByValue() {
        //given
        Rank expected = Rank.FIVE;

        //when
        Rank actual = Rank.findRankByValue("5");

        //then
        Assertions.assertEquals(expected, actual);
    }
}
