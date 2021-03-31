package chess.domain.board;

import java.util.Arrays;

public enum Horizontal {
    ONE(0),
    TWO(1),
    Three(2),
    Four(3),
    FIVE(4),
    SIX(5),
    SEVEN(6),
    EIGHT(7);

    private int symbol;
    private int index;

    Horizontal(int index){
        this.index = index;
    }

    static int indexOf(String number){
        Horizontal value = Arrays.stream(Horizontal.values())
                .filter(h -> h.symbol == Integer.parseInt(number))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);

        return value.index;
    }
}
