package chess.domain.piece;

import static chess.domain.piece.type.PieceType.QUEEN;

import chess.domain.piece.type.Direction;
import chess.domain.player.type.TeamColor;

public class Queen extends Piece {
    private static final double SCORE = 9;

    public Queen(Long id, TeamColor teamColor) {
        super(id, QUEEN, teamColor, SCORE, Direction.queenDirections());
    }

    public Queen(TeamColor teamColor) {
        super(QUEEN, teamColor, SCORE, Direction.queenDirections());
    }
}
