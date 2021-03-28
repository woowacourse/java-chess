package chess.domain.gamestate;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.board.Row;
import chess.domain.board.Team;
import chess.domain.chessgame.Turn;
import chess.domain.piece.Piece;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Ready implements GameState {

    private static final IllegalArgumentException EXCEPTION
        = new IllegalArgumentException("올바르지 않은 입력입니다.");
    private static final Map<Piece, List<Point>> WHITE_INITIAL_POINTS = new HashMap<>();

    static {
        WHITE_INITIAL_POINTS.put(Piece.KING, Collections.singletonList(Point.of("e1")));
        WHITE_INITIAL_POINTS.put(Piece.QUEEN, Collections.singletonList(Point.of("d1")));
        WHITE_INITIAL_POINTS.put(Piece.ROOK, Arrays.asList(Point.of("a1"), Point.of("h1")));
        WHITE_INITIAL_POINTS.put(Piece.BISHOP, Arrays.asList(Point.of("c1"), Point.of("f1")));
        WHITE_INITIAL_POINTS.put(Piece.KNIGHT, Arrays.asList(Point.of("b1"), Point.of("g1")));
        WHITE_INITIAL_POINTS.put(
            Piece.PAWN,
            Point.allPoints().stream()
                .filter(point -> point.isLocatedIn(Row.TWO))
                .collect(Collectors.toList())
        );
    }

    private final Board board;

    public Ready(Board board) {
        this.board = board;
    }

    @Override
    public GameState start() {
        board.clear();
        WHITE_INITIAL_POINTS.keySet()
            .forEach(this::initializePiece);
        return new Running(board);
    }

    private void initializePiece(Piece piece) {
        WHITE_INITIAL_POINTS.get(piece)
            .forEach(point -> board.putSymmetrically(piece, point));
    }

    @Override
    public GameState move(Point source, Point destination, Turn turn) {
        throw EXCEPTION;
    }

    @Override
    public GameState end() {
        throw EXCEPTION;
    }

    @Override
    public GameState status() {
        throw EXCEPTION;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Team winner() {
        throw EXCEPTION;
    }

    @Override
    public List<Point> movablePoints(Point currentPoint, Turn turn) {
        return new ArrayList<>();
    }
}
