package chess.domain;

import chess.domain.piece.Direction;
import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.player.Player;
import java.util.Map;

public class Board {

    private final Map<Point, Piece> board;

    public Board(Map<Point, Piece> board) {
        this.board = board;
    }

    public void move(Player player, Point currentPoint, Point destination) {
        Piece currentPiece = board.get(currentPoint);

        validateMove(player, currentPoint, destination, currentPiece);

        movePiece(currentPoint, destination, currentPiece);
    }

    private void movePiece(Point currentPoint, Point destination, Piece currentPiece) {
        board.put(currentPoint, new Empty(Team.EMPTY));
        board.put(destination, currentPiece);
    }

    private void validateMove(Player player, Point currentPoint, Point destination, Piece currentPiece) {
        validateMyPiece(player, currentPiece);
        validateMovablePoint(currentPoint, destination, currentPiece);
        validateDestination(player, destination);
        validateMovableRoute(currentPoint, destination);
        validatePawn(currentPoint, destination, currentPiece);
    }

    private void validateMyPiece(Player player, Piece currentPiece) {
        if (!player.isMyPiece(currentPiece)) {
            throw new IllegalArgumentException("상대방의 기물을 움직일 수 없습니다.");
        }
    }

    private void validateMovablePoint(Point currentPoint, Point destination, Piece currentPiece) {
        if (!currentPiece.isMovable(currentPoint, destination)) {
            throw new IllegalArgumentException("해당 기물이 이동할 수 있는 위치가 아닙니다.");
        }
    }

    private void validateDestination(Player player, Point destination) {
        Piece nextPiece = board.get(destination);
        if (!nextPiece.equals(new Empty(Team.EMPTY)) && player.isMyPiece(nextPiece)) {
            throw new IllegalArgumentException("이동하려는 위치에 이미 자신의 기물이 있을 수 없습니다.");
        }
    }

    private void validateMovableRoute(Point currentPoint, Point destination) {
        Direction route = currentPoint.findRoute(destination);
        Point nextPoint = currentPoint.add(route.file(), route.rank());
        while (!nextPoint.equals(destination)) {
            if (!board.get(nextPoint).equals(new Empty(Team.EMPTY))) {
                throw new IllegalArgumentException("이동 경로에 기물이 존재하여 이동할 수 없습니다.");
            }
            nextPoint = nextPoint.add(route.file(), route.rank());
        }
    }

    // TODO 리팩터링
    private void validatePawn(Point currentPoint, Point destination, Piece currentPiece) {
        if (currentPiece instanceof Pawn) {
            if (currentPoint.isDiagonal(destination)) {
                if (board.get(destination).equals(new Empty(Team.EMPTY))) {
                    throw new IllegalArgumentException("폰은 상대방의 기물이 대각선에 위치한 경우만 이동할 수 있습니다.");
                }
            }
            if (currentPoint.isStraight(destination)) {
                if (!board.get(destination).equals(new Empty(Team.EMPTY))) {
                    throw new IllegalArgumentException("폰의 이동 경로에 기물이 존재하여 이동할 수 없습니다.");
                }
            }
        }
    }

    public Map<Point, Piece> getBoard() {
        return board;
    }
}
