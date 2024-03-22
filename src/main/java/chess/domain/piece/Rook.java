package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;

import java.util.List;

public class Rook extends Piece {
    public Rook(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(Position start, Position destination, ChessBoard board) {
        return start.isStraightWith(destination)
                && board.pathIsAllEmpty(searchPath(start, destination));
    }

    private List<Position> searchPath(Position start, Position destination) {
        return start.straightPath(destination);
    }
}
