package domain;

public class Board {
    private static Board cashed;

    private final Chess[][] chess;

    static {
        cashed = new Board(new Chess[8][8]);
    }

    public Board(Chess[][] chess) {
        this.chess = chess;
    }

    public static Board getInstance() {
        return cashed;
    }
}
