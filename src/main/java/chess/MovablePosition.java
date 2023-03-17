package chess;

import chess.piece.ChessPiece;

import chess.piece.Pawn;
import java.util.ArrayList;
import java.util.List;

public class MovablePosition {
    private static final int ONE_STEP_MOVEMENT = 1;
    private static final int TWO_STEP_MOVEMENT = 2;
    private static final int UNLIMIT_STEP_MOVEMENT = 8;
    private static final int MIN_CHESS_BOUNDARY = 1;
    private static final int MAX_CHESS_BOUNDARY = 8;
    private static final String BLANK = "blank";

    private static final List<Position> crossVector = List.of(Position.initPosition(1, 0), Position.initPosition(0, 1), Position.initPosition(-1, 0), Position.initPosition(0, -1));
    private static final List<Position> diagonalVector = List.of(Position.initPosition(1, 1), Position.initPosition(1, -1), Position.initPosition(-1, 1), Position.initPosition(-1, -1));
    private static final List<Position> knightMoveVector = List.of(Position.initPosition(1, 2), Position.initPosition(-1, 2), Position.initPosition(1, -2), Position.initPosition(-1, -2),
            Position.initPosition(2, 1), Position.initPosition(-2, 1), Position.initPosition(2, -1), Position.initPosition(-2, -1));
    private static final List<Position> blackPawnVector = List.of(Position.initPosition(0, -1));
    private static final List<Position> whitePawnVector = List.of(Position.initPosition(0, 1));

    private final List<Position> movablePosition = new ArrayList<>();

    public MovablePosition() {
    }

    public List<Position> findByShape(ChessBoard chessBoard, Position sourcePosition) {
        ChessPiece chessPiece = chessBoard.getChessPieceByPosition(sourcePosition);
        String pieceShape = Shape.getNameByClass(chessPiece);
        makePawnRoute(chessBoard, sourcePosition, pieceShape);
        makeKnightRoute(chessBoard, sourcePosition, pieceShape);
        makeBishopRoute(chessBoard, sourcePosition, pieceShape);
        makeRookRoute(chessBoard, sourcePosition, pieceShape);
        makeQueenRoute(chessBoard, sourcePosition, pieceShape);
        makeKingRoute(chessBoard, sourcePosition, pieceShape);
        return movablePosition;
    }

    private void makePawnRoute(ChessBoard chessBoard, Position sourcePosition, String pieceShape) {
        if (pieceShape.equals(Shape.WHITE_PAWN.getName())) {
            addPawnPosition(chessBoard, sourcePosition, whitePawnVector, 2);
        }
        if (pieceShape.equals(Shape.BLACK_PAWN.getName())) {
            addPawnPosition(chessBoard, sourcePosition, blackPawnVector, 7);
        }
    }

    private void makeBishopRoute(ChessBoard chessBoard, Position sourcePosition, String pieceShape) {
        if (pieceShape.equals(Shape.WHITE_BISHOP.getName()) || pieceShape.equals(Shape.BLACK_BISHOP.getName())) {
            addDiagonalPosition(chessBoard, sourcePosition, true);
        }
    }

    private void makeRookRoute(ChessBoard chessBoard, Position sourcePosition, String pieceShape) {
        if (pieceShape.equals(Shape.WHITE_ROOK.getName()) || pieceShape.equals(Shape.BLACK_ROOK.getName())) {
            addCrossPosition(chessBoard, sourcePosition, true);
        }
    }

    private void makeKnightRoute(ChessBoard chessBoard, Position sourcePosition, String pieceShape) {
        if (pieceShape.equals(Shape.WHITE_KNIGHT.getName()) || pieceShape.equals(Shape.BLACK_KNIGHT.getName())) {
            addKnightPosition(chessBoard, sourcePosition);
        }
    }

    private void makeQueenRoute(ChessBoard chessBoard, Position sourcePosition, String pieceShape) {
        if (pieceShape.equals(Shape.WHITE_QUEEN.getName()) || pieceShape.equals(Shape.BLACK_QUEEN.getName())) {
            addCrossPosition(chessBoard, sourcePosition, true);
            addDiagonalPosition(chessBoard, sourcePosition, true);
        }
    }

    private void makeKingRoute(ChessBoard chessBoard, Position sourcePosition, String pieceShape) {
        if (pieceShape.equals(Shape.WHITE_KING.getName()) || pieceShape.equals(Shape.BLACK_KING.getName())) {
            addCrossPosition(chessBoard, sourcePosition, false);
            addDiagonalPosition(chessBoard, sourcePosition, false);
        }
    }

    public void addCrossPosition(ChessBoard chessBoard, Position sourcePosition, boolean isInfinite) {
        int limit = ONE_STEP_MOVEMENT;
        if (isInfinite) {
            limit = UNLIMIT_STEP_MOVEMENT;
        }
        for (Position crossPosition : crossVector) {
            findRoute(chessBoard, crossPosition, sourcePosition, limit);
        }
    }

    public void addDiagonalPosition(ChessBoard chessBoard, Position sourcePosition, boolean isInfinite) {
        int limit = ONE_STEP_MOVEMENT;
        if (isInfinite) {
            limit = UNLIMIT_STEP_MOVEMENT;
        }
        for (Position diagonalPosition : diagonalVector) {
            findRoute(chessBoard, diagonalPosition, sourcePosition, limit);
        }
    }

    public void addKnightPosition(ChessBoard chessBoard, Position sourcePosition) {
        for (Position knightPosition : knightMoveVector) {
            findRoute(chessBoard, knightPosition, sourcePosition, ONE_STEP_MOVEMENT);
        }
    }

    public void addPawnPosition(ChessBoard chessBoard, Position sourcePosition, List<Position> moveVector, int startY) {
        for (Position pawnPosition : moveVector) {
            findPawnRouteByRow(chessBoard, sourcePosition, startY, pawnPosition);
        }
    }

    private void findPawnRouteByRow(ChessBoard chessBoard, Position sourcePosition, int startY, Position pawnPosition) {
        if(sourcePosition.getYPosition() == startY) {
            findRoute(chessBoard, pawnPosition, sourcePosition, TWO_STEP_MOVEMENT);
            return;
        }
        findRoute(chessBoard, pawnPosition, sourcePosition, ONE_STEP_MOVEMENT);
    }

    private void findRoute(ChessBoard chessBoard, Position movingPosition, Position sourcePosition, int limit) {
        for (int step = ONE_STEP_MOVEMENT; step <= limit; step++) {
            int newX = sourcePosition.getXPosition() + movingPosition.getXPosition() * step;
            int newY = sourcePosition.getYPosition() + movingPosition.getYPosition() * step;
            if ((newX >= MIN_CHESS_BOUNDARY && newX <= MAX_CHESS_BOUNDARY) && (newY >= MIN_CHESS_BOUNDARY && newY <= MAX_CHESS_BOUNDARY)) {
                if (chessBoard.getChessPieceByPosition(Position.initPosition(newX, newY)).getSide().equals(BLANK)) {
                    movablePosition.add(Position.initPosition(newX, newY));
                    continue;
                }
                if (checkPieceSideInPosition(chessBoard, sourcePosition, Position.initPosition(newX, newY))) {
                    break;
                }
                if(chessBoard.getChessPieceByPosition(sourcePosition).getClass().equals(Pawn.class)){
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
