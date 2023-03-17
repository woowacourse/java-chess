package chess.domain.chessboard;

public enum FileIndex {
    A('a'),
    H('h');

    final char index;

    FileIndex(final char index) {
        this.index = index;
    }
}
