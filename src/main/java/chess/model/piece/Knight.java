package chess.model.piece;

import chess.model.Position;

public class Knight extends Piece {

    public Knight(PieceType pieceType) {
        super(pieceType);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        int rowDifference = Math.abs(calculateRowDifference(source, target));
        int columnDifference = Math.abs(calculateColumnDifference(source, target));

        if (rowDifference == 2 && columnDifference == 1) {
            return true;
        }
        return rowDifference == 1 && columnDifference == 2;
    }
}
