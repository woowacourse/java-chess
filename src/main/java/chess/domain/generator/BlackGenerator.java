package chess.domain.generator;

public class BlackGenerator extends Generator {

    private static final int PAWN_RANK = 7;
    private static final int DEFAULT_RANK = 8;

    public BlackGenerator() {
        super(PAWN_RANK, DEFAULT_RANK);
    }
}
