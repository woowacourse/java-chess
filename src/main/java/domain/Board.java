package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {

    private final PathValidator pathValidator;
    private final List<Line> lines;

    public Board(PathValidator pathValidator) {
        this.lines = initialize();
        this.pathValidator = pathValidator;
    }

    private List<Line> initialize() {
        final List<Line> lines = new ArrayList<>();
        lines.add(Line.blackBack());
        lines.add(Line.blackFront());
        IntStream.range(0, 4)
            .mapToObj(count -> Line.empty())
            .forEach(lines::add);
        lines.add(Line.whiteFront());
        lines.add(Line.whiteBack());
        return lines;
    }

    public void move(final Location start, final Location end) {
        validatePath(start, end);
        findSquare(start).moveTo(findSquare(end));
    }

    private void validatePath(Location start, Location end) {
        List<Square> squares = getSquaresInPath(start, end);
        pathValidator.validate(findSquare(start), squares);
    }

    private List<Square> getSquaresInPath(Location start, Location end) {
        Square square = findSquare(start);
        List<Location> paths = square.searchPath(start, end);
        return paths.stream().map(this::findSquare).collect(Collectors.toList());
    }

    public Square findSquare(Location location) {
        return lines.get(location.getRow()).getByCol(location.getCol());
    }

    public List<Line> getLines() {
        return Collections.unmodifiableList(lines);
    }
}
