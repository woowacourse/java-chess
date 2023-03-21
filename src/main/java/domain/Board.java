package domain;

import domain.piece.EmptyPiece;
import domain.piece.Piece;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {

    private static final String IMPOSSIBLE_MOVE_ERROR_MESSAGE = "이동할 수 없는 위치입니다.";
    private static final String WHITE_TURN_ERROR_MESSAGE = "흰 진영 차례입니다.";
    private static final String BLACK_TURN_ERROR_MESSAGE = "검은 진영 차례입니다.";
    private final PathValidator pathValidator;
    private final Map<Location, Piece> board;


    public Board(final PathValidator pathValidator, final Map<Location, Piece> board) {
        this.pathValidator = pathValidator;
        this.board = board;
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

    public void moveWhite(final Location start, final Location end) {
        final Piece piece = findPiece(start);
        if (piece.isBlack()) {
            throw new IllegalArgumentException(WHITE_TURN_ERROR_MESSAGE);
        }
        move(start, end);
    }

    public void moveBlack(final Location start, final Location end) {
        final Piece piece = findPiece(start);
        if (piece.isWhite()) {
            throw new IllegalArgumentException(BLACK_TURN_ERROR_MESSAGE);
        }
        move(start, end);
    }

    private void move(final Location start, final Location end) {
        canMove(start, end);
        convert(start, end);
    }

    private void canMove(final Location startLocation, final Location endLocation) {
        final Piece startPiece = findPiece(startLocation);
        final Piece endPiece = findPiece(endLocation);
        final Section start = Section.of(startLocation, startPiece);
        final Section end = Section.of(endLocation, endPiece);
        final List<Piece> path = getPiecesInPath(start, end);
        if (pathValidator.isValid(start, end, path)) {
            return;
        }
        throw new IllegalArgumentException(IMPOSSIBLE_MOVE_ERROR_MESSAGE);
    }

    private void convert(final Location start, final Location end) {
        board.replace(end, board.get(start));
        board.replace(start, EmptyPiece.make());
    }

    private List<Piece> getPiecesInPath(final Section start, final Section end) {
        final Piece piece = findPiece(start.getLocation());
        final List<Location> paths = piece.searchPath(start, end);
        return paths.stream()
            .map(this::findPiece)
            .collect(Collectors.toList());
    }

    public Piece findPiece(final Location location) {
        return board.get(location);
    }
}
