package chess.domain;

import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.player.Player;
import chess.domain.point.Point;
import java.util.Map;

public class Board {

    private final Map<Point, Piece> board;

    public Board(Map<Point, Piece> board) {
        this.board = board;
    }

    public void move(Player player, Point currentPoint, Point destination) {
        Piece currentPiece = board.get(currentPoint);
        Piece targetPiece = board.get(destination);

        validateMove(player, currentPoint, destination, currentPiece, targetPiece);

        movePiece(currentPoint, destination, currentPiece);
    }

    private void movePiece(Point currentPoint, Point destination, Piece currentPiece) {
        board.put(currentPoint, Piece.empty());
        board.put(destination, currentPiece);
    }

    private void validateMove(Player player, Point currentPoint, Point destination, Piece currentPiece,
            Piece targetPiece) {
        validateMyPiece(player, currentPiece);
        validateMovablePoint(currentPoint, destination, currentPiece, targetPiece);
        validateDestination(player, destination);
        validateMovableRoute(currentPoint, destination);
    }

    private void validateMyPiece(Player player, Piece currentPiece) {
        if (!player.isMyPiece(currentPiece)) {
            throw new IllegalArgumentException("상대방의 기물을 움직일 수 없습니다.");
        }
    }

    private void validateMovablePoint(Point currentPoint, Point destination, Piece currentPiece, Piece targetPiece) {
        if (!currentPiece.isMovable(currentPoint, destination, targetPiece)) {
            throw new IllegalArgumentException("해당 기물이 이동할 수 있는 위치가 아닙니다.");
        }
    }

    private void validateDestination(Player player, Point destination) {
        Piece nextPiece = board.get(destination);
        if (!nextPiece.equals(Piece.empty()) && player.isMyPiece(nextPiece)) {
            throw new IllegalArgumentException("이동하려는 위치에 이미 자신의 기물이 있을 수 없습니다.");
        }
    }

    private void validateMovableRoute(Point currentPoint, Point destination) {
        Direction unitDirection = currentPoint.findUnitDirection(destination);
        Point nextPoint = currentPoint.add(unitDirection.file(), unitDirection.rank());
        while (!nextPoint.equals(destination)) {
            if (!Piece.empty().equals(board.get(nextPoint))) {
                throw new IllegalArgumentException("이동 경로에 기물이 존재하여 이동할 수 없습니다.");
            }
            nextPoint = nextPoint.add(unitDirection.file(), unitDirection.rank());
        }
    }

    public Map<Point, Piece> getBoard() {
        return board;
    }
}
