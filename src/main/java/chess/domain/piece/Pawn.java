package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.feature.Color;
import chess.domain.feature.Type;

import java.util.List;

public class Pawn extends Piece {
    public Pawn(Color color, Position position) {
        super(color, position);
        this.type = Type.PAWN;
    }

    @Override
    public List<Direction> directions() {
        if (isBlack()) {
            return Direction.blackPawnDirection();
        }
        return Direction.whitePawnDirection();
    }

    @Override
    public boolean isMovable(ChessBoard chessBoard, Direction direction, Position targetPosition) {
        if (direction.isLinear()) {
            return isLinearMovable(chessBoard, direction, targetPosition);
        }
        return isDiagonalMovable(chessBoard, direction, targetPosition);
    }

    private boolean isLinearMovable(ChessBoard chessBoard, Direction direction, Position targetPosition) {
        Position nextPosition = this.nextPosition(direction);
        Piece nextPiece = chessBoard.getPiece(nextPosition);
        if (nextPosition.equals(targetPosition) && nextPiece.isBlank()) {
            return true;
        }
        Position twoStepNextPosition = nextPosition.nextPosition(direction);
        Piece twoStepNextPiece = chessBoard.getPiece(twoStepNextPosition);
        return twoStepNextPosition.equals(targetPosition) && twoStepNextPiece.isBlank() && nextPiece.isBlank();
    }

    private boolean isDiagonalMovable(ChessBoard chessBoard, Direction direction, Position targetPosition) {
        Position nextPosition = this.nextPosition(direction);
        Piece nextPiece = chessBoard.getPiece(nextPosition);
        return nextPosition.equals(targetPosition) && !nextPiece.isBlank() && this.isNotAlly(nextPiece);
    }
}
