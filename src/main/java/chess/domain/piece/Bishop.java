package chess.domain.piece;

import chess.domain.board.Position;

public class Bishop extends Piece {
    private static final String NAME = "b";

    public Bishop(TeamColor teamColor, Position position) {
        super(NAME, teamColor, position);
    }
}
