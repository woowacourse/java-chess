package chess.dto;

public class CampScore {

    private final String campName;
    private final double score;

    public CampScore(final String campName, final double score) {
        this.campName = campName;
        this.score = score;
    }

    public String getCampName() {
        return campName;
    }

    public double getScore() {
        return score;
    }
}
