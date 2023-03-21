package chess.position;

import chess.ChessBoard;
import chess.piece.ChessPiece;
import chess.piece.Shape;
import chess.piece.Side;
import java.util.ArrayList;
import java.util.List;

public class MovablePosition {
    private static final int ONE_STEP_MOVEMENT = 1;
    private static final int TWO_STEP_MOVEMENT = 2;
    private static final int UNLIMIT_STEP_MOVEMENT = 8;
    private static final int MIN_CHESS_BOUNDARY = 1;
    private static final int MAX_CHESS_BOUNDARY = 8;
    private static final String BLANK = ".";

    private final List<Position> movablePosition = new ArrayList<>();

    public MovablePosition() {
    }

    public List<Position> findByShape(ChessBoard chessBoard, Position sourcePosition) {
        makeRoute(chessBoard, sourcePosition);
        return movablePosition;
    }

    private void makeRoute(ChessBoard chessBoard, Position sourcePosition) {
        ChessPiece sourcePiece = chessBoard.getChessPieceByPosition(sourcePosition);
        if (sourcePiece.getShape().equals(Shape.PAWN) && sourcePiece.getSide().equals(Side.WHITE)) {
            addPawnPosition(chessBoard, sourcePosition, Direction.WHITE_PAWN_MOVE_DIRECTION, 2);
        }
        if (sourcePiece.getShape().equals(Shape.PAWN) && sourcePiece.getSide().equals(Side.BLACK)) {
            addPawnPosition(chessBoard, sourcePosition, Direction.BLACK_PAWN_MOVE_DIRECTION, 7);
        }
        if (sourcePiece.getShape().equals(Shape.ROOK)) {
            addCrossOrDiagonalPosition(chessBoard, sourcePosition, Direction.CROSS_DIRECTION, true);
        }
        if (sourcePiece.getShape().equals(Shape.BISHOP)) {
            addCrossOrDiagonalPosition(chessBoard, sourcePosition, Direction.DIAGONAL_DIRECTION, true);
        }
        if (sourcePiece.getShape().equals(Shape.KNIGHT)) {
            addKnightPosition(chessBoard, sourcePosition);
        }
        if (sourcePiece.getShape().equals(Shape.QUEEN)) {
            addRoyalPosition(chessBoard, sourcePosition, true);
        }
        if (sourcePiece.getShape().equals(Shape.KING)) {
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
                if (chessBoard.getChessPieceByPosition(Position.initPosition(newX, newY)).getName().equals(BLANK)) {
                    movablePosition.add(Position.initPosition(newX, newY));
                    continue;
                }
                if (checkPieceSideInPosition(chessBoard, sourcePosition, Position.initPosition(newX, newY))) {
                    break;
                }
                if (chessBoard.getChessPieceByPosition(sourcePosition).getShape().equals(Shape.PAWN)) {
                    break;
                }
                movablePosition.add(Position.initPosition(newX, newY));
                break;
            }
        }
    }

    public boolean checkPieceSideInPosition(ChessBoard chessBoard, Position sourcePosition, Position checkPosition) {
        Side sourcePieceSide = chessBoard.getChessPieceByPosition(sourcePosition).getSide();
        Side checkPieceSide = chessBoard.getChessPieceByPosition(checkPosition).getSide();
        return sourcePieceSide.equals(checkPieceSide);
    }

    public List<Position> getMovablePosition() {
        return movablePosition;
    }
}
