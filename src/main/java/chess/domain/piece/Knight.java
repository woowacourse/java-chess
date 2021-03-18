package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.player.TeamType;

public class Knight extends Piece {
    private static final String NAME = "N";

    public Knight(TeamType teamType) {
        super(teamType, NAME);
    }

    @Override
    public void move(Board board, Coordinate currentCoordinate, Coordinate coordinate) {

    }
}
