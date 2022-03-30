package chess.domain.result;

import java.util.Arrays;

public enum Result {

    WIN(1, "승리"),
    DRAW(0, "무승부"),
    LOSE(-1, "패배"),
    ;

    private final int value;
    private final String name;

    Result(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static Result decide(final Score my, final Score opponent) {
        final int resultValue = my.decideResultValue(opponent);

        return Arrays.stream(Result.values())
                .filter(result -> result.value == resultValue)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 승패를 구할 수 없습니다."));
    }

    public String getName() {
        return name;
    }
}
