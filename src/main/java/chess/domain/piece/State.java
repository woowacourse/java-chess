package chess.domain.piece;

import java.util.stream.Stream;

public enum State {

    PAWN('P', 1),
    ROOK('R', 5),
    KNIGHT('N', 2.5),
    BISHOP('B', 3),
    QUEEN('Q', 9),
    KING('K', 0);

    private final char name;
    private final double score;

    State(char name, double score) {
        this.name = name;
        this.score = score;
    }

    public static State from(final String name) {
        return Stream.of(State.values())
                .filter(state -> state.name == name.charAt(0))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 이름의 체스말이 없습니다."));
    }

    public char getName() {
        return name;
    }

    public double getScore() {
        return score;
    }
}
