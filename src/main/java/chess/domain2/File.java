package chess.domain2;

public enum File {
    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g'),
    H('h');

    private final char name;

    File(char name) {
        this.name = name;
    }

    public char getName() {
        return name;
    }
}
