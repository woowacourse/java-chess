package chess.domain.position;

import java.util.Arrays;

public enum File {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private final int value;

    File(int value) {
        this.value = value;
    }

    public int getFileDifference(File targetFile) {
        return targetFile.value - value;
    }

    public File add(int file) {
        int newFile = value + file;
        return Arrays.stream(values()).filter(files -> files.value == newFile)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
