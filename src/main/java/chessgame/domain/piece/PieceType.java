package chessgame.domain.piece;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import chessgame.domain.Team;

public enum PieceType {
    QUEEN("queen", Queen::from),
    ROOK("rook", Rook::from),
    KNIGHT("knight", Knight::from),
    PAWN("pawn", Pawn::from),
    BISHOP("bishop", Bishop::from),
    KING("king", King::from);

    private static final Map<String, PieceType> PIECE_TYPE_MAP = new HashMap<>();

    static {
        for (PieceType pieceType : values()) {
            PIECE_TYPE_MAP.put(pieceType.name, pieceType);
        }
    }

    private final String name;
    private final Function<Team, Piece> toPiece;

    PieceType(String name, Function<Team, Piece> toPiece) {
        this.name = name;
        this.toPiece = toPiece;
    }

    public static Piece find(String name, Team team) {
        if (PIECE_TYPE_MAP.containsKey(name)) {
            return toPiece(PIECE_TYPE_MAP.get(name), team);
        }
        throw new IllegalArgumentException("기물이름이 잘못되었습니다.");
    }

    public static Piece toPiece(final PieceType pieceType, final Team team) {
        return pieceType.toPiece.apply(team);
    }
}
