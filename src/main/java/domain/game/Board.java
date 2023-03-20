package domain.game;

import domain.piece.EmptyPiece;
import domain.piece.Piece;
import domain.piece.Position;
import domain.piece.Side;

import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> chessBoard;

    public Board(Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public void move(Side side, Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = this.chessBoard.get(sourcePosition);
        validateMoving(sourcePosition, targetPosition, sourcePiece, side);
        movePiece(sourcePosition, targetPosition, sourcePiece);
    }

    private void validateMoving(Position sourcePosition, Position targetPosition, Piece sourcePiece, Side side) {
        validateSourcePositionIsEmpty(sourcePosition, sourcePiece);
        validateTurn(side, sourcePiece);
        validateIsMovable(sourcePosition, targetPosition);
        validatePathIncludeAnyPiece(sourcePosition, targetPosition, sourcePiece);
    }

    private void validateTurn(Side side, Piece sourcePiece) {
        if (sourcePiece.isIncorrectTurn(side)) {
            throw new IllegalArgumentException("다른 진영의 말은 움직일 수 없습니다.");
        }
    }

    private void validateSourcePositionIsEmpty(Position sourcePosition, Piece sourcePiece) {
        if (sourcePiece.isEmptyPiece()) {
            throw new IllegalArgumentException(sourcePosition + "에 움직일 수 있는 말이 없습니다.");
        }
    }

    private void validatePathIncludeAnyPiece(Position sourcePosition, Position targetPosition, Piece sourcePiece) {
        List<Position> path = sourcePiece.collectPath(sourcePosition, targetPosition);
        for (Position position : path) {
            checkIsExistAnyPieceOn(position);
        }
    }

    private void checkIsExistAnyPieceOn(Position position) {
        if (!this.chessBoard.get(position).isEmptyPiece()) {
            throw new IllegalArgumentException("경로에 다른 말이 있습니다.");
        }
    }

    private void validateIsMovable(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = this.chessBoard.get(sourcePosition);
        Piece targetPiece = this.chessBoard.get(targetPosition);
        if (!sourcePiece.isMovable(targetPiece, sourcePosition, targetPosition)) {
            throw new IllegalArgumentException("올바른 움직임이 아닙니다.");
        }
    }

    private void movePiece(Position sourcePosition, Position targetPosition, Piece sourcePiece) {
        this.chessBoard.put(sourcePosition, new EmptyPiece());
        this.chessBoard.put(targetPosition, sourcePiece);
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard;
    }
}
