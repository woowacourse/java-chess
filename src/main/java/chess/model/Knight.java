package chess.model;

public class Knight extends Piece {
    public Knight(Side side, ChessPosition chessPosition) {
        super(side, chessPosition);
    }

    @Override
    public String getText() {
        if (side.isWhite()) {
            return "n";
        }
        return "N";
    }
}
