package chess;

public enum File {

    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g'),
    H('h');

    private final char file;

    File(final char file) {
        this.file = file;
    }

    public char value() {
        return file;
    }
}
