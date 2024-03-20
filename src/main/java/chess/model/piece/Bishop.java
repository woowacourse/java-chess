package chess.model.piece;

import chess.model.Position;

public class Bishop extends Piece {

    public Bishop(PieceType pieceType) {
        super(pieceType);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        int rowDifference = Math.abs(calculateRowDifference(source, target));
        int columnDifference = Math.abs(calculateColumnDifference(source, target));
        return rowDifference == columnDifference;
    }
}
