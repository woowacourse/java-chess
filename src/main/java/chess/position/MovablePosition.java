package chess.position;

import chess.ChessBoard;
import chess.piece.ChessPiece;
import chess.piece.Side;
import java.util.ArrayList;
import java.util.List;

public class MovablePosition {
    private static final int MIN_BOUNDARY = 1;
    private static final int MAX_BOUNDARY = 8;

    private final List<Position> movablePosition;

    public MovablePosition() {
        this.movablePosition = new ArrayList<>();
    }

    public MovablePosition(List<Position> movablePosition) {
        this.movablePosition = movablePosition;
    }

    public void findMovablePosition(ChessBoard chessBoard, Position sourcePosition, List<Position> moveVector,
                                    boolean isInfinite) {
        for (Position position : moveVector) {
            findRoute(chessBoard, position, sourcePosition, isInfinite);
        }
    }

    private void findRoute(ChessBoard chessBoard, Position movingPosition, Position sourcePosition,
                           boolean isInfinite) {
        if (isInfinite) {
            findInfiniteRoute(sourcePosition, movingPosition, chessBoard);
            return;
        }
        findStepRoute(chessBoard, movingPosition, sourcePosition);
    }

    private void findInfiniteRoute(Position sourcePosition, Position movingPosition, ChessBoard chessBoard) {
        int newX = sourcePosition.getXPosition() + movingPosition.getXPosition();
        int newY = sourcePosition.getYPosition() + movingPosition.getYPosition();
        while (newX >= MIN_BOUNDARY && newX <= MAX_BOUNDARY && newY >= MIN_BOUNDARY && newY <= MAX_BOUNDARY) {
            ChessPiece nextPositionPiece = chessBoard.getChessPieceByPosition(Position.initPosition(newX, newY));
            if (nextPositionPiece.getSide().equals(Side.EMPTY)) {
                movablePosition.add(Position.initPosition(newX, newY));
                newX += movingPosition.getXPosition();
                newY += movingPosition.getYPosition();
                continue;
            }
            if (nextPositionPiece.isEqualSide(chessBoard.getChessPieceByPosition(sourcePosition))) {
                break;
            }
            movablePosition.add(Position.initPosition(newX, newY));
            break;
        }
    }

    private void findStepRoute(ChessBoard chessBoard, Position movingPosition, Position sourcePosition) {
        int newX = sourcePosition.getXPosition() + movingPosition.getXPosition();
        int newY = sourcePosition.getYPosition() + movingPosition.getYPosition();
        if (newX >= MIN_BOUNDARY && newX <= MAX_BOUNDARY && newY >= MIN_BOUNDARY && newY <= MAX_BOUNDARY) {
            addStepRoute(chessBoard, sourcePosition, newX, newY);
        }
    }

    private void addStepRoute(ChessBoard chessBoard, Position sourcePosition, int newX, int newY) {
        ChessPiece nextPositionPiece = chessBoard.getChessPieceByPosition(Position.initPosition(newX, newY));
        if (!nextPositionPiece.isEqualSide(chessBoard.getChessPieceByPosition(sourcePosition))) {
            movablePosition.add(Position.initPosition(newX, newY));
        }
    }

    public List<Position> getMovablePosition() {
        return movablePosition;
    }
}
