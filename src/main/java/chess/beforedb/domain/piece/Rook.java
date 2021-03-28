package chess.beforedb.domain.piece;

import static chess.beforedb.domain.piece.type.PieceType.ROOK;

import chess.beforedb.domain.piece.type.Direction;
import chess.beforedb.domain.player.type.TeamColor;

public class Rook extends Piece {
    private static final double SCORE = 5;

    public Rook(TeamColor teamColor) {
        super(ROOK, teamColor, SCORE, Direction.rookDirections());
    }
}
