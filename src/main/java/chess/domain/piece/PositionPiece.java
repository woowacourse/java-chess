package chess.domain.piece;

import chess.domain.board.BoardMap;
import chess.domain.exception.IllegalMoveException;
import chess.domain.move.Move;
import chess.domain.position.Position;

public class PositionPiece {

    private Position position;
    private final Piece moves;

    public PositionPiece(Position position, Piece moves) {
        this.position = position;
        this.moves = moves;
    }

    public void moveTo(Position target, BoardMap map) {
        validateNotSameTeam(target, map);
        validateMove(target, map);
        validateNoObstacles(target, map);
        this.position = target;
    }

    private void validateNotSameTeam(Position target, BoardMap map) {
        if (map.isTeamSame(position, target)) {
            throw new IllegalMoveException("목표 위치에 같은 색 말이 있습니다");
        }
    }

    private void validateMove(Position target, BoardMap map) {
        if (!hasMove(target, map)) {
            throw new IllegalMoveException("해당 기물이 이동할 수 없는 수입니다");
        }
    }

    private boolean hasMove(Position target, BoardMap map) {
        Move move = Move.of(position, target);
        if (map.hasPieceAt(target)) {
            return moves.hasAttackMove(move);
        }
        return moves.hasMove(move);
    }

    private void validateNoObstacles(Position target, BoardMap map) {
        Move unitMove = Move.of(position, target).getUnitMove();
        Position current = unitMove.move(position);
        while (!current.equals(target)) {
            validateNoPieceAt(current, map);
            current = unitMove.move(current);
        }
    }

    private void validateNoPieceAt(Position position, BoardMap map) {
        if (map.hasPieceAt(position)) {
            throw new IllegalMoveException("다른 기물을 지나칠 수 없습니다");
        }
    }

    public Position getPosition() {
        return position;
    }
}
