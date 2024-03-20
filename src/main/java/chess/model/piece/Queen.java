package chess.model.piece;

import chess.model.Position;

public class Queen extends Piece {

    public Queen(PieceType pieceType) {
        super(pieceType);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        int rowDifference = Math.abs(calculateRowDifference(source, target));
        int columnDifference = Math.abs(calculateColumnDifference(source, target));

        if (rowDifference == columnDifference) {
            return true;
        }
        if (rowDifference > 0 && columnDifference == 0) {
            return true;
        }
        if (rowDifference == 0 && columnDifference > 0) {
            return true;
        }
        return false;
    }
}
