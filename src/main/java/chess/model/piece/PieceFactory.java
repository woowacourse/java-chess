package chess.model.piece;

import chess.model.Color;
import chess.model.piece.nonsliding.King;
import chess.model.piece.nonsliding.Knight;
import chess.model.piece.pawn.BlackPawn;
import chess.model.piece.pawn.InitialBlackPawn;
import chess.model.piece.pawn.InitialWhitePawn;
import chess.model.piece.pawn.WhitePawn;
import chess.model.piece.sliding.Bishop;
import chess.model.piece.sliding.Queen;
import chess.model.piece.sliding.Rook;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public final class PieceFactory {

    private static final Map<PieceType, Function<Color, Piece>> factories;

    static {
        factories = new EnumMap<>(PieceType.class);
        factories.put(PieceType.BLACK_PAWN, PieceFactory::initialBlackPawn);
        factories.put(PieceType.WHITE_PAWN, PieceFactory::initialWhitePawn);
        factories.put(PieceType.ROOK, Rook::new);
        factories.put(PieceType.KNIGHT, Knight::new);
        factories.put(PieceType.BISHOP, Bishop::new);
        factories.put(PieceType.KING, King::new);
        factories.put(PieceType.QUEEN, Queen::new);
    }

    private PieceFactory() {
    }

    private static Piece initialBlackPawn(final Color color) {
        final Piece pawn = new BlackPawn(color);
        return new InitialBlackPawn(pawn);
    }

    private static Piece initialWhitePawn(final Color color) {
        final Piece pawn = new WhitePawn(color);
        return new InitialWhitePawn(pawn);
    }

    public static Piece create(final Color color, final PieceType type) {
        if (cannotFoundPiece(type)) {
            throw new IllegalStateException("존재하지 않는 기물입니다.");
        }
        return factories.get(type).apply(color);
    }

    private static boolean cannotFoundPiece(final PieceType type) {
        return !factories.containsKey(type);
    }
}
