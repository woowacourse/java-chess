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

    private final String file;

    File(String file) {
        this.file = file;
    }

    public static File of(String text) {
        return Arrays.stream(File.values())
                .filter(file -> file.file.equals(text))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 file축은 없습니다."));
    }

    public int computeDiff(File target) {
        return (int) target.file.charAt(0) - (int) file.charAt(0);
    }

    public File add(int value) {
        int ascii = (int) file.charAt(0) + value;
        String string = String.valueOf((char) ascii);
        return File.of(string);
    }
}
