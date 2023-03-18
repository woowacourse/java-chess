package chess.domain.movepattern;

public interface MovePattern {

    int getFileVector();

    int getRankVector();

    int nextFileIndex(final int currentFileIndex);

    int nextRankIndex(final int currentRankIndex);
}
