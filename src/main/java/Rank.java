import java.util.Arrays;

public enum Rank {
    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h");

    private final String text;

    Rank(String text) {
        this.text = text;
    }

    public static Rank from(String rankText) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.getText().equals(rankText))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 rank입니다."));
    }

    public String getText() {
        return text;
    }
}
