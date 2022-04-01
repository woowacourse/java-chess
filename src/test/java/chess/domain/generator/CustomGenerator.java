package chess.domain.generator;

import chess.domain.piece.Piece;
import java.util.List;

public class CustomGenerator extends Generator {

    private static final int PAWN_RANK = 2;
    private static final int DEFAULT_RANK = 1;

    public CustomGenerator() {
        super(PAWN_RANK, DEFAULT_RANK);
    }

    @Override
    void createPawn(final List<Piece> pieces) {
    }
}
