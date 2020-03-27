package chess.domain.piece;

import chess.domain.board.Position;

public class EmptyPiece extends Piece {
    private static final String NAME = ".";

    public EmptyPiece(TeamColor teamColor, Position position) {
        super(NAME, teamColor, position);
    }
}
