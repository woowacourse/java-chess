package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.player.TeamType;

public class Pawn extends Piece {
    private static final String NAME = "P";

    public Pawn(TeamType teamType) {
        super(teamType, NAME);
    }

    @Override
    public void move(Board board, Coordinate currentCoordinate, Coordinate coordinate) {

    }
}
