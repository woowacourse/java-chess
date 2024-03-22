package domain;

import domain.board.Board;
import domain.piece.Piece;
import domain.position.Position;

public class Chess {

    private final Board board = Board.create();

    public void play(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = board.findPieceByPosition(sourcePosition);
        Piece targetPiece = board.findPieceByPosition(targetPosition);
        if (canMove(sourcePosition, targetPosition, sourcePiece, targetPiece)) {
            board.placePieceByPosition(sourcePiece, targetPosition);
            board.displacePieceByPosition(sourcePosition);
        }
    }

    private boolean canMove(Position sourcePosition, Position targetPosition, Piece sourcePiece, Piece targetPiece) {
        return sourcePiece.isDifferentColor(targetPiece)
                && sourcePiece.canMove(sourcePosition, targetPosition)
                && board.isNotBlocked(sourcePosition, targetPosition);
    }

    public Board getBoard() {
        return board;
    }
}
