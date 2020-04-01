package chess.domain.piece.implementation.piece;

import chess.domain.board.BoardSituation;
import chess.domain.direction.MovingDirection;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceState;
import chess.domain.piece.PieceType;
import chess.domain.piece.implementation.Strategy.PawnStrategy;
import chess.domain.player.Team;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public class Pawn extends Piece {

    private static final EnumMap<Team, MovingDirection> MOVING_DIRECTION_BY_TEAM = new EnumMap<>(Team.class);
    private static final EnumMap<Team, List<MovingDirection>> ATTACK_DIRECTION_BY_TEAM = new EnumMap<>(Team.class);
    private static final EnumMap<Team, Rank> START_POINT_BY_TEAM = new EnumMap<>(Team.class);

    static {
        MOVING_DIRECTION_BY_TEAM.put(Team.WHITE, MovingDirection.NORTH);
        MOVING_DIRECTION_BY_TEAM.put(Team.BLACK, MovingDirection.SOUTH);

        List<MovingDirection> whiteAttacks = Arrays.asList(MovingDirection.NORTH_EAST, MovingDirection.NORTH_WEST);
        List<MovingDirection> blackAttacks = Arrays.asList(MovingDirection.SOUTH_EAST, MovingDirection.SOUTH_WEST);
        ATTACK_DIRECTION_BY_TEAM.put(Team.WHITE, whiteAttacks);
        ATTACK_DIRECTION_BY_TEAM.put(Team.BLACK, blackAttacks);

        START_POINT_BY_TEAM.put(Team.WHITE, Rank.TWO);
        START_POINT_BY_TEAM.put(Team.BLACK, Rank.SEVEN);
    }

    private Pawn(Position position, Team team) {
        super(new PawnStrategy(team), PieceType.PAWN, position, team);
    }

    public static Pawn of(Position position, Team team) {
        return new Pawn(position, team);
    }

    @Override
    public List<Position> getMovablePositions(BoardSituation boardSituation) {
        List<Position> totalPositions = movePositions(boardSituation);
        totalPositions.addAll(attackPositions(boardSituation));
        return totalPositions;
    }

    private List<Position> movePositions(BoardSituation boardSituation) {
        MovingDirection moveDirection = MOVING_DIRECTION_BY_TEAM.get(team);
        List<Position> positions = new ArrayList<>();
        Position startPosition = position.moveByDirection(moveDirection);
        if (boardSituation.canMove(startPosition)) {
            positions.add(startPosition);
            checkOneStepMore(boardSituation, positions);
        }
        return positions;
    }

    private List<Position> attackPositions(BoardSituation boardSituation) {
        List<Position> attackPositions = new ArrayList<>();
        List<MovingDirection> attackDirections = ATTACK_DIRECTION_BY_TEAM.get(team);
        for (MovingDirection attackDirection : attackDirections) {
            canAttackBy(attackDirection, boardSituation, attackPositions);
        }
        return attackPositions;
    }

    private void checkOneStepMore(BoardSituation boardSituation, List<Position> positions) {
        if (isFistMove()) {
            moveOneStepMore(boardSituation, positions);
        }
    }

    private void moveOneStepMore(BoardSituation boardSituation, List<Position> positions) {
        MovingDirection movingDirection = MOVING_DIRECTION_BY_TEAM.get(team);
        Position twoStepMovedPosition = position.moveByDirection(movingDirection)
                .moveByDirection(movingDirection);
        if (boardSituation.canMove(twoStepMovedPosition)) {
            positions.add(twoStepMovedPosition);
        }
    }

    private void canAttackBy(MovingDirection attackDirection, BoardSituation boardSituation, List<Position> attackPositions) {
        Position startPosition = position;
        if (startPosition.canMoveBy(attackDirection)) {
            startPosition = startPosition.moveByDirection(attackDirection);
            addIfAttack(boardSituation, attackPositions, startPosition);
        }
    }

    private void addIfAttack(BoardSituation boardSituation, List<Position> attackPositions, Position startPosition) {
        if (boardSituation.canAttack(startPosition, team)) {
            attackPositions.add(startPosition);
        }
    }

    private boolean isFistMove() {
        Rank startRank = START_POINT_BY_TEAM.get(team);
        return position.isSameRank(startRank);
    }

    @Override
    public double getPoint(BoardSituation boardSituation) {
        if (boardSituation.existSamePieceInSameFile(position, team)) {
            return pieceType.getPoint() / 2;
        }
        return pieceType.getPoint();
    }

    @Override
    protected PieceState movedPieceState(Position target) {
        return new Pawn(target, team);
    }
}
