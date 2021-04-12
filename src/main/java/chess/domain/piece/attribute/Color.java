package chess.domain.piece.attribute;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public enum Color {
    BLACK(String::toUpperCase),
    WHITE(String::toLowerCase),
    BLANK(UnaryOperator.identity());

    private static final List<Color> USER_COLORS = Arrays.stream(values())
            .filter(Color::isNotBlank)
            .collect(Collectors.toList());

    private final UnaryOperator<String> notationRule;

    Color(UnaryOperator<String> notationRule) {
        this.notationRule = notationRule;
    }

    public static List<Color> getUserColors() {
        return Collections.unmodifiableList(USER_COLORS);
    }

    public static Color of(String colorName) {
        return Arrays.stream(values())
                .filter(color -> color.name().equalsIgnoreCase(colorName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 이름의 color가 없습니다."));
    }

    private static boolean isNotBlank(Color color) {
        return color != BLANK;
    }

    public String changeNotation(String notation) {
        return notationRule.apply(notation);
    }

    public Color opposite() {
        return USER_COLORS.stream()
                .filter(color -> this != color)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("반대되는 색상이 없습니다."));
    }
}
