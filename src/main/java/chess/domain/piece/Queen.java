package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;
import java.util.List;

public class Queen extends SlidingPiece {
    public Queen(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(Position start, Position destination, ChessBoard chessBoard) {
        if (isNotReachable(start, destination)) {
            return false;
        }
        if (isPathNotClear(start, destination, chessBoard)) {
            return false;
        }
        if (isFriendlyPieceAtDestination(destination, chessBoard)) {
            return false;
        }
        return true;
    }

    boolean isNotReachable(Position start, Position destination) {
        return !start.isOrthogonalWith(destination) && !start.isDiagonalWith(destination);
    }

    boolean isPathNotClear(Position start, Position destination, ChessBoard chessBoard) {
        return !chessBoard.isPathClear(start.calculateSlidingPath(destination));
    }

    boolean isFriendlyPieceAtDestination(Position destination, ChessBoard chessBoard) {
        if (chessBoard.positionIsEmpty(destination)) {
            return false;
        }
        if (chessBoard.findPieceByPosition(destination).isSameTeam(this)) {
            return false;
        }
        return true;
    }

    @Override
    public List<Position> searchPath(Position start, Position destination) {
        return start.calculateSlidingPath(destination);
    }
}
