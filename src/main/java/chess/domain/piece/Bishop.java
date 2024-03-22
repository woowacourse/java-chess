package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;

import java.util.List;

public class Bishop extends Piece {
    public Bishop(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(Position start, Position destination, ChessBoard board) {
        return start.isDiagonalWith(destination)
                && board.pathIsAllEmpty(searchPath(start, destination));
    }

    private List<Position> searchPath(Position start, Position destination) {
        return start.diagonalPath(destination);
    }
}
