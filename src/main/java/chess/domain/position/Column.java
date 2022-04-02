package chess.domain.position;

import java.util.Arrays;

public enum Column {

    A("a", 0),
    B("b", 1),
    C("c", 2),
    D("d", 3),
    E("e", 4),
    F("f", 5),
    G("g", 6),
    H("h", 7);

    private final String name;
    private final int file;

    Column(final String name, final int file) {
        this.name = name;
        this.file = file;
    }

    public static Column of(String name) {
        return Arrays.stream(values())
                .filter(file -> file.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("file" + name + " 은 유효하지 않은 좌표입니다."));
    }

    public static Column of(int file) {
        return Arrays.stream(values())
                .filter(column -> column.file == file)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("file" + file + " 은 유효하지 않은 좌표입니다."));
    }

    public int displacementTo(Column column) {
        return column.file - file;
    }

    public Column displacedOf(int displacement) {
        return Column.of(file + displacement);
    }

    public String getName() {
        return name;
    }
}
