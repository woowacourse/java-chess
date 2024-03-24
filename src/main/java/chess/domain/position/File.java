package chess.domain.position;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

public class File {
    private static final Map<String, File> CACHE = IntStream.rangeClosed('a', 'h')
                                                            .mapToObj(i -> String.valueOf((char) i))
                                                            .collect(toMap(Function.identity(), File::new));

    private final String value;

    private File(String value) {
        this.value = value;
    }

    public static File valueOf(String value) {
        validate(value);
        return CACHE.get(value);
    }

    private static void validate(String value) {
        validateAlphabet(value);
        validateSize(value);
    }

    private static void validateAlphabet(String value) {
        if (!CACHE.containsKey(value)) {
            throw new IllegalArgumentException("a~h까지 가능합니다.");
        }
    }

    private static void validateSize(String value) {
        if (value.length() != 1) {
            throw new IllegalArgumentException("a~h까지 가능합니다.");
        }
    }

    public File update(int direction) {
        return CACHE.get(String.valueOf((char) (toChar(value) + direction)));
    }

    public int subtractFile(File file) {
        return toChar(value) - toChar(file.value);
    }

    public int findDirection(File file) {
        return Integer.compare(toChar(file.value), toChar(value));
    }

    private int toChar(String value) {
        return value.charAt(0);
    }

    public int getValue() {
        return toChar(value) - 'a';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        File file = (File) o;
        return value == file.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
