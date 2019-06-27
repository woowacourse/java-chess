package chess.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum PieceValue {
    KING("K", 0, 1),
    QUEEN("Q", 9, 2),
    KNIGHT("N", 2.5, 4),
    BISHOP("B", 3, 5),
    ROOK("R", 5, 3),
    PAWN("P", 1, 6);

    private final int kindId;
    private String name;
    private double score;

    PieceValue(String name, double score, int kindId) {
        this.name = name;
        this.score = score;
        this.kindId = kindId;
    }

    public static PieceValue valueOf(int kindId) {
        List<PieceValue> allValues = Arrays.asList(PieceValue.values());
        return allValues.stream()
                .filter(value -> value.kindId == kindId)
                .collect(Collectors.toList())
                .get(0);
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public int getKindId() {
        return kindId;
    }
}
