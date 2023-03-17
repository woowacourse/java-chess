package chess.piece;

import chess.ChessBoard;
import chess.position.Position;
import chess.Side;

import java.util.ArrayList;
import java.util.List;

public class Queen extends ChessPiece{

    public Queen(Side side) {
        super(side);
    }

    @Override
    public List<Position> getMovablePosition(ChessBoard chessBoard, Position sourcePosition) {

        List<Position> movablePosition = new ArrayList<>();

        addEastPosition(movablePosition, chessBoard, sourcePosition);
//        addWestPosition(movablePosition, xPosition, yPosition);
//        addSouthPosition(movablePosition, xPosition, yPosition);
//        addNorthPosition(movablePosition, xPosition, yPosition);

        return movablePosition;
    }

    private static void addEastPosition(List<Position> movablePosition, ChessBoard chessBoard, Position sourcePosition) {
        int xPosition = sourcePosition.getXPosition();
        int yPosition = sourcePosition.getYPosition();

        for (int x = xPosition + 1; x <= 8; x++) {
            checkSameSidePiece(chessBoard, Position.initPosition(x, yPosition));
            movablePosition.add(Position.initPosition(x, yPosition));
        }
    }

    private static void checkSameSidePiece(ChessBoard chessBoard, Position position) {
//        if (this..equals(chessBoard.getChessPieceByPosition(position).getSide())) {
//        }
    }

//    private static void addWestPosition(List<Position> movablePosition, Position position) {
//        if (xPosition - 1 >= 0) {
//            movablePosition.add(Position.initPosition(xPosition - 1, yPosition));
//        }
//    }

    private static void addSouthPosition(List<Position> movablePosition, int xPosition, int yPosition) {
        if (yPosition - 1 >= 0) {
            movablePosition.add(Position.initPosition(xPosition, yPosition - 1));
        }
    }

    private static void addNorthPosition(List<Position> movablePosition, int xPosition, int yPosition) {
        if (yPosition + 1 <= 8) {
            movablePosition.add(Position.initPosition(xPosition, yPosition + 1));
        }
    }


}
