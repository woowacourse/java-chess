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
    public boolean canMove(final Position source, final Position destination, final ChessBoard board) {
        if (!board.equalsByPosition(source, this)) {
            return false;
        }

        return findMovablePositions(source, board).contains(destination);
    }

    private List<Position> findMovablePositions(final Position source, final ChessBoard board) {
        return MOVABLE_DIRECTIONS.stream()
                .map(source::moveByDirection)
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
        return MOVABLE_DIRECTIONS.stream()
                .map(source::moveByDirection)
                .filter(position -> board.existsPiece(position) && board.hasProperTeam(position, this.team.inverse()))
                .toList();
    }

    @Override
    public PieceType type() {
        return PieceType.KING;
    }
}
