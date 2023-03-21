package chess.model.board;

import chess.model.piece.Camp;
import chess.model.piece.Direction;
import chess.model.piece.Piece;
import chess.model.piece.type.Empty;
import chess.model.position.Distance;
import chess.model.position.Position;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> board;

    ChessBoard(final Map<Position, Piece> board) {
        this.board = board;
    }

    public void move(final Position source, final Position target, final Camp camp) {
        final Piece sourcePiece = board.getOrDefault(source, Empty.EMPTY_PIECE);

        validateSource(sourcePiece, camp);
        validateTotalWayPoint(source, target);

        final Piece targetPiece = board.getOrDefault(target, Empty.EMPTY_PIECE);
        final Distance distance = target.differ(source);

        validateMovable(sourcePiece, targetPiece, distance);
        updateBoard(source, sourcePiece, target);
    }

    private void validateSource(final Piece sourcePiece, final Camp camp) {
        if (!sourcePiece.isSameTeam(camp)) {
            throw new IllegalArgumentException("게임을 진행하는 플레이어의 기물이 아닙니다.");
        }
    }

    private void validateTotalWayPoint(final Position source, final Position target) {
        final Distance distance = target.differ(source);
        final Direction direction = distance.findDirection();

        findWayPoint(source, target, direction);
    }

    private void findWayPoint(final Position source, final Position target, final Direction direction) {
        Position wayPoint = source.findNextPosition(direction);
        source.findNextPosition(direction);

        while (!wayPoint.equals(target)) {
            validateTargetWayPoint(wayPoint);
            wayPoint = wayPoint.findNextPosition(direction);
        }
    }

    private void validateTargetWayPoint(final Position wayPoint) {
        final Piece wayPointPiece = board.getOrDefault(wayPoint, Empty.EMPTY_PIECE);

        if (wayPointPiece.isNotPassable()) {
            throw new IllegalArgumentException("해당 경로로 이동할 수 없습니다.");
        }
    }

    private void validateMovable(final Piece sourcePiece, final Piece targetPiece, final Distance distance) {
        if (!sourcePiece.movable(distance, targetPiece)) {
            throw new IllegalArgumentException("해당 기물은 지정한 목적지로 움직일 수 없습니다.");
        }
    }

    private void updateBoard(final Position source, final Piece sourcePiece, final Position target) {
        board.put(target, sourcePiece.pick());
        board.put(source, Empty.EMPTY_PIECE);
    }

    public Map<Position, Piece> getBoard() {
        return Map.copyOf(board);
    }
}
