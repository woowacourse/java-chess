package chess.domain.gamestate;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.board.Row;
import chess.domain.board.Team;
import chess.domain.piece.Piece;

import java.util.*;
import java.util.stream.Collectors;

public class Ready implements GameState {
    private static final Map<Piece, List<Point>> WHITE_INITIAL_POINTS = new HashMap<>();

    private final Board board;

    static {
        WHITE_INITIAL_POINTS.put(Piece.KING, Collections.singletonList(Point.of("e1")));
        WHITE_INITIAL_POINTS.put(Piece.QUEEN, Collections.singletonList(Point.of("d1")));
        WHITE_INITIAL_POINTS.put(Piece.ROOK, Arrays.asList(Point.of("a1"), Point.of("h1")));
        WHITE_INITIAL_POINTS.put(Piece.BISHOP, Arrays.asList(Point.of("c1"), Point.of("f1")));
        WHITE_INITIAL_POINTS.put(Piece.KNIGHT, Arrays.asList(Point.of("b1"), Point.of("g1")));
        WHITE_INITIAL_POINTS.put(Piece.PAWN, Point.getAllPoints().stream()
                .filter(point -> point.isRow(Row.TWO))
                .collect(Collectors.toList())
        );
    }

    public Ready(Board board) {
        this.board = board;
    }

    private void initialize() {
        WHITE_INITIAL_POINTS.keySet()
                .forEach(this::initializePiece);
    }

    private void initializePiece(Piece piece) {
        WHITE_INITIAL_POINTS.get(piece)
                .forEach(point -> board.putSymmetrically(piece, point));
    }

    @Override
    public GameState start() {
        initialize();
        return new Running(board);
    }

    @Override
    public GameState end() {
        return new Finished(board);
    }

    @Override
    public GameState move(Point source, Point destination, Team turn) {
        throw new IllegalArgumentException("현재 상태에서 유효하지 않은 명령입니다.");
    }

    @Override
    public GameState status() {
        throw new IllegalArgumentException("현재 상태에서 유효하지 않은 명령입니다.");
    }

    @Override
    public Board board() {
        return board;
    }

    @Override
    public boolean isRunning() {
        return board.isRunning();
    }

    @Override
    public String winner() {
        throw new IllegalArgumentException("현재 상태에서 유효하지 않습니다.");
    }
}
