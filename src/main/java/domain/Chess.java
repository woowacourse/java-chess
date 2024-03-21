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
    // TODO: bug 해결
    // 1) 앞에 장애물이 있는지 판단하는 것
    // 2) 블랙 폰이 안 움직이는 현상 (전진이 반대 방향이라서)

    public Board getBoard() {
        return board;
    }
}
