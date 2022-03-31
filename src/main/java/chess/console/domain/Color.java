package chess.console.domain;

import java.util.Arrays;
import java.util.function.Function;

public enum Color {

    WHITE("White", String::toLowerCase),
    BLACK("Black", String::toUpperCase),
    ;

    private final String colorName;
    private final Function<String, String> caseConverter;

    Color(final String colorName, final Function<String, String> caseConvertor) {
        this.colorName = colorName;
        this.caseConverter = caseConvertor;
    }

    public static Color from(final String colorName) {
        return Arrays.stream(values())
                .filter(it -> colorName.equals(it.colorName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("색상 이름이 잘못되었습니다."));
    }

    public String convertCase(final String value) {
        return caseConverter.apply(value);
    }
}
