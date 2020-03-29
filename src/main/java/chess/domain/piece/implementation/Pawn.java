package chess.domain.piece.implementation;

import chess.domain.board.BoardState;
import chess.domain.direction.MovingDirection;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceState;
import chess.domain.piece.PieceType;
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
        super(PieceType.PAWN, position, team);
    }

    public static Pawn of(Position position, Team team) {
        return new Pawn(position, team);
    }

    @Override
    public List<Position> getMovablePositions(BoardState boardState) {
        List<Position> totalPositions = movePositions(boardState);
        totalPositions.addAll(attackPositions(boardState));
        return totalPositions;
    }

    private List<Position> movePositions(BoardState boardState) {
        MovingDirection moveDirection = MOVING_DIRECTION_BY_TEAM.get(team);
        List<Position> positions = new ArrayList<>();
        Position startPosition = position.moveByDirection(moveDirection);
        if (boardState.canMove(startPosition)) {
            positions.add(startPosition);
            checkOneStepMore(boardState, positions);
        }
        return positions;
    }

    private List<Position> attackPositions(BoardState boardState) {
        List<Position> attackPositions = new ArrayList<>();
        List<MovingDirection> attackDirections = ATTACK_DIRECTION_BY_TEAM.get(team);
        for (MovingDirection attackDirection : attackDirections) {
            canAttackBy(attackDirection, boardState, attackPositions);
        }
        return attackPositions;
    }

    private void checkOneStepMore(BoardState boardState, List<Position> positions) {
        if (isFistMove()) {
            moveOneStepMore(boardState, positions);
        }
    }

    private void moveOneStepMore(BoardState boardState, List<Position> positions) {
        MovingDirection movingDirection = MOVING_DIRECTION_BY_TEAM.get(team);
        Position twoStepMovedPosition = position.moveByDirection(movingDirection)
                .moveByDirection(movingDirection);
        if (boardState.canMove(twoStepMovedPosition)) {
            positions.add(twoStepMovedPosition);
        }
    }

    private void canAttackBy(MovingDirection attackDirection, BoardState boardState, List<Position> attackPositions) {
        Position startPosition = position;
        if (startPosition.canMoveBy(attackDirection)) {
            startPosition = startPosition.moveByDirection(attackDirection);
            addIfAttack(boardState, attackPositions, startPosition);
        }
    }

    private void addIfAttack(BoardState boardState, List<Position> attackPositions, Position startPosition) {
        if (boardState.canAttack(startPosition, team)) {
            attackPositions.add(startPosition);
        }
    }

    private boolean isFistMove() {
        Rank startRank = START_POINT_BY_TEAM.get(team);
        return position.isSameRank(startRank);
    }

    @Override
    protected PieceState movedPieceState(Position target) {
        return new Pawn(target, team);
    }
}
