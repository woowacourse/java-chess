package domain;

import domain.board.Board;
import domain.piece.Piece;
import domain.position.Position;

public class Chess {

    private final Board board = Board.create();

    public void play(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = board.findPieceByPosition(sourcePosition);
        Piece targetPiece = board.findPieceByPosition(targetPosition);

        if (!sourcePiece.isKnight() && !board.isNotBlocked(sourcePosition, targetPosition)) {
            throw new IllegalArgumentException("[ERROR] 이동할 수 없는 위치입니다. 경로가 비어있지 않습니다.");
        }
        if (!sourcePiece.isDifferentColor(targetPiece)) {
            throw new IllegalArgumentException("[ERROR] 이동할 수 없는 위치입니다. 목적지에 같은 편 기물이 있습니다.");
        }
        if (sourcePiece.canAttack(sourcePosition, targetPosition) && sourcePiece.isDifferentColor(targetPiece)) {
            board.placePieceByPosition(sourcePiece, targetPosition);
            board.displacePieceByPosition(sourcePosition);
            return;
        }
        if (!sourcePiece.canMove(sourcePosition, targetPosition)) {
            throw new IllegalArgumentException("[ERROR] 이동할 수 없는 위치입니다. 해당 기물은 해당 위치로 움직일 수 없습니다.");
        }
        board.placePieceByPosition(sourcePiece, targetPosition);
        board.displacePieceByPosition(sourcePosition);
    }

    public Board getBoard() {
        return board;
    }
}
