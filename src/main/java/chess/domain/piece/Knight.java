package chess.domain.piece;

import chess.domain.board.Position;

public class Knight extends Piece {
    private static final String NAME = "n";

    public Knight(TeamColor teamColor, Position position) {
        super(NAME, teamColor, position);
    }
}
