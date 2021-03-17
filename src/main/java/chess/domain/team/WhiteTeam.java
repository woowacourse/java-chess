package chess.domain.team;

public class WhiteTeam extends Team {
    private static final int WHITE_PAWN_COLUMN = 1;
    private static final int WHITE_PIECE_COLUMN = 0;

    public WhiteTeam() {
        super();
        initializePiece(WHITE_PAWN_COLUMN, WHITE_PIECE_COLUMN);
    }
}
