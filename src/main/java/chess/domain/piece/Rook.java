package chess.domain.piece;

import chess.domain.board.Position;

public class Rook extends Piece {
    private static final String NAME = "r";

    public Rook(TeamColor teamColor, Position position) {
        super(NAME, teamColor, position);
    }
}
