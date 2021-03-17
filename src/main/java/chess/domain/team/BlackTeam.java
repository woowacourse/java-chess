package chess.domain.team;

public class BlackTeam extends Team {
    private static final int BLACK_PAWN_COLUMN = 6;
    private static final int BLACK_PIECE_COLUMN = 7;

    public BlackTeam() {
        super();
        initializePiece(BLACK_PAWN_COLUMN, BLACK_PIECE_COLUMN);
    }
}
