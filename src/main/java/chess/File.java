package chess;

import java.util.Arrays;

public enum File {
    A, B, C, D, E, F, G, H;

    public static File of(String file) {
        return Arrays.stream(values())
                .filter(ph -> ph.name().equals(file.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 x 좌표값을 입력하였습니다."));
    }
}
