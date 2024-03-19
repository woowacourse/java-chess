package chess.model;

public abstract class Piece implements MoveStrategy {
    protected final Side side;
    protected final ChessPosition chessPosition;

    protected Piece(Side side, ChessPosition chessPosition) {
        this.side = side;
        this.chessPosition = chessPosition;
    }

    public abstract String getText();

    public ChessPosition getChessPosition() {
        return chessPosition;
    }
}
