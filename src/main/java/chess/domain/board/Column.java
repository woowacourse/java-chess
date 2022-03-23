package chess.domain.board;

public enum Column {

    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g'),
    H('h');

    private final Character value;

    Column(Character value) {
        this.value = value;
    }

    public Character getValue() {
        return value;
    }
}
