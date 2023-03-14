package domain.board;

import java.util.Arrays;
import java.util.List;

public enum File {
    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g'),
    H('h');

    private final char value;

    File(char value) {
        this.value = value;
    }

    public File convertToOpposite() {
        List<File> values = List.of(File.values());

        int index = values.indexOf(this);

        return values.get(values.size() - index);
    }

    public static File findFile(int fileCoordinate) {
        return Arrays.stream(File.values())
                .filter(file -> file.ordinal() == fileCoordinate)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("adfa"));
    }
}
