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
            throw new IllegalArgumentException("기물을 움직일 수 없습니다.");
        }

        Piece nextPiece = board.get(destination);
        if (nextPiece != null && player.isMyPiece(nextPiece)) {
            throw new IllegalArgumentException("기물을 움직일 수 없습니다.");
        }

        if (!currentPiece.isMovable(currentPoint, destination)) {
            throw new IllegalArgumentException("기물을 움직일 수 없습니다.");
        }

        Direction route = currentPoint.findRoute(destination);
        Point nextPoint = currentPoint.add(route.file(), route.rank()).get();
        while (!nextPoint.equals(destination)) {
            // TODO: get 삭제
            if (board.get(nextPoint) != null) {
                throw new IllegalArgumentException("기물을 움직일 수 없습니다.");
            }
            nextPoint = nextPoint.add(route.file(), route.rank()).get();
        }

        if (currentPiece instanceof Pawn) {
            if (currentPoint.isDiagonal(destination)) {
                if (board.get(destination) == null) {
                    throw new IllegalArgumentException("기물을 움직일 수 없습니다.");
                }
            }
            if (currentPoint.isStraight(destination)) {
                if (board.get(destination) != null) {
                    throw new IllegalArgumentException("기물을 움직일 수 없습니다.");
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
