package domain;

import domain.piece.Piece;
import domain.type.direction.PieceMoveDirection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import view.PieceView;

public final class Board {

    private static final String IMPOSSIBLE_MOVE_ERROR_MESSAGE = "가 이동할 수 없는 위치입니다.";
    private static final String WHITE_TURN_ERROR_MESSAGE = "흰 진영 차례입니다.";
    private static final String BLACK_TURN_ERROR_MESSAGE = "검은 진영 차례입니다.";
    private static final int EMPTY_LINE_COUNT = 4;
    private final PathValidator pathValidator;
    private List<Line> lines;

    public Board(final PathValidator pathValidator) {
        this.pathValidator = pathValidator;
    }

    public void initialize() {
        final List<Line> lines = new ArrayList<>();
        lines.add(Line.whiteBack());
        lines.add(Line.whiteFront());
        IntStream.range(0, EMPTY_LINE_COUNT).mapToObj(count -> Line.empty()).forEach(lines::add);
        lines.add(Line.blackFront());
        lines.add(Line.blackBack());
        this.lines = lines;
    }

    public void moveWhite(final Location start, final Location end) {
        final Square square = findSquare(start);
        if (square.isBlack()) {
            throw new IllegalArgumentException(WHITE_TURN_ERROR_MESSAGE);
        }
        move(start, end);
    }

    public void moveBlack(final Location start, final Location end) {
        final Square square = findSquare(start);
        if (square.isWhite()) {
            throw new IllegalArgumentException(BLACK_TURN_ERROR_MESSAGE);
        }
        move(start, end);
    }

    private void move(final Location start, final Location end) {
        validatePath(start, end);
        findSquare(start).moveTo(findSquare(end));
    }

    private void validatePath(final Location startLocation, final Location endLocation) {
        final Square startSquare = findSquare(startLocation);
        final Piece startPiece = startSquare.getPiece();
        final PieceMoveDirection direction = PieceMoveDirection.find(startLocation, endLocation);
        final List<Square> path = getSquaresInPath(startLocation, endLocation);
        if (pathValidator.isValid(direction, startPiece, path)) {
            return;
        }
        throw new IllegalArgumentException(PieceView.findSign(startPiece) + IMPOSSIBLE_MOVE_ERROR_MESSAGE);
    }

    private List<Square> getSquaresInPath(final Location start, final Location end) {
        final Square square = findSquare(start);
        final List<Location> paths = square.searchPath(start, end);
        return paths.stream().map(this::findSquare).collect(Collectors.toList());
    }

    public Square findSquare(final Location location) {
        return lines.get(location.getRow()).getByCol(location.getCol());
    }

    public List<Line> getLines() {
        return Collections.unmodifiableList(lines);
    }
}
