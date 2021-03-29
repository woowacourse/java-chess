package chess.domain.piece;

import static chess.domain.piece.type.PieceType.QUEEN;

import chess.domain.piece.type.Direction;
import chess.domain.player.type.TeamColor;

public class QueenEntity extends PieceEntity {
    private static final double SCORE = 9;

    public QueenEntity(Long id, TeamColor teamColor) {
        super(id, QUEEN, teamColor, SCORE, Direction.queenDirections());
    }

    public QueenEntity(TeamColor teamColor) {
        super(QUEEN, teamColor, SCORE, Direction.queenDirections());
    }
}
