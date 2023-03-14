package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Board {
    private final List<Line> lines;

    public Board() {
        this.lines = initialize();
    }

    private List<Line> initialize(){
        final List<Line> lines = new ArrayList<>();
        lines.add(generateBlackBack());
        lines.add(generateBlackFront());
        IntStream.range(0, 4)
            .mapToObj(i -> generateEmpty())
            .forEach(lines::add);
        lines.add(generateWhiteFront());
        lines.add(generateWhiteBack());
        return lines;
    }

    private Line generateBlackFront(){
        return Line.blackFront();
    }

    private Line generateBlackBack(){
        return Line.blackBack();
    }

    private Line generateWhiteFront(){
        return Line.whiteFront();
    }

    private Line generateWhiteBack(){
        return Line.whiteBack();
    }

    private Line generateEmpty(){
        return Line.empty();
    }
}
