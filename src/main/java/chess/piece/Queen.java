package chess.piece;

import chess.board.ChessBoard;
import chess.board.Direction;
import chess.board.Position;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Queen extends Piece {

    private static final List<Direction> MOVABLE_DIRECTIONS = Arrays.stream(Direction.values()).toList();

    public Queen(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(Position source, Position destination, ChessBoard board) {
        if (!board.equalsByPosition(source, this)) {
            return false;
        }

        return findMovablePositions(source, board).contains(destination);
    }

    private List<Position> findMovablePositions(final Position source, final ChessBoard board) {
        List<Position> candidates = new ArrayList<>();
        MOVABLE_DIRECTIONS.forEach(direction ->
                findMovablePositionsByDirection(source, direction, board, candidates));
        return candidates;

    }

    private void findMovablePositionsByDirection(final Position currentPosition, final Direction direction,
                                                 final ChessBoard board, final List<Position> candidates) {
        if (!currentPosition.canMoveByDirection(direction)) {
            return;
        }
        Position nextPosition = currentPosition.moveByDirection(direction);
        // 기물이 있으면 더이상 전진할 수 없음
        if (board.existsPiece(nextPosition)) {
            return;
        }
        candidates.add(nextPosition);
        findMovablePositionsByDirection(nextPosition, direction, board, candidates);
    }

    @Override
    public boolean canAttack(Position source, Position destination, ChessBoard board) {
        if (!board.equalsByPosition(source, this)) {
            return false;
        }

        return findAttackablePositions(source, board).contains(destination);
    }

    private List<Position> findAttackablePositions(final Position source, final ChessBoard board) {
        List<Position> candidates = new ArrayList<>();
        MOVABLE_DIRECTIONS.forEach(direction ->
                findAttackablePositionsByDirection(source, direction, board, candidates));
        return candidates;

    }

    private void findAttackablePositionsByDirection(final Position currentPosition, final Direction direction,
                                                    final ChessBoard board, final List<Position> candidates) {
        if (!currentPosition.canMoveByDirection(direction)) {
            return;
        }
        Position nextPosition = currentPosition.moveByDirection(direction);

        // 적 기물 찾음 종료
        if (board.hasProperTeam(nextPosition, this.team.inverse())) {
            candidates.add(nextPosition);
            return;
        }

        // 우리 팀이 가로막고 있으면 못 지나감
        if (board.hasProperTeam(nextPosition, this.team)) {
            return;
        }

        // 빈칸이면 계속 전진해봄
        findAttackablePositionsByDirection(nextPosition, direction, board, candidates);
    }

    @Override
    public PieceType type() {
        return PieceType.QUEEN;
    }
}
