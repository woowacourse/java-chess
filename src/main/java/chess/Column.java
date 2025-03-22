package chess;

import java.util.Arrays;

public enum Column {

    A('A'),
    B('B'),
    C('C'),
    D('D'),
    E('E'),
    F('F'),
    G('G'),
    H('H');

    private final char name;

    Column(char name) {
        this.name = name;
    }

    public char getName() {
        return name;
    }

    public static Column findColumn(char input){
        return Arrays.stream(Column.values())
                .filter(row -> row.name==input)
                .findAny()
                .orElseThrow(()->new IllegalArgumentException("A부터 H까지 열을 선택해주세요(대문자만 가능합니다)"));
    }

    public boolean isFarLeft() {
        return ordinal() == 0;
    }

    public boolean isFarRight() {
        return ordinal() + 1 == values().length;
    }

    public boolean canMoveLeft(final int step) {
        return ordinal() - step >= 0;
    }

    public Column moveLeft() {
        return moveLeft(1);
    }

    public Column moveLeft(final int step) {
        if (canMoveLeft(step)) {
            return values()[ordinal() - step];
        }

        throw new IllegalStateException("움직일 수 없는 위치입니다.");
    }

    public boolean canMoveRight(final int step) {
        return ordinal() + step < values().length;
    }

    public Column moveRight() {
        return moveRight(1);
    }

    public Column moveRight(final int step) {
        if (canMoveRight(step)) {
            return values()[ordinal() + step];
        }

        throw new IllegalStateException("움직일 수 없는 위치입니다.");
    }

}
