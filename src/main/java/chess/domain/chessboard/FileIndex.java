package chess.domain.chessboard;

public enum FileIndex {
    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g'),
    H('h');

    final char index;

    FileIndex(final char index) {
        this.index = index;
    }
}
