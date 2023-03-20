package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {

    private static final String IMPOSSIBLE_MOVE_ERROR_MESSAGE = "이동할 수 없는 위치입니다.";
    private static final String WHITE_TURN_ERROR_MESSAGE = "흰 진영 차례입니다.";
    private static final String BLACK_TURN_ERROR_MESSAGE = "검은 진영 차례입니다.";
    private final PathValidator pathValidator;
    private List<Line> lines;

    public Board(PathValidator pathValidator) {
        this.pathValidator = pathValidator;
    }

    public void initialize() {
        final List<Line> lines = new ArrayList<>();
        lines.add(Line.whiteBack());
        lines.add(Line.whiteFront());
        IntStream.range(0, 4).mapToObj(count -> Line.empty()).forEach(lines::add);
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
        final Square endSquare = findSquare(endLocation);
        final ValidateDto start = ValidateDto.of(startLocation, startSquare);
        final ValidateDto end = ValidateDto.of(endLocation, endSquare);
        final List<Square> path = getSquaresInPath(startLocation, endLocation);
        if (pathValidator.isValid(start, end, path)) {
            return;
        }
        throw new IllegalArgumentException(IMPOSSIBLE_MOVE_ERROR_MESSAGE);
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
