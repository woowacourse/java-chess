package chess.domain.position;

public class RankDifference extends Difference {

    public RankDifference(Rank from, Rank to) {
        super(from.calculateDifferenceTo(to));
    }
}
