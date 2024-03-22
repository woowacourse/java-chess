package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;

import java.util.List;

public class Queen extends Piece {
    public Queen(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(Position start, Position destination, ChessBoard board) {
        return (start.isStraightWith(destination) || start.isDiagonalWith(destination))
                && board.pathIsAllEmpty(searchPath(start, destination));
    }

    private List<Position> searchPath(Position start, Position destination) {
        if (start.isDiagonalWith(destination)) {
            return start.diagonalPath(destination);
        }
        return start.straightPath(destination);
    }
}
