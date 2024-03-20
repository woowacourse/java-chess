package chess.domain.attribute;

import static chess.domain.piece.PieceType.PAWN;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public enum File {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8)
    ;

    private static final int FILE_MIN = 1;
    private static final int FILE_MAX = 8;
    private final int column;

    File(final int column) {
        this.column = column;
    }

    public static File of(final int column) {
        return Arrays.stream(values())
                .filter(file -> file.column == column)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "파일은 %d~%d 사이로 입력해주세요: %d".formatted(FILE_MIN, FILE_MAX, column)));
    }

    public static File startKingFile() {
        return E;
    }

    public static File startQueenFile() {
        return D;
    }

    public static File startBishopFileOf(final int index) {
        validateIndexExceptPawn(index);
        if (index == 0) {
            return C;
        }
        return F;
    }

    public static File startKnightFileOf(final int index) {
        validateIndexExceptPawn(index);
        if (index == 0) {
            return B;
        }
        return G;
    }

    public static File startRookFileOf(final int index) {
        validateIndexExceptPawn(index);
        if (index == 0) {
            return A;
        }
        return H;
    }

    private static void validateIndexExceptPawn(final int index) {
        List<Integer> validIndices = List.of(0, 1);
        if (!validIndices.contains(index)) {
            throw new IllegalArgumentException("이미 존재하는 기물의 개수는 0 또는 1개입니다: %d".formatted(index));
        }
    }

    public static File startPawnFileOf(final int index) {
        validatePawnIndex(index);
        return values()[index];
    }

    private static void validatePawnIndex(final int index) {
        List<Integer> validIndices = IntStream.range(0, PAWN.getCount())
                .boxed()
                .toList();
        if (!validIndices.contains(index)) {
            throw new IllegalArgumentException("폰의 개수는 0~8개입니다: %d".formatted(index));
        }
    }

    public int getColumn() {
        return column;
    }
}
