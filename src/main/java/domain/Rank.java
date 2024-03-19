package domain;

public class Rank {
    private final String points;

    public Rank(final RankGenerator rankGenerator, final int index) {
        this.points = rankGenerator.generate(index);
    }

    public int size() {
        return points.length();
    }
}
