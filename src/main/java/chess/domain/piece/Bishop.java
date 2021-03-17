package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.Position;

import java.util.List;

public class Bishop extends Piece {
    public Bishop(final String team) {
        super(team);
    }

    @Override
    public boolean isMovable(Position current, Position destination, ChessBoard chessBoard) {
        if (!checkPositionRule(current, destination)) {
            return false;
        }
        final List<Position> diagonalPath = current.generateDiagonalPath(destination);
        return checkEmptyPath(diagonalPath, chessBoard);
    }

    @Override
    public boolean checkPositionRule(Position current, Position destination) {
        return checkDiagonalRule(current, destination);
    }
}
