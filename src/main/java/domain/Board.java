package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {

    private static final String IMPOSSIBLE_MOVE_ERROR_MESSAGE = "이동할 수 없는 위치입니다.";
    private final PathValidator pathValidator;
    private final List<Line> lines;

    public Board(PathValidator pathValidator) {
        this.lines = initialize();
        this.pathValidator = pathValidator;
    }

    private List<Line> initialize() {
        final List<Line> lines = new ArrayList<>();
        lines.add(Line.whiteBack());
        lines.add(Line.whiteFront());
        IntStream.range(0, 4).mapToObj(count -> Line.empty()).forEach(lines::add);
        lines.add(Line.blackFront());
        lines.add(Line.blackBack());
        return lines;
    }

    public void moveWhite(final Location start, final Location end) {
        final Square square = findSquare(start);
        if (square.isBlack()) {
            throw new IllegalArgumentException("흰 진영 차례입니다.");
        }
        move(start, end);
    }

    public void moveBlack(final Location start, final Location end) {
        final Square square = findSquare(start);
        if (square.isWhite()) {
            throw new IllegalArgumentException("검은 진영 차례입니다.");
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
        final SpecialValidateDto start = SpecialValidateDto.of(startLocation, startSquare.getPiece());
        final SpecialValidateDto end = SpecialValidateDto.of(endLocation, endSquare.getPiece());
        final List<Square> squares = getSquaresInPath(startLocation, endLocation);
        if (isSpecialPath(start, end) || isNormalPath(startSquare, squares)) {
            return;
        }
        throw new IllegalArgumentException(IMPOSSIBLE_MOVE_ERROR_MESSAGE);
    }

    private boolean isNormalPath(final Square startSquare, final List<Square> squares) {
        return pathValidator.validateNormal(startSquare, squares);
    }

    private boolean isSpecialPath(final SpecialValidateDto start, final SpecialValidateDto end) {
        return pathValidator.validateSpecial(start, end);
    }

    private List<Square> getSquaresInPath(Location start, Location end) {
        final Square square = findSquare(start);
        final List<Location> paths = square.searchPath(start, end);
        return paths.stream().map(this::findSquare).collect(Collectors.toList());
    }

    public Square findSquare(Location location) {
        return lines.get(location.getRow()).getByCol(location.getCol());
    }

    public List<Line> getLines() {
        return Collections.unmodifiableList(lines);
    }
}
