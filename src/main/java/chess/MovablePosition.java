package chess;

import java.util.ArrayList;
import java.util.List;

public class MovablePosition {
    private static List<Position> crossVector = List.of(Position.initPosition(1, 0), Position.initPosition(0, 1), Position.initPosition(-1, 0), Position.initPosition(0, -1));
    private static List<Position> diagonalVector = List.of(Position.initPosition(1, 1), Position.initPosition(1, -1), Position.initPosition(-1, 1), Position.initPosition(-1, -1));
    private static List<Position> knightMoveVector = List.of(Position.initPosition(1, 2 ),Position.initPosition(-1, 2 ),Position.initPosition(1, -2 ),Position.initPosition(-1, -2 ),
            Position.initPosition(2, 1 ),Position.initPosition(-2, 1 ),Position.initPosition(2, -1 ),Position.initPosition(-2, -1 ));
    private static List<Position> blackPawnVector = List.of(Position.initPosition(0,-1), Position.initPosition(0,-2));
    private static List<Position> whitePawnVector = List.of(Position.initPosition(0,1), Position.initPosition(0,2));

    private List<Position> movablePosition;

    public MovablePosition() {
        this.movablePosition = new ArrayList<>();
    }

    public void addCrossPosition(ChessBoard chessBoard, Position sourcePosition, boolean isInfinite) {
        int limit = 1;
        if (isInfinite) {
            limit = 8;
        }
        for (Position crossPosition : crossVector) {
            findRoute(chessBoard, crossPosition, sourcePosition, limit);
        }
    }

    public boolean checkPieceSideInPosition(ChessBoard chessBoard, Position sourcePosition, Position checkPosition) {
        String sourcePieceSide = chessBoard.getChessPieceByPosition(sourcePosition).getSide();
        String checkPieceSide = chessBoard.getChessPieceByPosition(checkPosition).getSide();
        if(sourcePieceSide.equals(checkPieceSide)) {
            return true;
        }
        return false;
    }



    public void addDiagonalPosition(ChessBoard chessBoard, Position sourcePosition, boolean isInfinite) {
        int limit = 1;
        if (isInfinite) {
            limit = 8;
        }
        for (Position diagonalPosition : diagonalVector) {
            findRoute(chessBoard, diagonalPosition, sourcePosition, limit);
        }
    }

    private void findDiagonal(ChessBoard chessBoard, Position crossPosition, Position sourcePosition, int limit) {
        for (int i = 1; i <= limit; i++) {
            int newX = sourcePosition.getXPosition() + crossPosition.getXPosition()*i;
            int newY = sourcePosition.getYPosition() + crossPosition.getYPosition()*i;
            if ((newX >= 1 && newX <= 8) && (newY >= 1 && newY <= 8)) {
                movablePosition.add(Position.initPosition(newX, newY));
            }
        }
    }


    public void addKnightPosition(ChessBoard chessBoard, Position sourcePosition) {
        int limit = 1;
        for (Position knightPosition : knightMoveVector) {
            findRoute(chessBoard, knightPosition, sourcePosition, limit);
        }
    }


    private void findRoute(ChessBoard chessBoard, Position movingPosition, Position sourcePosition, int limit) {
        for (int i = 1; i <= limit; i++) {
            int newX = sourcePosition.getXPosition() + movingPosition.getXPosition()*i;
            int newY = sourcePosition.getYPosition() + movingPosition.getYPosition()*i;
            if ((newX >= 1 && newX <= 8) && (newY >= 1 && newY <= 8)) {
                if(chessBoard.getChessPieceByPosition(Position.initPosition(newX, newY)).getSide().equals("blank")){
                    movablePosition.add(Position.initPosition(newX, newY));
                    continue;
                }
                if(checkPieceSideInPosition(chessBoard, sourcePosition, Position.initPosition(newX, newY))){
                    break;
                }
                movablePosition.add(Position.initPosition(newX, newY));
                break;
            }
        }
    }

    public void addBlackPawnPosition(ChessBoard chessBoard, Position sourcePosition) {
        for (Position blackPawnPosition : blackPawnVector) {
            findRoute(chessBoard, blackPawnPosition, sourcePosition,1);
        }
    }

    private void findKnightRoute(ChessBoard chessBoard, Position movingPosition, Position sourcePosition, int limit) {
        int newX = sourcePosition.getXPosition() + movingPosition.getXPosition();
        int newY = sourcePosition.getYPosition() + movingPosition.getYPosition();
        if ((newX >= 1 && newX <= 8) && (newY >= 1 && newY <= 8)) {
            if(chessBoard.getChessPieceByPosition(Position.initPosition(newX, newY)).getSide().equals("blank")){
                movablePosition.add(Position.initPosition(newX, newY));
                return;
            }
            if(checkPieceSideInPosition(chessBoard, sourcePosition, Position.initPosition(newX, newY))){
                return;
            }
            movablePosition.add(Position.initPosition(newX, newY));
        }
    }

    private void findPawnRoute(ChessBoard chessBoard, Position movingPosition, Position sourcePosition) {
        int newX = sourcePosition.getXPosition() + movingPosition.getXPosition();
        int newY = sourcePosition.getYPosition() + movingPosition.getYPosition();
        if ((newX >= 1 && newX <= 8) && (newY >= 1 && newY <= 8)) {
            if(chessBoard.getChessPieceByPosition(Position.initPosition(newX, newY)).getSide().equals("blank")){
                movablePosition.add(Position.initPosition(newX, newY));
                return;
            }
            if(checkPieceSideInPosition(chessBoard, sourcePosition, Position.initPosition(newX, newY))){
                return;
            }
            movablePosition.add(Position.initPosition(newX, newY));
        }
    }


    public List<Position> getMovablePosition() {
        return movablePosition;
    }

    public void addWhitePawnPosition(ChessBoard chessBoard, Position sourcePosition) {
        for (Position whitePawnPosition : whitePawnVector) {
            findRoute(chessBoard, whitePawnPosition, sourcePosition,1);
        }
    }
}
