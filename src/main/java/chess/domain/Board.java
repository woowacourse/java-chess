package chess.domain;

import chess.domain.piece.Direction;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.player.Player;
import java.util.Map;

public class Board {

    private final Map<Point, Piece> board;

    public Board(Map<Point, Piece> board) {
        this.board = board;
    }

    public void move(Player player, Point currentPoint, Point destination) {
        Piece currentPiece = board.get(currentPoint);

        if (!player.isMyPiece(currentPiece)) {
            throw new IllegalArgumentException("상대방의 기물을 움직일 수 없습니다.");
        }

        Piece nextPiece = board.get(destination);
        if (nextPiece != null && player.isMyPiece(nextPiece)) {
            throw new IllegalArgumentException("이동하려는 위치에 이미 자신의 기물이 있을 수 없습니다.");
        }

        if (!currentPiece.isMovable(currentPoint, destination)) {
            throw new IllegalArgumentException("해당 기물이 이동할 수 있는 위치가 아닙니다.");
        }

        Direction route = currentPoint.findRoute(destination);
        Point nextPoint = currentPoint.add(route.file(), route.rank()).get();
        while (!nextPoint.equals(destination)) {
            // TODO: get 삭제
            if (board.get(nextPoint) != null) {
                throw new IllegalArgumentException("이동 경로에 기물이 존재하여 이동할 수 없습니다.");
            }
            nextPoint = nextPoint.add(route.file(), route.rank()).get();
        }

        if (currentPiece instanceof Pawn) {
            if (currentPoint.isDiagonal(destination)) {
                if (board.get(destination) == null) {
                    throw new IllegalArgumentException("폰은 상대방의 기물이 대각선에 위치한 경우만 이동할 수 있습니다.");
                }
            }
            if (currentPoint.isStraight(destination)) {
                if (board.get(destination) != null) {
                    throw new IllegalArgumentException("폰의 이동 경로에 기물이 존재하여 이동할 수 없습니다.");
                }
            }
        }
        board.put(currentPoint, null);
        board.put(destination, currentPiece);
    }

    public Map<Point, Piece> getBoard() {
        return board;
    }
}
