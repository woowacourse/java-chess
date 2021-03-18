package chess.domain.board.position;

import chess.domain.piece.Owner;

import java.util.Arrays;

public enum Horizontal {
    ONE(0),
    TWO(1),
    THREE(2),
    FOUR(3),
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

    static Horizontal of(int index){
        return Arrays.stream(Horizontal.values())
                .filter(h -> h.index == index)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    public int getIndex(){
        return index;
    }

    public int getDistance(Horizontal other){
        return Math.abs(this.index - other.getIndex());
    }

    public boolean isForward(Owner owner, Horizontal other){
        if (owner.equals(Owner.BLACK)) {
            return this.getIndex() < other.getIndex();
        }

        if (owner.equals(Owner.WHITE)) {
            return this.getIndex() > other.getIndex();
        }

        throw new IllegalArgumentException();
    }

    public Horizontal add(int h){
        return of(index +h);
    }
}
