package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.point.Point;
import java.util.Collections;
import java.util.Map;

public class Board {

    private final Map<Point, Piece> board;

    Board(Map<Point, Piece> board) {
        this.board = board;
    }

    public void move(Point currentPoint, Point destination) {
        Piece currentPiece = board.get(currentPoint);

        movePiece(currentPoint, destination, currentPiece);
    }

    private void movePiece(Point currentPoint, Point destination, Piece currentPiece) {
        board.put(currentPoint, Piece.empty());
        board.put(destination, currentPiece);
    }

    public Map<Point, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public Piece get(Point point) {
        return board.get(point);
    }
}
