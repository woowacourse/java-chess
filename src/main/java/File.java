import java.util.Arrays;

public enum File {
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8");

    private final String text;

    File(String text) {
        this.text = text;
    }

    public static File from(String fileText) {
        return Arrays.stream(File.values())
                .filter(file -> file.getText().equals(fileText))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 file입니다."));
    }

    public String getText() {
        return text;
    }
}
