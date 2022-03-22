package chess;

import static chess.File.*;
import static chess.PieceType.*;
import static chess.Rank.*;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> values;

    public Board() {
        this.values = initBoard();
    }

    private Map<Position, Piece> initBoard() {
        Map<Position, Piece> result = new HashMap<>();

        result.put(new Position(ONE, A), new Piece(ROOK, ONE, A));
        result.put(new Position(ONE, B), new Piece(KNIGHT, ONE, B));
        result.put(new Position(ONE, C), new Piece(BISHOP, ONE, C));
        result.put(new Position(ONE, D), new Piece(QUEEN, ONE, D));
        result.put(new Position(ONE, E), new Piece(KING, ONE, E));
        result.put(new Position(ONE, F), new Piece(BISHOP, ONE, F));
        result.put(new Position(ONE, G), new Piece(KNIGHT, ONE, G));
        result.put(new Position(ONE, H), new Piece(ROOK, ONE, H));

        for (File file : File.values()) {
            result.put(new Position(TWO, file), new Piece(PAWN, TWO, file));
            result.put(new Position(SEVEN, file), new Piece(PAWN, SEVEN, file));
        }

        result.put(new Position(EIGHT, A), new Piece(ROOK, EIGHT, A));
        result.put(new Position(EIGHT, B), new Piece(KNIGHT, EIGHT, B));
        result.put(new Position(EIGHT, C), new Piece(BISHOP, EIGHT, C));
        result.put(new Position(EIGHT, D), new Piece(QUEEN, EIGHT, D));
        result.put(new Position(EIGHT, E), new Piece(KING, EIGHT, E));
        result.put(new Position(EIGHT, F), new Piece(BISHOP, EIGHT, F));
        result.put(new Position(EIGHT, G), new Piece(KNIGHT, EIGHT, G));
        result.put(new Position(EIGHT, H), new Piece(ROOK, EIGHT, H));

        return result;
    }

    public Map<Position, Piece> getValues() {
        return values;
    }
}
