package domain.position;

import java.util.Arrays;

public enum XPosition {

    A("a",1),
    B("b",2),
    C("c",3),
    D("d",4),
    E("e",5),
    F("f",6),
    G("g",7),
    H("h",8);

    private final String symbol;
    private final int x;

    XPosition(final String symbol, final int x) {
        this.symbol = symbol;
        this.x = x;
    }

    public static XPosition of(int x){
        return Arrays.stream(values())
                .filter(value -> value.x == x)
                .findFirst()
                .orElse(null);
    }

    public int getX() {
        return x;
    }

    @Override
    public String toString() {
        return "XPosition{" +"x=" + x + '}';
    }
}
