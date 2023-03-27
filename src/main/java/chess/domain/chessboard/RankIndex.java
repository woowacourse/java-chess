package chess.domain.chessboard;

import java.util.Arrays;

public enum RankIndex {
    FIRST('1'),
    SECOND('2'),
    THIRD('3'),
    FOURTH('4'),
    FIFTH('5'),
    SIXTH('6'),
    SEVENTH('7'),
    EIGHTH('8');

    final char index;

    RankIndex(final char index) {
        this.index = index;
    }

    static RankIndex of(final char value) {
        return Arrays.stream(RankIndex.values())
                .filter(it -> it.index == value)
                .findAny()
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 Rank 가 없습니다.");
                });
    }
}
