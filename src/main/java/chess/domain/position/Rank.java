package chess.domain.position;

import java.util.List;

public enum Rank {

    EIGHT,
    SEVEN,
    SIX,
    FIVE,
    FOUR,
    THREE,
    TWO,
    ONE,
    ;

    public Rank moveVertical(int index) {
        List<Rank> ranks = List.of(Rank.values());
        int targetIndex = ranks.indexOf(this) + (index * -1);

        validateIndexBound(targetIndex, ranks);

        return ranks.get(targetIndex);
    }

    private void validateIndexBound(int targetIndex, List<Rank> ranks) {
        if (targetIndex < 0 || ranks.size() <= targetIndex) {
            throw new IndexOutOfBoundsException("더 이상 전진할 수 없습니다.");
        }
    }

    public int calculateDiff(Rank rank) {
        List<Rank> ranks = List.of(Rank.values());

        return Math.abs(ranks.indexOf(this) - ranks.indexOf(rank));
    }
}
