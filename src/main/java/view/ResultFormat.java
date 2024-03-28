package view;

import java.util.Arrays;
import model.Result;

public enum ResultFormat {

    BLACK_WIN(Result.BLACK_WIN, "BLACK 우승"),
    WHITE_WIN(Result.WHITE_WIN, "WHITE 우승"),
    DRAW(Result.DRAW, "무승부");

    private final Result result;
    private final String value;

    ResultFormat(Result result, String value) {
        this.result = result;
        this.value = value;
    }

    public static ResultFormat from(Result result) {
        return Arrays.stream(values())
                .filter(resultFormat -> resultFormat.result == result)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 값은 없습니다."));
    }

    public String getValue() {
        return value;
    }
}
