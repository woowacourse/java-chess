package chess.piece;

import static chess.board.Direction.DOWN;
import static chess.board.Direction.LEFT;
import static chess.board.Direction.RIGHT;
import static chess.board.Direction.UP;

import chess.board.ChessBoard;
import chess.board.Movement;
import chess.board.Position;
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
    public boolean canMove(final Position source, final Position destination, final ChessBoard board) {
        if (!board.equalsByPosition(source, this)) {
            return false;
        }

        return findMovablePositions(source, board).contains(destination);
    }

    private List<Position> findMovablePositions(final Position source, final ChessBoard board) {
        return MOVABLE_MOVEMENTS.stream()
                .filter(source::canMoveByDirections)
                .map(source::moveByDirections)
                .filter(position -> !board.existsPiece(position))
                .toList();
    }

    @Override
    public boolean canAttack(final Position source, final Position destination, final ChessBoard board) {
        if (!board.equalsByPosition(source, this)) {
            return false;
        }

        return findAttackablePositions(source, board).contains(destination);
    }

    private List<Position> findAttackablePositions(final Position source, final ChessBoard board) {
        return MOVABLE_MOVEMENTS.stream()
                .filter(source::canMoveByDirections)
                .map(source::moveByDirections)
                .filter(position -> board.existsPiece(position) && board.hasProperTeam(position, this.team.inverse()))
                .toList();
    }

    @Override
    public PieceType type() {
        return PieceType.KNIGHT;
    }
}
