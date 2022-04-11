package chess.domain.location;

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

    private final String value;

    File(String file) {
        this.value = file;
    }

    public static File of(String text) {
        return Arrays.stream(File.values())
                .filter(file -> file.value.equals(text))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 file축은 없습니다."));
    }

    public int computeDiff(File target) {
        return (int) target.value.charAt(0) - (int) value.charAt(0);
    }

    public File add(int value) {
        int ascii = (int) this.value.charAt(0) + value;
        String string = String.valueOf((char) ascii);
        return File.of(string);
    }

    public String getValue() {
        return value;
    }
}
