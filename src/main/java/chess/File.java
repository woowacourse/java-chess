package chess;

public enum File {
    a(1),
    b(2),
    c(3),
    d(4),
    e(5),
    f(6),
    g(7),
    h(8);
    private final int value;

    File(int value) {
        this.value = value;
    }

    public FileDifference calculateDifference(File file) {
        return new FileDifference(value - file.value);
    }
}
