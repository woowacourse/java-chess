package chess.piece;

import chess.chessboard.Side;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public class PieceFactory {

    private static final Map<PieceType, Function<Side, Piece>> factoryMap = new EnumMap<>(PieceType.class);


    static {
        factoryMap.put(PieceType.KING, King::new);
        factoryMap.put(PieceType.QUEEN, Queen::new);
        factoryMap.put(PieceType.ROOK, Rook::new);
        factoryMap.put(PieceType.BISHOP, Bishop::new);
        factoryMap.put(PieceType.KNIGHT, Knight::new);
        factoryMap.put(PieceType.PAWN, Pawn::new);
        factoryMap.put(PieceType.EMPTY, ignored -> EmptyPiece.getInstance());
    }

    public static Piece generate(PieceType pieceType, Side side) {
        final Function<Side, Piece> constructor = factoryMap.get(pieceType);
        return constructor.apply(side);
    }
}
