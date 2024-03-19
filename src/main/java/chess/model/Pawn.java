package chess.model;

public class Pawn extends Piece {
    public Pawn(Side side, ChessPosition chessPosition) {
        super(side, chessPosition);
    }

    @Override
    public String getText() {
        if (side.isWhite()) {
            return "p";
        }
        return "P";
    }
}
