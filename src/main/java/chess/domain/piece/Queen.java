package chess.domain.piece;

import chess.domain.board.Position;

public class Queen extends Piece {
    private static final String NAME = "q";

    public Queen(TeamColor teamColor, Position position) {
        super(NAME, teamColor, position);
    }
}
