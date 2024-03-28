package chess.domain.chessGame;

import chess.domain.position.File;
import java.util.ArrayList;
import java.util.List;

public class FileScore {

    private final List<Score> scores;
    private final File file;

    private FileScore(File file, List<Score> scores) {
        this.file = file;
        this.scores = scores;
    }

    public static FileScore create(File file, Space space) {
        return new FileScore(file, List.of(space.score()));
    }

    public static FileScore zero(File file) {
        return new FileScore(file, List.of());
    }

    public FileScore concat(FileScore fileScore) {
        if (file != fileScore.file) {
            throw new IllegalArgumentException("같은 file이 아닙니다");
        }
        List<Score> concatScores = new ArrayList<>(this.scores);
        concatScores.addAll(fileScore.scores);

        return new FileScore(file, concatScores);
    }

    public double asDouble() {
        if (isPawnDuplicated()) {
            return getSpecialScore() + getPawnScore() / 2;
        }
        return getSpecialScore() + getPawnScore();
    }

    private double getSpecialScore() {
        return scores.stream()
                .filter(score -> !score.isPawn())
                .mapToDouble(Score::asDouble)
                .sum();
    }

    private double getPawnScore() {
        return scores.stream()
                .filter(Score::isPawn)
                .mapToDouble(Score::asDouble)
                .sum();
    }

    private boolean isPawnDuplicated() {
        return scores.stream()
                .filter(Score::isPawn)
                .count() >= 2;
    }
}
