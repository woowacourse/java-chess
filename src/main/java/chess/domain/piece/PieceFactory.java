package chess.domain.piece;

import chess.dto.response.ResponseCode;
import chess.exception.ChessException;

import java.util.HashMap;
import java.util.Map;

public class PieceFactory {
    private static final Map<Condition, Constructor> constructors = new HashMap<>();

    static {
        constructors.put(pieceName -> pieceName == Bishop.NAME_WHEN_BLACK || pieceName == Bishop.NAME_WHEN_WHITE,
                (color, x, y) -> new Bishop(color, x, y));
        constructors.put(pieceName -> pieceName == Empty.NAME,
                (color, x, y) -> new Empty(color, x, y));
        constructors.put(pieceName -> pieceName == King.NAME_WHEN_BLACK || pieceName == King.NAME_WHEN_WHITE,
                (color, x, y) -> new King(color, x, y));
        constructors.put(pieceName -> pieceName == Knight.NAME_WHEN_BLACK || pieceName == Knight.NAME_WHEN_WHITE,
                (color, x, y) -> new Knight(color, x, y));
        constructors.put(pieceName -> pieceName == Pawn.NAME_WHEN_BLACK || pieceName == Pawn.NAME_WHEN_WHITE,
                (color, x, y) -> new Pawn(color, x, y));
        constructors.put(pieceName -> pieceName == Queen.NAME_WHEN_BLACK || pieceName == Queen.NAME_WHEN_WHITE,
                (color, x, y) -> new Queen(color, x, y));
        constructors.put(pieceName -> pieceName == Rook.NAME_WHEN_BLACK || pieceName == Rook.NAME_WHEN_WHITE,
                (color, x, y) -> new Rook(color, x, y));
    }

    private PieceFactory() {
    }

    public static Piece from(char pieceName, Color color, char x, char y) {
        return constructors.entrySet()
                .stream()
                .filter(entry -> entry.getKey().match(pieceName))
                .map(Map.Entry::getValue)
                .map(constructor -> constructor.create(color, x, y))
                .findAny()
                .orElseThrow(() -> new ChessException(ResponseCode.NOT_EXISTING_PIECE));
    }
}
