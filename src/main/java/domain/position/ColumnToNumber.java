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
            return convertAlphabetToColumnNumber(alphabet).getNumber();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("[ERROR] 사용할 수 없는 명령어를 입력했습니다.");
        }
    }

    private static ColumnToNumber convertAlphabetToColumnNumber(char alphabet) {
        String convertedAlphabet = Character.toString(alphabet);
        return ColumnToNumber.valueOf(convertedAlphabet.toUpperCase());
    }

    private int getNumber() {
        return number;
    }
}
