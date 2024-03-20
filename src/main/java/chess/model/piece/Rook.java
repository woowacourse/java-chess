package chess.model.piece;

import chess.model.Position;

public class Rook extends Piece {

    public Rook(PieceType pieceType) {
        super(pieceType);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        int rowDifference = Math.abs(calculateRowDifference(source, target));
        int columnDifference = Math.abs(calculateColumnDifference(source, target));

        if (rowDifference > 0 && columnDifference == 0) {
            return true;
        }
        return rowDifference == 0 && columnDifference > 0;
    }
}
