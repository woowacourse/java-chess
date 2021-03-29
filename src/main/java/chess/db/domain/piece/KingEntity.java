package chess.db.domain.piece;

import static chess.beforedb.domain.piece.type.PieceType.KING;

import chess.beforedb.domain.piece.type.Direction;
import chess.beforedb.domain.player.type.TeamColor;
import chess.db.domain.board.BoardForDB;
import chess.db.domain.position.MoveRouteForDB;
import chess.db.domain.position.PositionEntity;

public class KingEntity extends PieceEntity {
    private static final double SCORE = 0;

    public KingEntity(Long id, TeamColor teamColor) {
        super(id, KING, teamColor, SCORE, Direction.kingDirections());
    }

    public KingEntity(TeamColor teamColor) {
        super(KING, teamColor, SCORE, Direction.kingDirections());
    }
}
