package chess.domain.movepattern;

public interface MovePattern {

    int nextFileIndex(final int currentFileIndex);

    int nextRankIndex(final int currentRankIndex);
}
