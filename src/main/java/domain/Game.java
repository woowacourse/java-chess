package domain;

import domain.piece.EmptyPiece;
import domain.piece.Piece;
import domain.piece.Position;

import java.util.List;
import java.util.Map;

public class Game {
    private final Map<Position, Piece> chessBoard;

    public Game(Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public static Game create() {
        return new Game(new ChessBoardGenerator().generate());
    }

    public void move(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = this.chessBoard.get(sourcePosition);
        validateMoving(sourcePosition, targetPosition, sourcePiece);
        movePiece(sourcePosition, targetPosition, sourcePiece);
    }

    private void validateMoving(Position sourcePosition, Position targetPosition, Piece sourcePiece) {
        validateExistPieceOnSourcePosition(sourcePiece);
        validateIsMovable(sourcePosition, targetPosition);
        validatePathIncludeAnyPiece(sourcePosition, targetPosition, sourcePiece);
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

    private static void validateExistPieceOnSourcePosition(Piece sourcePiece) {
        if (sourcePiece.isEmptyPiece()) {
            throw new IllegalArgumentException("source위치에 말이 없습니다.");
        }
    }

    private void movePiece(Position sourcePosition, Position targetPosition, Piece sourcePiece) {
        this.chessBoard.put(sourcePosition, new EmptyPiece());
        this.chessBoard.put(targetPosition, sourcePiece);
    }
}
