import java.util.Arrays;

public enum File {
    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h");

    private final String text;

    File(String text) {
        this.text = text;
    }

    public static File from(String rankText) {
        return Arrays.stream(File.values())
                .filter(rank -> rank.getText().equals(rankText))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 file입니다."));
    }

    public String getText() {
        return text;
    }
}
