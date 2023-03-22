package domain.position;

import java.util.Arrays;

public enum File {
    A("A"),
    B("B"),
    C("C"),
    D("D"),
    E("E"),
    F("F"),
    G("G"),
    H("H"),
    NOTHING(" ");

    private final String name;

    File(final String name) {
        this.name = name;
    }

    public File move(int distance) {
        final String resultFile = String.valueOf((char) (name.charAt(0) + distance));

        return Arrays.stream(File.values())
                .filter(file -> file.name.equals(resultFile))
                .findAny()
                .orElse(NOTHING);
    }

    public int getDifference(final File other) {
        return other.name.charAt(0) - this.name.charAt(0);
    }

    public String getName() {
        return name;
    }
}
