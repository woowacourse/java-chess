package chess.model.piece;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public final class PieceFactory {

    private static final Map<PieceType, Function<Camp, Piece>> factories = new EnumMap<>(PieceType.class);

    static {
        factories.put(PieceType.BISHOP, camp -> new Piece(PieceType.BISHOP, camp));
        factories.put(PieceType.PAWN, camp -> new Piece(PieceType.INITIAL_PAWN, camp));
        factories.put(PieceType.KING, camp -> new Piece(PieceType.KING, camp));
        factories.put(PieceType.KNIGHT, camp -> new Piece(PieceType.KNIGHT, camp));
        factories.put(PieceType.QUEEN, camp -> new Piece(PieceType.QUEEN, camp));
        factories.put(PieceType.ROOK, camp -> new Piece(PieceType.ROOK, camp));
    }

    private PieceFactory() {
    }

    public static Piece create(final PieceType pieceType, Camp camp) {
        if (cannotFoundPiece(pieceType)) {
            throw new IllegalStateException("존재하지 않는 기물입니다.");
        }
        return factories.get(pieceType).apply(camp);
    }

    private static boolean cannotFoundPiece(final PieceType pieceType) {
        return !factories.containsKey(pieceType);
    }
}
