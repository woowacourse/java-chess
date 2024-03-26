package chess.domain.chessGame;

import java.util.ArrayList;
import java.util.List;

public class BoardScore {

    private final List<FileScore> fileScores;

    private BoardScore(List<FileScore> fileScores) {
        this.fileScores = new ArrayList<>(fileScores);
    }

    public static BoardScore create(FileScore fileScore) {
        return new BoardScore(List.of(fileScore));
    }

    public static BoardScore zero() {
        return new BoardScore(List.of());
    }

    public BoardScore concat(BoardScore boardScore) {
        List<FileScore> concatFileScores = new ArrayList<>(this.fileScores);
        concatFileScores.addAll(boardScore.fileScores);

        return new BoardScore(concatFileScores);
    }

    public double asDouble() {
        return fileScores.stream()
                .mapToDouble(FileScore::asDouble)
                .sum();
    }
}
