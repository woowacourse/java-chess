package domain.piece;

import domain.game.PieceType;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public class PieceFactory {
    private final Map<PieceType, Function<Side, Piece>> pieces = new EnumMap<>(PieceType.class);

    public PieceFactory() {
        pieces.put(PieceType.PAWN, Pawn::new);
        pieces.put(PieceType.KNIGHT, Knight::new);
        pieces.put(PieceType.KING, King::new);
        pieces.put(PieceType.QUEEN, Queen::new);
        pieces.put(PieceType.ROOK, Rook::new);
        pieces.put(PieceType.BISHOP, Bishop::new);
        pieces.put(PieceType.EMPTY_PIECE, (side) -> new EmptyPiece());
    }

    public Piece create(PieceType pieceType, Side side) {
        Function<Side, Piece> pieceConstructorBySide = pieces.get(pieceType);
        return pieceConstructorBySide.apply(side);
    }
}
