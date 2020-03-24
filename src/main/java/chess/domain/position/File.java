package chess.domain.position;

public enum File {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private final int value;

    File(int value) {
        this.value = value;
    }

    public int getFileDifference(File targetFile) {
        return Math.abs(targetFile.value - value);
    }
}
