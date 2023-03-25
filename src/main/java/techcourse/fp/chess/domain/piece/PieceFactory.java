package techcourse.fp.chess.domain.piece;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;
import techcourse.fp.chess.domain.piece.ordinary.Bishop;
import techcourse.fp.chess.domain.piece.ordinary.King;
import techcourse.fp.chess.domain.piece.ordinary.Knight;
import techcourse.fp.chess.domain.piece.ordinary.Queen;
import techcourse.fp.chess.domain.piece.ordinary.Rook;
import techcourse.fp.chess.domain.piece.pawn.BlackPawn;
import techcourse.fp.chess.domain.piece.pawn.WhitePawn;

public class PieceFactory {

    private static final Map<PieceType, Function<Color, Piece>> factory = init();

    private static EnumMap<PieceType, Function<Color, Piece>> init() {
        final EnumMap<PieceType, Function<Color, Piece>> factory = new EnumMap<>(PieceType.class);

        factory.put(PieceType.KING, King::create);
        factory.put(PieceType.QUEEN, Queen::create);
        factory.put(PieceType.ROOK, Rook::create);
        factory.put(PieceType.BISHOP, Bishop::create);
        factory.put(PieceType.KNIGHT, Knight::create);
        factory.put(PieceType.EMPTY, color -> Empty.create());
        factory.put(PieceType.PAWN, color -> {

            if (color.isBlack()) {
                return BlackPawn.create();
            }
            if (color.isWhite()) {
                return WhitePawn.create();
            }

            throw new IllegalStateException("기물의 색이 EMPTY일수 없습니다.");
        });

        return factory;
    }


    public static Piece generate(Color color, PieceType pieceType) {
        return factory.get(pieceType).apply(color);
    }
}
