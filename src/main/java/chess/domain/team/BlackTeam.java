package chess.domain.team;

public class BlackTeam extends Team {
    public static final int BLACK_PAWN_COLUMN = 6;
    private static final int BLACK_PIECE_COLUMN = 7;
    private static final int BLACK_PAWN_DIRECTION = -1;

    public BlackTeam() {
        super();
        initializePawn(BLACK_PAWN_COLUMN, BLACK_PAWN_DIRECTION);
        initializePiece(BLACK_PIECE_COLUMN);
    }
}
