package domain;

import domain.board.Board;
import domain.piece.Color;
import domain.piece.Piece;
import domain.position.Position;

public class Chess { // TODO: 생성자 초기화 vs 변수 초기화?

    private final Board board = Board.create();
    private Color turn = Color.WHITE;

    public void play(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = board.findPieceByPosition(sourcePosition);
        Piece targetPiece = board.findPieceByPosition(targetPosition);

        if (sourcePiece.isDifferentColor(turn)) { // TODO: 부정 -> 긍정 변경하기
            throw new IllegalArgumentException("[ERROR] 이동할 수 없는 기물입니다. 차례가 아닙니다.");
        }
        if (!sourcePiece.isKnight() && !board.isNotBlocked(sourcePosition, targetPosition)) {
            throw new IllegalArgumentException("[ERROR] 이동할 수 없는 위치입니다. 경로가 비어있지 않습니다.");
        }
        if (sourcePiece.isSameColor(targetPiece)) {
            throw new IllegalArgumentException("[ERROR] 이동할 수 없는 위치입니다. 목적지에 같은 편 기물이 있습니다.");
        }
        if (sourcePiece.canAttack(sourcePosition, targetPosition) && sourcePiece.isDifferentColor(targetPiece)) {
            board.placePieceByPosition(sourcePiece, targetPosition);
            board.displacePieceByPosition(sourcePosition);
            switchTurn();
            return;
        }
        if (!sourcePiece.canMove(sourcePosition, targetPosition)) {
            throw new IllegalArgumentException("[ERROR] 이동할 수 없는 위치입니다. 해당 기물은 해당 위치로 움직일 수 없습니다.");
        }
        if (sourcePiece.canMove(sourcePosition, targetPosition) && !targetPiece.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 이동할 수 없는 위치입니다. 목적지에 기물이 있습니다.");
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
}
