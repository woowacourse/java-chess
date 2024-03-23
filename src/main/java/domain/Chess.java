package domain;

import domain.board.Board;
import domain.piece.Color;
import domain.piece.Piece;
import domain.position.Position;

public class Chess { // TODO: 생성자 초기화 vs 변수 초기화?

    private final Board board = Board.create();
    private Color turn = Color.WHITE;

    public void play(Position sourcePosition, Position targetPosition) { // 소스와 타겟이 같은 경우
        Piece sourcePiece = board.findPieceByPosition(sourcePosition);
        Piece targetPiece = board.findPieceByPosition(targetPosition);

        if (sourcePiece.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 선택된 위치에 기물이 없습니다.");
        }
        if (sourcePiece.isOppositeColor(turn)) {
            throw new IllegalArgumentException("[ERROR] 차례가 아닙니다.");
        }
        if (sourcePiece.isNotKnight() && board.isBlocked(sourcePosition, targetPosition)) {
            throw new IllegalArgumentException("[ERROR] 다른 기물이 길을 막습니다.");
        }
        if (sourcePiece.isSameColor(targetPiece)) {
            throw new IllegalArgumentException("[ERROR] 가려는 곳에 같은 편 기물이 있습니다.");
        }
        if (sourcePiece.canAttack(sourcePosition, targetPosition) && sourcePiece.isOppositeColor(targetPiece)) {
            board.placePieceByPosition(sourcePiece, targetPosition);
            board.displacePieceByPosition(sourcePosition);
            switchTurn();
            return;
        }
        if (!sourcePiece.canMove(sourcePosition, targetPosition)) {
            throw new IllegalArgumentException("[ERROR] 움직일 수 없는 방식입니다.");
        }
        if (targetPiece.isNotBlank()) {
            throw new IllegalArgumentException("[ERROR] 가려는 곳에 기물이 있습니다.");
        }
        board.placePieceByPosition(sourcePiece, targetPosition);
        board.displacePieceByPosition(sourcePosition);
        switchTurn();
    }

    private void switchTurn() {
        turn = turn.oppositeColor();
    }

    public Board getBoard() {
        return board;
    }

    public Color getTurn() {
        return turn;
    }
}
