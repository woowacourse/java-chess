package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.player.TeamType;

public class King extends Piece {
    private static final String NAME = "K";

    public King(TeamType teamType) {
        super(teamType, NAME);
    }

    @Override
    public void move(Board board, Coordinate currentCoordinate, Coordinate coordinate) {

    }
}
