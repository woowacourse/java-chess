package chess.domain.piece;

import chess.domain.strategy.piecemovestrategy.PieceType;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public class PieceFactory {

    private static final Map<PieceType, Function<Color, Piece>> factoryMap = new EnumMap<>(PieceType.class);


    static {
        factoryMap.put(PieceType.KING, King::new);
        factoryMap.put(PieceType.QUEEN, Queen::new);
        factoryMap.put(PieceType.ROOK, Rook::new);
        factoryMap.put(PieceType.BISHOP, Bishop::new);
        factoryMap.put(PieceType.KNIGHT, Knight::new);
        factoryMap.put(PieceType.PAWN, t -> new Pawn(position, pawnMoveStrategy));
        factoryMap.put(PieceType.EMPTY, ignored -> EmptyPiece.getInstance());
    }

    public static Piece generate(PieceType pieceType, Color color) {
        final Function<Color, Piece> constructor = factoryMap.get(pieceType);
        return constructor.apply(color);
    }
}
