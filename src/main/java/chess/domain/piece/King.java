package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;

public class King extends Piece {
    private static final int STRAIGHT_DISTANCE = 1;
    private static final int DIAGONAL_DISTANCE = 2;

    public King(Team team) {
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
        int squaredDistance = start.squaredDistanceWith(destination);
        return !(squaredDistance == STRAIGHT_DISTANCE || squaredDistance == DIAGONAL_DISTANCE);
    }

    boolean isPathNotClear(Position start, Position destination, ChessBoard chessBoard) {
        return false;
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
}
