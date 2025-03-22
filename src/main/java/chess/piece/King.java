package chess.piece;

import chess.board.ChessBoard;
import chess.board.Direction;
import chess.board.Position;
import java.util.Arrays;
import java.util.List;

public class King extends Piece {

    private static final List<Direction> MOVABLE_DIRECTIONS = Arrays.stream(Direction.values()).toList();

    public King(Team team) {
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
        return MOVABLE_DIRECTIONS.stream()
                .map(source::moveByDirection)
                .filter(position -> !board.existsPiece(position))
                .toList();
    }

    @Override
    public boolean canAttack(Position source, Position destination, ChessBoard board) {
        if (!board.equalsByPosition(source, this)) {
            return false;
        }

        return findAttackablePositions(source, board).contains(destination);
    }

    private List<Position> findAttackablePositions(Position source, ChessBoard board) {
        return MOVABLE_DIRECTIONS.stream()
                .map(source::moveByDirection)
                .filter(position -> !board.existsPiece(position))
                .toList();
    }

    @Override
    public PieceType type() {
        return PieceType.KING;
    }
}
