package chess.domain;

public enum File {
    FILE_A(1),
    FILE_B(2),
    FILE_C(3),
    FILE_D(4),
    FILE_E(5),
    FILE_F(6),
    FILE_G(7),
    FILE_H(8);

    private final int number;

    File(int number) {
        this.number = number;
    }
}
