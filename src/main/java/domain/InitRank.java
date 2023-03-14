package domain;

import java.util.Arrays;
import java.util.List;

public enum InitRank {
    NONES(List.of(Type.NONE, Type.NONE, Type.NONE, Type.NONE, Type.NONE, Type.NONE, Type.NONE, Type.NONE)),
    PAWNS(List.of(Type.PAWN, Type.PAWN, Type.PAWN, Type.PAWN, Type.PAWN, Type.PAWN, Type.PAWN, Type.PAWN)),
    OTHERS(List.of(Type.ROOK, Type.KNIGHT, Type.BISHOP, Type.QUEEN, Type.KING, Type.BISHOP, Type.KNIGHT, Type.ROOK));

    private final List<Type> types;

    InitRank(final List<Type> types) {
        this.types = types;
    }

    public static List<Type> from(InitRank initRank) {
        return Arrays.stream(values())
                .filter(value -> value == initRank)
                .findFirst()
                .orElseThrow(IllegalAccessError::new)
                .types;
    }

}
