package domain;

import domain.board.Board;
import domain.piece.Piece;
import domain.position.Position;

public class Chess {

    private final Board board = Board.create();

    public void play(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = board.findPieceByPosition(sourcePosition);
        Piece targetPiece = board.findPieceByPosition(targetPosition);
        if (sourcePiece.isDifferentColor(targetPiece) && sourcePiece.canMove(sourcePosition, targetPosition)
                && board.isNotBlocked(sourcePosition, targetPosition)) {
            board.placePieceByPosition(sourcePiece, targetPosition);
            board.displacePieceByPosition(sourcePosition);
        }
    }

    public Board getBoard() {
        return board;
    }
}
