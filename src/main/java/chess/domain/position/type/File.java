package chess.domain.position.type;

import chess.domain.piece.type.Direction;
import java.util.Arrays;

public enum File {
    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

    private final String value;
    private final int order;

    File(String value, int order) {
        this.value = value;
        this.order = order;
    }

    public static File of(String fileInput) {
        return File.valueOf(fileInput.toUpperCase());
    }

    public File getMovedFile(Direction direction) {
        int movedX = order + direction.getX();
        return findFileByOrder(movedX);
    }

    private static File findFileByOrder(int order) {
        return Arrays.stream(values())
            .filter(file -> file.getOrder() == order)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("잘못된 file값 입니다."));
    }

    public boolean isSameAs(File destinationFile) {
        return this == destinationFile;
    }

    public String getValue() {
        return value;
    }

    public int getOrder() {
        return order;
    }
}
