package chess.beforedb.domain.piece;

import static chess.beforedb.domain.piece.type.PieceType.BISHOP;

import chess.beforedb.domain.piece.type.Direction;
import chess.beforedb.domain.player.type.TeamColor;

public class Bishop extends Piece {
    private static final double SCORE = 3;

    public Bishop(TeamColor teamColor) {
        super(BISHOP, teamColor, SCORE, Direction.bishopDirections());
    }
}
