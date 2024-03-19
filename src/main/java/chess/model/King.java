package chess.model;

public class King extends Piece {
    public King(Side side, ChessPosition chessPosition) {
        super(side, chessPosition);
    }

    @Override
    public String getText() {
        if (side.isWhite()) {
            return "k";
        }
        return "K";
    }
}
