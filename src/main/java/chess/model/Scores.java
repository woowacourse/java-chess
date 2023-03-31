package chess.model;

import java.util.List;

public class Scores {

    private final List<Score> scoreBoard;

    public Scores(final List<Score> scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public List<Score> getScoreBoard() {
        return List.copyOf(scoreBoard);
    }
}
