package chess.domain;

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

    private final int position;

    File(final int position) {
        this.position = position;
    }

    public static File from(final int value) {
        return Arrays.stream(values())
                .filter(file -> file.position == value)
                .findFirst()
                .orElseThrow(
                        () -> {
                            throw new IllegalArgumentException("해당 값의 파일이 없습니다.");
                        }
                );
    }

    public int calculateDifference(final File target) {
        return target.position - this.position;
    }

    public File getNextFile(final int fileDifference) {
        int nextPosition = this.position + fileDifference;
        return File.from(nextPosition);
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
