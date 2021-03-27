package chess.db.domain.piece;

import static chess.domain.piece.type.PieceType.PAWN;

import chess.domain.board.Board;
import chess.domain.piece.type.Direction;
import chess.domain.player.type.TeamColor;
import chess.domain.position.MoveRoute;
import chess.domain.position.Position;

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
    public boolean canMoveTo(MoveRoute moveRoute, Board board) {
        Direction moveDirection = moveRoute.direction();
        if (isNotCorrectDirection(moveDirection)) {
            throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
        }
        if (moveDirection.isForward()) {
            return canMoveForward(moveRoute, board);
        }
        return canMoveDiagonal(moveRoute, board);
    }

    private boolean canMoveForward(MoveRoute moveRoute, Board board) {
        if (moveRoute.isRankForwardedBy(2)) {
            return canMoveTwoRankForward(moveRoute, board);
        }
        if (moveRoute.isRankForwardedBy(1)) {
            return canMoveOneRankForward(moveRoute, board);
        }
        throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
    }


    private boolean canMoveTwoRankForward(MoveRoute moveRoute, Board board) {
        if (!moveRoute.isStartPositionFirstPawnPosition(getTeamColor())) {
            throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
        }
        if (board.isAnyPieceExistsOnRouteBeforeDestination(moveRoute)
            || board.isAnyPieceExistsInCell(moveRoute.destination())) {
            throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
        }
        return true;
    }

    private boolean canMoveOneRankForward(MoveRoute moveRoute, Board board) {
        if (board.isAnyPieceExistsInCell(moveRoute.destination())) {
            throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
        }
        return true;
    }

    private boolean canMoveDiagonal(MoveRoute moveRoute, Board board) {
        Position nextPosition = moveRoute.nextPositionOfStartPosition();
        if (!(board.isEnemyExists(moveRoute.destination(), getTeamColor())
            && nextPosition.equals(moveRoute.destination()))) {
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
