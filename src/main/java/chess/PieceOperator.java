package chess;

import chess.board.Board;
import chess.board.Point;
import chess.board.Row;
import chess.piece.Piece;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PieceOperator {

    private static final Map<Piece, List<Point>> WHITE_INITIAL_POINTS = new HashMap<>();

    static {
        WHITE_INITIAL_POINTS.put(Piece.KING, Collections.singletonList(Point.of("e1")));
        WHITE_INITIAL_POINTS.put(Piece.QUEEN, Collections.singletonList(Point.of("d1")));
        WHITE_INITIAL_POINTS.put(Piece.ROOK, Arrays.asList(Point.of("a1"), Point.of("h1")));
        WHITE_INITIAL_POINTS.put(Piece.BISHOP, Arrays.asList(Point.of("c1"), Point.of("f1")));
        WHITE_INITIAL_POINTS.put(Piece.KNIGHT, Arrays.asList(Point.of("b1"), Point.of("g1")));
        WHITE_INITIAL_POINTS.put(
            Piece.PAWN,
            Point.getAllPoints().stream()
                .filter(point -> point.isRow(Row.TWO))
                .collect(Collectors.toList())
        );
    }

    private final Board board;

    public PieceOperator(Board board) {
        this.board = board;
    }

    public void initialize() {
        WHITE_INITIAL_POINTS.keySet()
            .forEach(this::initializePiece);
    }

    private void initializePiece(Piece piece) {
        WHITE_INITIAL_POINTS.get(piece)
            .forEach(point -> board.putSymmetrically(piece, point));
    }

    public void move(Point source, Point destination) {
        if (!board.canMove(source, destination)) {
            throw new IllegalArgumentException("해당 위치로는 이동할 수 없는 말입니다.");
        }
        board.move(source, destination);
    }
}
