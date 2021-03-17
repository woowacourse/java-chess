package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.Position;

import java.util.List;

public class Queen extends Piece {
    public Queen(final String team) {
        super(team);
    }

    @Override
    public boolean isMovable(final Position current, final Position destination, final ChessBoard chessBoard) {
        if (!checkPositionRule(current, destination)) {
            return false;
        }
        if (checkDiagonalRule(current, destination)) {
            final List<Position> diagonalPath = current.generateDiagonalPath(destination);
            return checkEmptyPath(diagonalPath, chessBoard);
        }
        if (checkStraightRule(current, destination)) {
            System.out.println("여기로 와야해");
            final List<Position> straightPath = current.generateStraightPath(destination);
            return checkEmptyPath(straightPath, chessBoard);
        }
        return false;
    }

    @Override
    boolean checkPositionRule(Position current, Position destination) {
        return checkDiagonalRule(current, destination) || checkStraightRule(current, destination);
    }
}