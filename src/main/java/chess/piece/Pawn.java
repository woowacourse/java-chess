package chess.piece;

import static chess.board.Direction.DOWN;
import static chess.board.Direction.DOWN_LEFT;
import static chess.board.Direction.DOWN_RIGHT;
import static chess.board.Direction.UP;
import static chess.board.Direction.UP_LEFT;
import static chess.board.Direction.UP_RIGHT;

import chess.board.ChessBoard;
import chess.board.Direction;
import chess.board.Movement;
import chess.board.Position;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {


    private static final Movement BLACK_FIRST_MOVEMENT = new Movement(List.of(UP, UP));
    private static final List<Direction> BLACK_MOVABLE_DIRECTIONS = List.of(UP);
    private static final List<Direction> BLACK_ATTACKABLE_DIRECTIONS = List.of(UP_LEFT, UP_RIGHT);

    private static final Movement WHITE_FIRST_MOVEMENT = new Movement(List.of(DOWN, DOWN));
    private static final List<Direction> WHITE_MOVABLE_DIRECTIONS = List.of(DOWN);
    private static final List<Direction> WHITE_ATTACKABLE_DIRECTIONS = List.of(DOWN_LEFT, DOWN_RIGHT);

    public Pawn(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Position source, final Position destination, final ChessBoard board) {
        if (!board.equalsByPosition(source, this)) {
            return false;
        }

        return findMovablePositions(source, board).contains(destination);
    }

    private List<Position> findMovablePositions(final Position source, final ChessBoard board) {
        List<Position> candidates = new ArrayList<>();
        switch (this.team) {
            case BLACK -> {
                checkAndAddWhenFirstMove(source, BLACK_FIRST_MOVEMENT, board, candidates);
                findNormalMovablePositions(candidates, BLACK_MOVABLE_DIRECTIONS, source, board);
            }
            case WHITE -> {
                checkAndAddWhenFirstMove(source, WHITE_FIRST_MOVEMENT, board, candidates);
                findNormalMovablePositions(candidates, WHITE_MOVABLE_DIRECTIONS, source, board);
            }
        }
        return candidates;
    }

    private void checkAndAddWhenFirstMove(final Position source, final Movement blackFirstMovement,
                                          final ChessBoard board, final List<Position> candidates) {
        if (isFirstMove && source.canMoveByDirections(blackFirstMovement)
                && board.existsPieceInPath(source, blackFirstMovement)) {
            Position nextPosition = source.moveByDirections(blackFirstMovement);
            candidates.add(nextPosition);
        }
    }

    private void findNormalMovablePositions(final List<Position> candidates,
                                            final List<Direction> blackMovableDirections,
                                            final Position source, final ChessBoard board) {
        candidates.addAll(blackMovableDirections
                .stream()
                .map(source::moveByDirection)
                .filter(position -> !board.existsPiece(position))
                .toList());
    }

    @Override
    public boolean canAttack(final Position source, final Position destination, final ChessBoard board) {
        if (!board.equalsByPosition(source, this)) {
            return false;
        }

        return findAttackablePositions(source, board).contains(destination);
    }

    private List<Position> findAttackablePositions(final Position source, final ChessBoard board) {
        List<Position> candidates = new ArrayList<>();
        switch (this.team) {
            case BLACK -> findAttackablePositions(candidates, BLACK_ATTACKABLE_DIRECTIONS, source, board);
            case WHITE -> findAttackablePositions(candidates, WHITE_ATTACKABLE_DIRECTIONS, source, board);
        }
        return candidates;
    }

    private void findAttackablePositions(final List<Position> candidates,
                                         final List<Direction> blackAttackableDirections,
                                         final Position source, final ChessBoard board) {
        candidates.addAll(blackAttackableDirections
                .stream()
                .map(source::moveByDirection)
                .filter(position
                        -> board.existsPiece(position) && board.hasProperTeam(position, this.team.inverse()))
                .toList());
    }

    @Override
    public PieceType type() {
        return PieceType.PAWN;
    }
}
