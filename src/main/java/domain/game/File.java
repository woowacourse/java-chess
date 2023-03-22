package domain.game;

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

    private final int order;

    File(int order) {
        this.order = order;
    }

    public File getNext() {
        return Arrays.stream(File.values())
                .filter(file -> file.order == this.order + 1)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("서버 내부 에러 - 다음 File은 존재하지 않습니다."));
    }

    public File getPrevious() {
        return Arrays.stream(File.values())
                .filter(file -> file.order == this.order - 1)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("서버 내부 에러 - 이전 File은 존재하지 않습니다."));
    }

    public int calculateIncrement(File targetFile) {
        return targetFile.order - this.order;
    }
}
