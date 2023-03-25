package chess.domain.chessboard;

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
}
