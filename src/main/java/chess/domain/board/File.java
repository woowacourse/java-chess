package chess.domain.board;

import chess.domain.piece.Direction;

import java.util.Arrays;
import java.util.function.Predicate;

public enum File {
    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

    private final String signature;
    private final int x;

    File(String signature, int x) {
        this.signature = signature;
        this.x = x;
    }

    public static File findFileBySignature(String fileInput) {
        return findFileOnCondition(file -> file.hasSameSignature(fileInput));
    }

    private static File findFileOnCondition(Predicate<File> hasSameValue) {
        return Arrays.stream(File.values())
                .filter(hasSameValue)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 File(행)값 입니다."));
    }

    private boolean hasSameSignature(String fileInput) {
        return signature.equals(fileInput);
    }

    public File move(Direction direction) {
        int nextX = direction.calculateFile(x);
        return findFileOnCondition(file -> file.hasSameX(nextX));
    }

    private boolean hasSameX(int x) {
        return this.x == x;
    }

    public int calculateDifference(File file) {
        return this.x - file.x;
    }
}
