package chess.db.domain.piece;

import static chess.domain.piece.type.PieceType.KING;

import chess.domain.board.Board;
import chess.domain.piece.type.Direction;
import chess.domain.player.type.TeamColor;
import chess.domain.position.MoveRoute;
import chess.domain.position.Position;

public class KingEntity extends PieceEntity {
    private static final double SCORE = 0;

    public KingEntity(Long id, TeamColor teamColor) {
        super(id, KING, teamColor, SCORE, Direction.kingDirections());
    }

    public KingEntity(TeamColor teamColor) {
        super(KING, teamColor, SCORE, Direction.kingDirections());
    }

    @Override
    public boolean canMoveTo(MoveRoute moveRoute, Board board) {
        if (isNotCorrectDirection(moveRoute.direction())) {
            throw new IllegalArgumentException("이동할 수 없는 도착위치 입니다.");
        }
        Position nextPosition = moveRoute.nextPositionOfStartPosition();
        if (!(moveRoute.isDestination(nextPosition)
            && board.isCellEmptyOrEnemyExists(nextPosition, getTeamColor()))) {
            throw new IllegalArgumentException("이동할 수 없는 도착위치 입니다.");
        }
        return true;
    }
}
