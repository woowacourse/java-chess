package chess.domain.generator;

public class WhiteGenerator extends Generator {

    private static final int PAWN_RANK = 2;
    private static final int DEFAULT_RANK = 1;

    public WhiteGenerator() {
        super(PAWN_RANK, DEFAULT_RANK);
    }
}
