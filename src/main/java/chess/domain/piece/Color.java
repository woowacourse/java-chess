package chess.domain.piece;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum Color {

    BLACK("검은색", 1),
    WHITE("흰색", 2),
    ;

    private final String name;
    private final int order;

    Color(final String name, final int order) {
        this.name = name;
        this.order = order;
    }

    public Color reversed() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }

    public static List<Color> sorted() {
        return Arrays.stream(Color.values())
                .sorted(Comparator.comparing(Color::getOrder))
                .collect(Collectors.toUnmodifiableList());
    }

    public String getName() {
        return name;
    }

    private int getOrder() {
        return order;
    }
}
