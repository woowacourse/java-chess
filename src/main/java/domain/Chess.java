package domain;

import domain.board.Board;
import domain.board.Turn;
import domain.piece.Color;
import domain.piece.Piece;
import domain.position.Position;

public class Chess {

    private final Board board;
    private Turn turn;

    public Chess() {
        this.board = Board.create();
        this.turn = new Turn(Color.WHITE);
    }

    public void tryMove(Position sourcePosition, Position targetPosition) {
        validateMovement(sourcePosition, targetPosition);
        if (canAttack(sourcePosition, targetPosition)) {
            attack(sourcePosition, targetPosition);
            return;
        }
        validateCanMove(sourcePosition, targetPosition);
        move(sourcePosition, targetPosition);
    }

    private void validateMovement(Position sourcePosition, Position targetPosition) {
        validate(sourcePosition.isSame(targetPosition), "[ERROR] 움직여야 합니다.");
        validate(board.isBlocked(sourcePosition, targetPosition), "[ERROR] 다른 기물이 길을 막습니다.");

        Piece sourcePiece = board.findPieceByPosition(sourcePosition);
        Piece targetPiece = board.findPieceByPosition(targetPosition);
        validate(sourcePiece.isBlank(), "[ERROR] 선택된 위치에 기물이 없습니다.");
        validate(sourcePiece.isNotTurn(turn), "[ERROR] 차례가 아닙니다.");
        validate(targetPiece.isSameColor(sourcePiece), "[ERROR] 가려는 곳에 같은 편 기물이 있습니다.");
    }

    private boolean canAttack(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = board.findPieceByPosition(sourcePosition);
        Piece targetPiece = board.findPieceByPosition(targetPosition);
        return sourcePiece.canAttack(sourcePosition, targetPosition) && targetPiece.isOppositeSide(sourcePiece);
    }

    private void attack(Position sourcePosition, Position targetPosition) {
        move(sourcePosition, targetPosition);
        // move()와 같은 역할의 함수지만 확장성을 위해 분리하였습니다.
    }

    private void validateCanMove(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = board.findPieceByPosition(sourcePosition);
        Piece targetPiece = board.findPieceByPosition(targetPosition);
        validate(!sourcePiece.canMove(sourcePosition, targetPosition), "[ERROR] 움직일 수 없는 방식입니다.");
        validate(targetPiece.isNotBlank(), "[ERROR] 가려는 곳에 기물이 있습니다.");
    }

    private void move(Position sourcePosition, Position targetPosition) {
        board.movePiece(sourcePosition, targetPosition);
        turn = turn.next();
    }

    private void validate(boolean condition, String errorMessage) {
        if (condition) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public Board getBoard() {
        return board;
    }

    public Turn getTurn() {
        return turn;
    }
}
