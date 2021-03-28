package chess.db.domain.piece;

import static chess.beforedb.domain.piece.type.PieceType.QUEEN;

import chess.beforedb.domain.piece.type.Direction;
import chess.beforedb.domain.player.type.TeamColor;

public class QueenEntity extends PieceEntity {
    private static final double SCORE = 9;

    public QueenEntity(Long id, TeamColor teamColor) {
        super(id, QUEEN, teamColor, SCORE, Direction.queenDirections());
    }

    public QueenEntity(TeamColor teamColor) {
        super(QUEEN, teamColor, SCORE, Direction.queenDirections());
    }
}
