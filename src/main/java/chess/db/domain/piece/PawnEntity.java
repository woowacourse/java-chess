package chess.db.domain.piece;

import static chess.beforedb.domain.piece.type.PieceType.PAWN;

import chess.beforedb.domain.piece.type.Direction;
import chess.beforedb.domain.player.type.TeamColor;
import chess.db.domain.board.BoardForDB;
import chess.db.domain.position.MoveRouteForDB;
import chess.db.domain.position.PositionEntity;

public class PawnEntity extends PieceEntity {
    private static final double DEFAULT_SCORE = 1;
    private static final double HALF_SCORE = DEFAULT_SCORE / 2;

    public PawnEntity(Long id, TeamColor teamColor) {
        super(id, PAWN, teamColor, DEFAULT_SCORE, Direction.pawnDirections(teamColor));
    }

    public PawnEntity(TeamColor teamColor) {
        super(PAWN, teamColor, DEFAULT_SCORE, Direction.pawnDirections(teamColor));
    }

    @Override
    public boolean canMoveTo(MoveRouteForDB moveRouteForDB, BoardForDB boardForDB) {
        Direction moveDirection = moveRouteForDB.getDirection();
        if (isNotCorrectDirection(moveDirection)) {
            throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
        }
        if (moveDirection.isForward()) {
            return canMoveForward(moveRouteForDB, boardForDB);
        }
        return canMoveDiagonal(moveRouteForDB, boardForDB);
    }

    private boolean canMoveForward(MoveRouteForDB moveRouteForDB, BoardForDB boardForDB) {
        if (moveRouteForDB.isRankForwardedBy(2)) {
            return canMoveTwoRankForward(moveRouteForDB, boardForDB);
        }
        if (moveRouteForDB.isRankForwardedBy(1)) {
            return canMoveOneRankForward(moveRouteForDB, boardForDB);
        }
        throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
    }


    private boolean canMoveTwoRankForward(MoveRouteForDB moveRouteForDB, BoardForDB boardForDB) {
        if (!moveRouteForDB.isStartPositionFirstPawnPosition(getTeamColor())) {
            throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
        }
        if (boardForDB.isAnyPieceExistsOnRouteBeforeDestination(moveRouteForDB)
            || boardForDB.isAnyPieceExistsInCell(moveRouteForDB.getDestination())) {
            throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
        }
        return true;
    }

    private boolean canMoveOneRankForward(MoveRouteForDB moveRouteForDB, BoardForDB boardForDB) {
        if (boardForDB.isAnyPieceExistsInCell(moveRouteForDB.getDestination())) {
            throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
        }
        return true;
    }

    private boolean canMoveDiagonal(MoveRouteForDB moveRouteForDB, BoardForDB boardForDB) {
        PositionEntity nextPosition = moveRouteForDB.getNextPositionOfStartPosition();
        if (!(boardForDB.isEnemyExists(moveRouteForDB.getDestination(), getTeamColor())
            && nextPosition.equals(moveRouteForDB.getDestination()))) {
            throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
        }
        return true;
    }

    public static double defaultScore() {
        return DEFAULT_SCORE;
    }

    public static double halfScore() {
        return HALF_SCORE;
    }
}
