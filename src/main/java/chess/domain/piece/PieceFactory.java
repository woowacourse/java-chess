package chess.domain.piece;

import chess.domain.position.Position;
import java.util.function.BiFunction;

public class PieceFactory {

    public static Piece of(String type, String color, String position) {
        PieceType pieceType = PieceType.of(type);
        BiFunction<Color, Position, Piece> biFunction  = pieceType.getConstructor();

        return biFunction.apply(Color.of(color), Position.of(position));
    }

}
