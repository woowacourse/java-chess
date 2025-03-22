package chess.piece;

import static chess.board.Direction.DOWN;
import static chess.board.Direction.LEFT;
import static chess.board.Direction.RIGHT;
import static chess.board.Direction.UP;

import chess.board.ChessBoard;
import chess.board.Movement;
import chess.board.Position;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    private static final List<Movement> MOVABLE_MOVEMENTS = List.of(
            new Movement(List.of(UP, UP, LEFT)),
            new Movement(List.of(UP, UP, RIGHT)),
            new Movement(List.of(RIGHT, RIGHT, UP)),
            new Movement(List.of(RIGHT, RIGHT, DOWN)),
            new Movement(List.of(DOWN, DOWN, LEFT)),
            new Movement(List.of(DOWN, DOWN, RIGHT)),
            new Movement(List.of(LEFT, LEFT, UP)),
            new Movement(List.of(LEFT, LEFT, DOWN))
    );

    public Knight(Team team) {
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
        for (Movement movement : MOVABLE_MOVEMENTS) {
            if (!source.canMoveByDirections(movement)) {
                continue;
            }
            Position nextPosition = source.moveByDirections(movement);
            if (board.existsPiece(nextPosition)) {
                continue;
            }
            candidates.add(nextPosition);
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
        for (Movement movement : MOVABLE_MOVEMENTS) {
            if (!source.canMoveByDirections(movement)) {
                continue;
            }
            Position nextPosition = source.moveByDirections(movement);
            if (board.existsPiece(nextPosition) && board.hasProperTeam(nextPosition, this.team.inverse())) {
                candidates.add(nextPosition);
            }
        }
        return candidates;
    }

    @Override
    public PieceType type() {
        return PieceType.KNIGHT;
    }
}
