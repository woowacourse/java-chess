package chess.domain.board.position;

public enum Vertical {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7);

    private int index;

    Vertical(int index){
        this.index = index;
    }

    static int indexOf(String symbol){
        Vertical value = Vertical.valueOf(symbol.toUpperCase());
        return value.index;
    }

    public int getIndex(){
        return index;
    }

    public int getDistance(Vertical other){
        return Math.abs(this.index - other.getIndex());
    }
}
