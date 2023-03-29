package chess.domain.board;

import org.junit.jupiter.api.Test;

import java.util.List;

class RankTest {
    @Test
    void reverseRank() {
        List<Rank> rankList = Rank.ONE.getRanksInRange(Rank.EIGHT);
        System.out.println(rankList);
    }
}
