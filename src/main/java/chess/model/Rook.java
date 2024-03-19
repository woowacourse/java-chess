package chess.model;

public class Rook extends Piece {
    public Rook(Side side, ChessPosition chessPosition) {
        super(side, chessPosition);
    }

    @Override
    public String getText() {
        if (side.isWhite()) {
            return "r";
        }
        return "R";
    }
}
