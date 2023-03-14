package chess;

public class ChessPiece {

    private final Shape shape;
    private final Side side;


    public ChessPiece(Shape shape, Side side) {
        this.shape = shape;
        this.side = side;
    }
}
