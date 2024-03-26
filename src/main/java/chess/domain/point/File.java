package chess.domain.point;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum File {
    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g'),
    H('h');

    private static final Map<Character, File> CACHED_FILE = Arrays.stream(values())
            .collect(Collectors.toMap(file -> file.position, Function.identity()));

    private final char position;

    File(final char position) {
        this.position = position;
    }

    public static File from(final char position) {
        if (CACHED_FILE.containsKey(position)) {
            return CACHED_FILE.get(position);
        }
        throw new IllegalArgumentException("가로 위치의 범위를 벗어났습니다. 입력된 가로 위치 = " + position);
    }

    public boolean canMove(final int distanceToMove) {
        final char movedPosition = addPosition(distanceToMove);
        return CACHED_FILE.containsKey(movedPosition);
    }

    public char addPosition(final int distanceToMove) {
        return (char) (position + distanceToMove);
    }

    public int calculateDistanceFrom(final File file) {
        return file.subtractPosition(position);
    }

    private int subtractPosition(final char position) {
        return this.position - position;
    }

    public char getPosition() {
        return position;
    }
}
