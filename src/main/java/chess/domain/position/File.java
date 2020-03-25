package chess.domain.position;

public enum File {
    a(1),
    b(2),
    c(3),
    d(4),
    e(5),
    f(6),
    g(7),
    h(8);

    private int index;

    File(int index) {
        this.index = index;
    }
}
