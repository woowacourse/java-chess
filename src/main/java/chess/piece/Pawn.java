package chess.piece;

import chess.board.ChessBoard;
import chess.board.Color;
import chess.board.Movement;
import chess.board.Position;
import java.util.List;

public class Pawn implements Piece {

    private boolean isFirstMove;
    private final Color color;

    public Pawn(Color color) {
        this.color = color;
        isFirstMove = true;
    }

    @Override
    public boolean canMoveToDestination(ChessBoard chessBoard, Position start, Position end) {
        if (color == Color.WHITE) {
            return canMoveToDestinationWhitePawn(chessBoard, start, end);
        }
        return canMoveToDestinationBlackPawn(chessBoard, start, end);
    }

    private boolean canMoveToDestinationBlackPawn(ChessBoard chessBoard, Position start, Position end) {
        if (start.move(Movement.DOWN_DOWN).equals(end) && isFirstMove) {
            isFirstMove = false;
            return true;
        }
        if (start.move(Movement.DOWN).equals(end) && chessBoard.isEmpty(end)) {
            isFirstMove = false;
            return true;
        }
        if(canMove(start, end, List.of(Movement.LEFT_DOWN, Movement.RIGHT_DOWN)) && chessBoard.isOppositeColor(end, Color.BLACK)) {
            isFirstMove = false;
            return true;
        }
        return false;
    }

    private boolean canMoveToDestinationWhitePawn(ChessBoard chessBoard, Position start, Position end) {
        if (start.move(Movement.UP_UP).equals(end) && isFirstMove) {
            isFirstMove = false;
            return true;
        }
        if (start.move(Movement.UP).equals(end) && chessBoard.isEmpty(end)) {
            isFirstMove = false;
            return true;
        }
        if(canMove(start, end, List.of(Movement.LEFT_UP, Movement.RIGHT_UP)) && chessBoard.isOppositeColor(end, Color.WHITE)) {
            isFirstMove = false;
            return true;
        }
        return false;
    }

    private static boolean canMove(Position start, Position end, List<Movement> movements) {
        for (Movement movement : movements) {
            if(start.move(movement).equals(end)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isSameColor(Color color) {
        return this.color == color;
    }
}
