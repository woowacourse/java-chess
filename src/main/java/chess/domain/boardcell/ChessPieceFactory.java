package chess.domain.boardcell;

import chess.domain.Team;

import java.util.HashMap;
import java.util.Map;

public class ChessPieceFactory {

    private static Map<PieceType, ChessPiece> mapper = new HashMap<>();

    static {
        mapper.put(PieceType.ROOK_WHITE, Rook.getInstance(Team.WHITE));
        mapper.put(PieceType.ROOK_BLACK, Rook.getInstance(Team.BLACK));
        mapper.put(PieceType.KNIGHT_WHITE, Knight.getInstance(Team.WHITE));
        mapper.put(PieceType.KNIGHT_BLACK, Knight.getInstance(Team.BLACK));
        mapper.put(PieceType.BISHOP_WHITE, Bishop.getInstance(Team.WHITE));
        mapper.put(PieceType.BISHOP_BLACK, Bishop.getInstance(Team.BLACK));
        mapper.put(PieceType.QUEEN_WHITE, Queen.getInstance(Team.WHITE));
        mapper.put(PieceType.QUEEN_BLACK, Queen.getInstance(Team.BLACK));
        mapper.put(PieceType.KING_WHITE, King.getInstance(Team.WHITE));
        mapper.put(PieceType.KING_BLACK, King.getInstance(Team.BLACK));
        mapper.put(PieceType.PAWN_WHITE, Pawn.getInstance(Team.WHITE));
        mapper.put(PieceType.PAWN_BLACK, Pawn.getInstance(Team.BLACK));
    }

    private ChessPieceFactory() {
    }

    public static ChessPiece create(PieceType type) {
        return mapper.get(type);
    }
}
