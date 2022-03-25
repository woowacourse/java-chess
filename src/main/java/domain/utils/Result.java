package domain.utils;

public enum Result {
    WIN("승"),
    LOSE("패"),
    DRAW("무승부");

    private final String result;

    Result(final String result) {
        this.result = result;
    }
}
