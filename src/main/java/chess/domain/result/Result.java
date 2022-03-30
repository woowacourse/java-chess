package chess.domain.result;

public enum Result {

    WIN("승리"),
    DRAW("무승부"),
    LOSE("패배"),
    ;

    private final String name;

    Result(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
