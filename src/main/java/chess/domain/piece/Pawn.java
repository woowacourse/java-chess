package chess.domain.piece;

import chess.domain.board.Position;

public class Pawn extends Piece {
    private static final String NAME = "p";

    public Pawn(TeamColor teamColor, Position position) {
        super(NAME, teamColor, position);
    }
}
