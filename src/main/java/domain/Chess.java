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
        validate(sourcePosition.equals(targetPosition), "[ERROR] 제자리에 있을 수 없습니다.");

        Piece sourcePiece = board.findPieceByPosition(sourcePosition);
        Piece targetPiece = board.findPieceByPosition(targetPosition);
        validate(sourcePiece.isBlank(), "[ERROR] 출발지에 기물이 없어 이동하지 못했습니다.");
        validate(sourcePiece.isNotTurn(turn), "[ERROR] 차례가 아니므로 이동하지 못했습니다.");
        validate(board.isBlocked(sourcePosition, targetPosition), "[ERROR] 다른 기물에 막혀 이동하지 못했습니다.");
        validate(targetPiece.isSameColor(sourcePiece), "[ERROR] 도착지에 같은 편 기물이 있어 이동하지 못했습니다.");
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
        validate(!sourcePiece.canMove(sourcePosition, targetPosition), "[ERROR] 해당 기물이 움직일 수 없는 방식입니다.");
        validate(targetPiece.isNotBlank(), "[ERROR] 도착지에 기물이 있어 움직일 수 없습니다.");
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
