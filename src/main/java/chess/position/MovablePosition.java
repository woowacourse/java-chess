package chess.position;

import chess.ChessBoard;
import chess.piece.ChessPiece;
import chess.piece.Pawn;
import chess.piece.Shape;
import java.util.ArrayList;
import java.util.List;

public class MovablePosition {
    private static final int ONE_STEP_MOVEMENT = 1;
    private static final int TWO_STEP_MOVEMENT = 2;
    private static final int UNLIMIT_STEP_MOVEMENT = 8;
    private static final int MIN_CHESS_BOUNDARY = 1;
    private static final int MAX_CHESS_BOUNDARY = 8;
    private static final String BLANK = "blank";

    private final List<Position> movablePosition = new ArrayList<>();

    public MovablePosition() {
    }

    public List<Position> findByShape(ChessBoard chessBoard, Position sourcePosition) {
        ChessPiece chessPiece = chessBoard.getChessPieceByPosition(sourcePosition);
        String pieceShape = Shape.getNameByClass(chessPiece);
        makeRoute(chessBoard, sourcePosition, pieceShape);
        return movablePosition;
    }

    private void makeRoute(ChessBoard chessBoard, Position sourcePosition, String pieceShape) {
        if (pieceShape.equals(Shape.WHITE_PAWN.getName())) {
            addPawnPosition(chessBoard, sourcePosition, Direction.HWITE_PAWN_MOVE_DIRECTION, 2);
        }
        if (pieceShape.equals(Shape.BLACK_PAWN.getName())) {
            addPawnPosition(chessBoard, sourcePosition, Direction.BLACK_PAWN_MOVE_DIRECTION, 7);
        }
        if (pieceShape.equals(Shape.WHITE_ROOK.getName()) || pieceShape.equals(Shape.BLACK_ROOK.getName())) {
            addCrossOrDiagonalPosition(chessBoard, sourcePosition, Direction.DIAGONAL_DIRECTION, true);
        }
        if (pieceShape.equals(Shape.WHITE_BISHOP.getName()) || pieceShape.equals(Shape.BLACK_BISHOP.getName())) {
            addCrossOrDiagonalPosition(chessBoard, sourcePosition, Direction.CROSS_DIRECTION, true);
        }
        if (pieceShape.equals(Shape.WHITE_KNIGHT.getName()) || pieceShape.equals(Shape.BLACK_KNIGHT.getName())) {
            addKnightPosition(chessBoard, sourcePosition);
        }
        if (pieceShape.equals(Shape.WHITE_QUEEN.getName()) || pieceShape.equals(Shape.BLACK_QUEEN.getName())) {
            addRoyalPosition(chessBoard, sourcePosition, true);
        }
        if (pieceShape.equals(Shape.WHITE_KING.getName()) || pieceShape.equals(Shape.BLACK_KING.getName())) {
            addRoyalPosition(chessBoard, sourcePosition, false);
        }
    }

    private void addRoyalPosition(ChessBoard chessBoard, Position sourcePosition, boolean isInfinite) {
        addCrossOrDiagonalPosition(chessBoard, sourcePosition, Direction.CROSS_DIRECTION, isInfinite);
        addCrossOrDiagonalPosition(chessBoard, sourcePosition, Direction.DIAGONAL_DIRECTION, isInfinite);
    }

    public void addCrossOrDiagonalPosition(
            ChessBoard chessBoard, Position sourcePosition, List<Position> moveVector, boolean isInfinite) {
        int limit = ONE_STEP_MOVEMENT;
        if (isInfinite) {
            limit = UNLIMIT_STEP_MOVEMENT;
        }
        for (Position position : moveVector) {
            findRoute(chessBoard, position, sourcePosition, limit);
        }
    }

    public void addKnightPosition(ChessBoard chessBoard, Position sourcePosition) {
        for (Position knightPosition : Direction.KNIGHT_DIRECTION) {
            findRoute(chessBoard, knightPosition, sourcePosition, ONE_STEP_MOVEMENT);
        }
    }

    public void addPawnPosition(ChessBoard chessBoard, Position sourcePosition, List<Position> moveVector, int startY) {
        for (Position pawnPosition : moveVector) {
            findPawnRouteByRow(chessBoard, sourcePosition, startY, pawnPosition);
        }
    }

    private void findPawnRouteByRow(ChessBoard chessBoard, Position sourcePosition, int startY, Position pawnPosition) {
        if (sourcePosition.getYPosition() == startY) {
            findRoute(chessBoard, pawnPosition, sourcePosition, TWO_STEP_MOVEMENT);
            return;
        }
        findRoute(chessBoard, pawnPosition, sourcePosition, ONE_STEP_MOVEMENT);
    }

    private void findRoute(ChessBoard chessBoard, Position movingPosition, Position sourcePosition, int limit) {
        for (int step = ONE_STEP_MOVEMENT; step <= limit; step++) {
            int newX = sourcePosition.getXPosition() + movingPosition.getXPosition() * step;
            int newY = sourcePosition.getYPosition() + movingPosition.getYPosition() * step;
            if ((newX >= MIN_CHESS_BOUNDARY && newX <= MAX_CHESS_BOUNDARY) && (newY >= MIN_CHESS_BOUNDARY
                    && newY <= MAX_CHESS_BOUNDARY)) {
                if (chessBoard.getChessPieceByPosition(Position.initPosition(newX, newY)).getSide().equals(BLANK)) {
                    movablePosition.add(Position.initPosition(newX, newY));
                    continue;
                }
                if (checkPieceSideInPosition(chessBoard, sourcePosition, Position.initPosition(newX, newY))) {
                    break;
                }
                if (chessBoard.getChessPieceByPosition(sourcePosition).getClass().equals(Pawn.class)) {
                    break;
                }
                movablePosition.add(Position.initPosition(newX, newY));
                break;
            }
        }
    }

    public boolean checkPieceSideInPosition(ChessBoard chessBoard, Position sourcePosition, Position checkPosition) {
        String sourcePieceSide = chessBoard.getChessPieceByPosition(sourcePosition).getSide();
        String checkPieceSide = chessBoard.getChessPieceByPosition(checkPosition).getSide();
        return sourcePieceSide.equals(checkPieceSide);
    }

    public List<Position> getMovablePosition() {
        return movablePosition;
    }
}
