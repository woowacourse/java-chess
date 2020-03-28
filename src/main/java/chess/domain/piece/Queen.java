package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;

import java.util.List;

public class Queen extends Piece {
    public Queen(char representation, Team team, Position position) {
        super(representation, team, position);
    }

    @Override
    public List<Position> getPossiblePositions(Board board) {
        return null;
    }
}
