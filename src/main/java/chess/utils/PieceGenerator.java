package chess.utils;

import chess.piece.*;

import java.util.Map;
import java.util.function.Function;

public class PieceGenerator {

    private static final Map<String, Function<String, Piece>> generator = Map.of(
            "k", (String color) -> new King(Color.valueOf(color)),
            "q", (String color) -> new Queen(Color.valueOf(color)),
            "r", (String color) -> new Rook(Color.valueOf(color)),
            "b", (String color) -> new Bishop(Color.valueOf(color)),
            "n", (String color) -> new Knight(Color.valueOf(color)),
            "p", (String color) -> new Pawn(Color.valueOf(color)),
            ".", (String color) -> new Blank()
    );

    public static Piece generate(String type, String color) {
        return generator.get(type).apply(color);
    }
}
