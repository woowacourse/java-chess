package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.player.TeamType;
import java.util.List;

public class Pawn extends Piece {
    private static final String NAME = "P";

    public Pawn(TeamType teamType) {
        super(teamType, NAME, null);
    }

    @Override
    public boolean isMovableTo(Board board, Coordinate currentCoordinate, Coordinate targetCoordinate) {
        return true;
    }
}
