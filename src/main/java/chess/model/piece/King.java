package chess.model.piece;

import chess.model.Position;

public class King extends Piece {

    public King(PieceType pieceType) {
        super(pieceType);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        int rowDifference = Math.abs(calculateRowDifference(source, target));
        int columnDifference = Math.abs(calculateColumnDifference(source, target));
        return rowDifference < 2 && columnDifference < 2;
    }
}
