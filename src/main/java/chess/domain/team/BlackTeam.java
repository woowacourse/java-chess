package chess.domain.team;

import chess.domain.Position;
import chess.domain.piece.Piece;

import java.util.Map;

public class BlackTeam extends Team {
    private static final String DEFAULT_NAME = "Black";
    public static final int BLACK_PAWN_COLUMN = 6;
    private static final int BLACK_PIECE_COLUMN = 7;
    private static final int BLACK_PAWN_DIRECTION = -1;

    public BlackTeam() {
        super(DEFAULT_NAME);
        initializePawn(BLACK_PAWN_COLUMN, BLACK_PAWN_DIRECTION);
        initializePiece(BLACK_PIECE_COLUMN);
    }

    public BlackTeam(String name) {
        super(name);
        initializePawn(BLACK_PAWN_COLUMN, BLACK_PAWN_DIRECTION);
        initializePiece(BLACK_PIECE_COLUMN);
    }

    public BlackTeam(boolean isCurrentTurn, Map<Position, Piece> pieces) {
        super(DEFAULT_NAME, isCurrentTurn, pieces);
    }

    public BlackTeam(String name, boolean isCurrentTurn, Map<Position, Piece> pieces) {
        super(name, isCurrentTurn, pieces);
    }
}
