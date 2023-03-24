package chess.domain.board;

import chess.exception.FileCanNotFindException;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum File {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7);

    private static final char DIFFERENCE_BETWEEN_LETTER_AND_INDEX = 'a';
    private static final Map<Integer, File> FILES = Collections.unmodifiableMap(Stream.of(values())
            .collect(Collectors.toMap(File::getFile, Function.identity())));

    private final int file;

    File(int file) {
        this.file = file;
    }

    public static File findFileByLetter(char letter) {
        return findFile(letter - DIFFERENCE_BETWEEN_LETTER_AND_INDEX);
    }

    public static File findFile(int fileIndex) {
        if (!FILES.containsKey(fileIndex)) {
            throw new FileCanNotFindException();
        }
        return FILES.get(fileIndex);
    }

    public int calculateGap(File other) {
        return file - other.file;
    }

    public int getFile() {
        return file;
    }
}
