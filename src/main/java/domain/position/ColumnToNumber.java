package domain.position;

public enum ColumnToNumber {

    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private final int number;

    ColumnToNumber(int number) {
        this.number = number;
    }

    public static int of(char alphabet) {
        try {
            String convertedAlphabet = Character.toString(alphabet);
            ColumnToNumber columnToNumber = ColumnToNumber.valueOf(convertedAlphabet.toUpperCase());
            return columnToNumber.getNumber();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 열 좌표값입니다.");
        }
    }

    private int getNumber() {
        return number;
    }
}
