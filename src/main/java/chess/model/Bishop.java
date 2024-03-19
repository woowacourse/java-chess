package chess.model;

public class Bishop extends Piece {
    public Bishop(Side side, ChessPosition chessPosition) {
        super(side, chessPosition);
    }

    @Override
    public String getText() {
        if (side.isWhite()) {
            return "b";
        }
        return "B";
    }
}
