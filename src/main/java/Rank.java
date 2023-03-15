import java.util.Arrays;

public enum Rank {
    ONE("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8);

    private final String text;
    private final int order;

    Rank(String text, int order) {
        this.text = text;
        this.order = order;
    }

    public static Rank from(String fileText) {
        return Arrays.stream(Rank.values())
                .filter(file -> file.getText().equals(fileText))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 rank입니다."));
    }

    public String getText() {
        return text;
    }

    public int calculateIncrement(Rank targetRank) {
        return targetRank.order - this.order;
    }

    public Rank getNext() {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.order == this.order + 1)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("인덱스를 벗어난 움직임입니다."));
    }
}
