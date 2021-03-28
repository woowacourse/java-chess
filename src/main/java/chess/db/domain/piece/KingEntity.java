package chess.db.domain.piece;

import static chess.beforedb.domain.piece.type.PieceType.KING;

import chess.db.domain.board.BoardForDB;
import chess.db.domain.position.MoveRouteForDB;
import chess.db.domain.position.PositionEntity;
import chess.beforedb.domain.piece.type.Direction;
import chess.beforedb.domain.player.type.TeamColor;

public class KingEntity extends PieceEntity {
    private static final double SCORE = 0;

    public KingEntity(Long id, TeamColor teamColor) {
        super(id, KING, teamColor, SCORE, Direction.kingDirections());
    }

    public KingEntity(TeamColor teamColor) {
        super(KING, teamColor, SCORE, Direction.kingDirections());
    }

    @Override
    public boolean canMoveTo(MoveRouteForDB moveRouteForDB, BoardForDB boardForDB) {
        if (isNotCorrectDirection(moveRouteForDB.getDirection())) {
            throw new IllegalArgumentException("이동할 수 없는 도착위치 입니다.");
        }
        PositionEntity nextPosition = moveRouteForDB.getNextPositionOfStartPosition();
        if (!(moveRouteForDB.isDestination(nextPosition)
            && boardForDB.isCellEmptyOrEnemyExists(nextPosition, getTeamColor()))) {
            throw new IllegalArgumentException("이동할 수 없는 도착위치 입니다.");
        }
        return true;
    }
}
