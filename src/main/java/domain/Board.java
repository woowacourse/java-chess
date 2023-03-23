package domain;

import domain.config.BoardSetting;
import domain.piece.EmptyPiece;
import domain.piece.Piece;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {

    private static final String IMPOSSIBLE_MOVE_ERROR_MESSAGE = "이동할 수 없는 위치입니다.";
    private static final String WHITE_TURN_ERROR_MESSAGE = "흰 진영 차례입니다.";
    private static final String BLACK_TURN_ERROR_MESSAGE = "검은 진영 차례입니다.";
    private final Map<Location, Piece> board;
    private final ScoreCalculator scoreCalculator;


    public Board(final Map<Location, Piece> board, final ScoreCalculator scoreCalculator) {
        this.board = board;
        this.scoreCalculator = scoreCalculator;
    }

    public void initialize() {
        for (int column = 1; column <= 8; column++) {
            putToBoard(column);
        }
    }

    private void putToBoard(final int column) {
        for (int row = 1; row <= 8; row++) {
            final Piece piece = BoardSetting.findPiece(Location.of(column, row));
            board.put(Location.of(column, row), piece);
        }
    }

    public Piece moveWhite(final Location start, final Location end) {
        final Piece piece = findPiece(start);
        if (piece.isBlack()) {
            throw new IllegalArgumentException(WHITE_TURN_ERROR_MESSAGE);
        }
        return move(start, end);
    }

    public Piece moveBlack(final Location start, final Location end) {
        final Piece piece = findPiece(start);
        if (piece.isWhite()) {
            throw new IllegalArgumentException(BLACK_TURN_ERROR_MESSAGE);
        }
        return move(start, end);
    }

    public Piece findPiece(final Location location) {
        return board.get(location);
    }

    private Piece move(final Location start, final Location end) {
        canMove(start, end);
        return convert(start, end);
    }

    private void canMove(final Location startLocation, final Location endLocation) {
        final Piece startPiece = findPiece(startLocation);
        final Piece endPiece = findPiece(endLocation);
        final Section start = Section.of(startLocation, startPiece);
        final Section end = Section.of(endLocation, endPiece);
        final List<Piece> path = getPiecesInPath(start, end);
        if (isBlocked(path)) {
            throw new IllegalArgumentException(IMPOSSIBLE_MOVE_ERROR_MESSAGE);
        }
    }

    private List<Piece> getPiecesInPath(final Section start, final Section end) {
        final Piece piece = start.getPiece();
        final List<Location> paths = piece.searchPath(start, end);
        return paths.stream()
            .map(this::findPiece)
            .collect(Collectors.toList());
    }

    private boolean isBlocked(final List<Piece> path) {
        return IntStream.range(0, path.size() - 1)
            .mapToObj(path::get)
            .anyMatch(Piece::isNotEmpty);
    }

    private Piece convert(final Location start, final Location end) {
        final Piece endPiece = board.get(end);
        board.replace(end, board.get(start));
        board.replace(start, EmptyPiece.make());
        return endPiece;
    }

    public double calculateWhiteScore() {
        return scoreCalculator.calculateWhite(board);
    }

    public double calculateBlackScore() {
        return scoreCalculator.calculateBlack(board);
    }

    public Map<Location, Piece> getBoard() {
        return board;
    }
}
