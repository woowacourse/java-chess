package chess.domain.board;

import java.util.List;

public class Board {

    private final List<Rank> ranks;

    Board(List<Rank> ranks) {
        this.ranks = ranks;
    }

    public List<Rank> getRanks() {
        return List.copyOf(ranks);
    }

    public Square findSquare(FileCoordinate fileCoordinate, RankCoordinate rankCoordinate) {
        Rank rank = findRankBy(rankCoordinate);
        return rank.findSquareBy(fileCoordinate);
    }

    private Rank findRankBy(RankCoordinate rankCoordinate) {
        return ranks.stream()
                .filter(rank -> rank.isSameWith(rankCoordinate))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 행 위치를 입력했습니다"));
    }
}
