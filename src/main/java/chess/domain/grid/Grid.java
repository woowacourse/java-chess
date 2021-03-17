package chess.domain.grid;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private static List<Line> lines;

    static {
        initialize();
    }

    public static void initialize() {
        List<Line> lines = new ArrayList<>();
        lines.add(Line.createGeneralLine(1, false));
        lines.add(Line.createPawnLine(2, false));
        for (int i = 3; i <= 6 ; i++) {
            lines.add(Line.createEmptyLine(i));
        }
        lines.add(Line.createPawnLine(7, true));
        lines.add(Line.createGeneralLine(8, true));
    }
}
