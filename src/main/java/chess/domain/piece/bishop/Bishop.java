package chess.domain.piece.bishop;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.move.CanNotMoveStrategy;
import chess.domain.piece.state.Initialized;
import chess.domain.piece.team.Team;
import chess.domain.position.Direction;
import chess.domain.position.Distance;
import chess.domain.position.Position;

import java.util.List;

public class Bishop extends Initialized {

    public Bishop(String name, Position position, Team team, List<CanNotMoveStrategy> canNotMoveStrategies) {
        super(name, position, team, canNotMoveStrategies);
    }

    @Override
    public Piece move(Position to, Board board) {
        if (canNotMove(to, board)) {
            throw new IllegalArgumentException(String.format("%s 위치의 말을 %s 위치로 옮길 수 없습니다.", position, to));
        }
        return new Bishop(name, to, team, canNotMoveStrategies);
    }

    @Override
    public boolean hasHindrance(Position to, Board board) {
        if (position.isNotStraightDiagonalDirection(to)) {
            return true;
        }

        return hasHindranceInBetween(to, board);
    }

    private boolean hasHindranceInBetween(Position to, Board board) {
        Distance amount = position.calculateHorizontalDistance(to);
        Direction direction = position.calculateDirection(to);
        Position targetPosition = position;
        for (int i = 1; i < amount.getValue(); i++) {
            targetPosition = targetPosition.go(direction);
            Piece piece = board.getPiece(targetPosition);
            if (piece.isNotBlank()) {
                return true;
            }
        }

        return false;
    }
}
