package chess.domain.team;

public class WhiteTeam extends Team {
    public static final int WHITE_PAWN_COLUMN = 1;
    private static final int WHITE_PIECE_COLUMN = 0;
    private static final int WHITE_PAWN_DIRECTION = 1;

    public WhiteTeam() {
        super();
        initializePawn(WHITE_PAWN_COLUMN, WHITE_PAWN_DIRECTION);
        initializePiece(WHITE_PIECE_COLUMN);
    }
}
