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
    public boolean canMove(Position source, Position destination, ChessBoard board) {
        if (!board.equalsByPosition(source, this)) {
            return false;
        }

        return findMovablePositions(source, board).contains(destination);
    }

    private List<Position> findMovablePositions(Position source, ChessBoard board) {
        List<Position> candidates = new ArrayList<>();
        switch (this.team) {
            case BLACK -> {
                if (isFirstMove && source.canMoveByDirections(BLACK_FIRST_MOVEMENT)
                        && board.existsPieceInPath(source, BLACK_FIRST_MOVEMENT)) {
                    Position nextPosition = source.moveByDirections(BLACK_FIRST_MOVEMENT);
                    candidates.add(nextPosition);
                }

                candidates.addAll(BLACK_MOVABLE_DIRECTIONS
                        .stream()
                        .map(source::moveByDirection)
                        .filter(position -> !board.existsPiece(position))
                        .toList());
            }
            case WHITE -> {
                if (isFirstMove && source.canMoveByDirections(WHITE_FIRST_MOVEMENT)
                        && board.existsPieceInPath(source, WHITE_FIRST_MOVEMENT)) {
                    Position nextPosition = source.moveByDirections(WHITE_FIRST_MOVEMENT);
                    candidates.add(nextPosition);
                }

                candidates.addAll(WHITE_MOVABLE_DIRECTIONS
                        .stream()
                        .map(source::moveByDirection)
                        .filter(position -> !board.existsPiece(position))
                        .toList());
            }
        }
        return candidates;
    }

    @Override
    public boolean canAttack(Position source, Position destination, ChessBoard board) {
        if (!board.equalsByPosition(source, this)) {
            return false;
        }

        return findAttackablePositions(source, board).contains(destination);
    }

    private List<Position> findAttackablePositions(Position source, ChessBoard board) {
        List<Position> candidates = new ArrayList<>();
        switch (this.team) {
            case BLACK -> {
                candidates.addAll(BLACK_ATTACKABLE_DIRECTIONS
                        .stream()
                        .map(source::moveByDirection)
                        .filter(position -> board.existsPiece(position) && board.hasProperTeam(position,
                                this.team.inverse()))
                        .toList());
            }
            case WHITE -> {
                candidates.addAll(WHITE_ATTACKABLE_DIRECTIONS
                        .stream()
                        .map(source::moveByDirection)
                        .filter(position -> board.existsPiece(position) && board.hasProperTeam(position,
                                this.team.inverse()))
                        .toList());
            }
        }
        return candidates;
    }

    @Override
    public PieceType type() {
        return PieceType.PAWN;
    }
}
